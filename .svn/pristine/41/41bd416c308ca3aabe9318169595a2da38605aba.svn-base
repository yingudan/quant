package com.ujuit.quant.firmoffer.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ujuit.quant.firmoffer.constant.TradingDirectType;
import com.ujuit.quant.firmoffer.dto.DailyPositionDto;
import com.ujuit.quant.firmoffer.dto.IncomeNowSituationDto;
import com.ujuit.quant.firmoffer.dto.TransactionDto;
import com.ujuit.quant.firmoffer.model.DailyAssets;
import com.ujuit.quant.firmoffer.model.DailyPosition;
import com.ujuit.quant.firmoffer.model.IncomeSituation;
import com.ujuit.quant.firmoffer.model.TMinIncome;
import com.ujuit.quant.firmoffer.model.TSubAccount;
import com.ujuit.quant.firmoffer.model.TTransactionDetail;
import com.ujuit.quant.firmoffer.service.DailyPositionService;
import com.ujuit.quant.firmoffer.service.IncomeSituationService;
import com.ujuit.quant.firmoffer.service.TStrategyParamsService;
import com.ujuit.quant.firmoffer.service.TTransactionDetailService;
import com.ujuit.quant.utils.DataUtils;
import com.ujuit.quant.utils.DateUtils;
import com.ujuit.quant.utils.StockUtil;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class IncomeSituationServiceImpl implements IncomeSituationService {

	@Resource
	Dao dao;

	@Resource
	DailyPositionService dailypositionService;

	@Resource
	TTransactionDetailService ttransactiondetailService;

	@Resource
	TStrategyParamsService tstrategyparamsService;

	@Override
	public IncomeSituation queryInsTheMaxProfit(String userId, Integer strategyId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("strategyId", strategyId);
		return dao.find(new DataItem(IncomeSituation.class, "queryInsTheMaxProfit"), map);
	}

	@Override
	public IncomeSituation findHisIncomeSituation(String groupId, String strategyId, String testId, Long startTime,
			Long endTime) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("groupId", groupId);
		IncomeSituation dto = new IncomeSituation();
		if (startTime != null) {
			map.put("startTime", new Date(startTime));
		}
		if (endTime != null) {
			map.put("endTime", new Date(endTime));
		}
		if (groupId == null || "".equals(groupId)) {
			map.put("status", "1"); // 没有组id就说明是在进行中的那组
			dto = dao.find(new DataItem(IncomeSituation.class, "findHisIncomeSituation"), map);
		} else {
			dto = dao.find(new DataItem(IncomeSituation.class, "findHisIncomeSituationBygroupId"), map);// 对应组的收入详情
		}

		if (dto != null) {
			dto.setAlpha(DataUtils.formatBd(dto.getAlpha()));
			dto.setYield(DataUtils.formatBd(dto.getYield()));
		}
		dto.setIndexDto(tstrategyparamsService.queryIndexDtoByTestId(Integer.valueOf(testId)));
		return dto;
	}

	/**
	 * 实盘组装收益率等
	 */
	private IncomeNowSituationDto fitDto(String groupId, String strategyId, String testId, Integer subAccountId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("date", DateUtils.getToDay());
		map.put("subAccountId", subAccountId);
		map.put("testId", testId);
		map.put("strategyId", strategyId);
		IncomeNowSituationDto dto = findSituationByDate(map);
		// 查询上一天
		if (dto == null) {// 为空
			dto = new IncomeNowSituationDto();
			// 查询最近一条
			IncomeSituation incomeSituation = findBySubAccountId(subAccountId);
			if (incomeSituation != null) {
				dto.setAlpha(incomeSituation.getAlpha());
				dto.setBeta(incomeSituation.getBeta());
				dto.setShapratio(incomeSituation.getShapratio());
				dto.setReturnVolatlity(incomeSituation.getRelativeReturn());
			}
			// 实盘最新计算的值 基本不会为null
			IncomeNowSituationDto thedto = dao.find(new DataItem(IncomeSituation.class, "findTheNowIncomeSituation"),
					map);
			if (thedto != null) {
				dto.setAccumulatedReturn(thedto.getAccumulatedReturn());
				// dto.setProfit(thedto.getProfit());
				dto.setBenchmark(thedto.getBenchmark());
				dto.setYield(thedto.getYield());
				// 设置实盘最大回测
				dto.setMaxRetrace(thedto.getMaxRetrace());
				dto.setAccumulatedReturn(thedto.getAccumulatedReturn());
			}
		} else {
			dto.setAccumulatedReturn(dto.getAccumulatedReturn());
			if (dto.getAlpha() == null) {
				map.clear();
				map.put("subAccountId", subAccountId);
				map.put("smallDate", DateUtils.getSpecifiedDayBefore(DateUtils.TODAY));
				IncomeNowSituationDto bfDaydto = findSituationByDate(map);
				if (bfDaydto != null) {
					dto.setAlpha(bfDaydto.getAlpha());
					dto.setShapratio(bfDaydto.getShapratio());
					dto.setBeta(bfDaydto.getBeta());
				}
			}
		}
		return dto;
	}

	/**
	 * 实盘持仓计算
	 * 
	 * @author shandowF
	 * @date 2018年2月1日
	 */
	private DailyPositionDto getTheDplist(String groupId, String strategyId, String testId, TMinIncome tminincome,
			TSubAccount account) {
		// 我的持仓
		DailyPositionDto theDplist = new DailyPositionDto();
		// 持仓金额
		BigDecimal dailyMoney = BigDecimal.ZERO;
		List<DailyPosition> dplist = dailypositionService.findNowDaily(groupId, strategyId, testId, null, null);
		// 这里要用最新的价格来算
		Hashtable<String, BigDecimal> hashtable = StockUtil.get();
		Iterator<DailyPosition> it = dplist.iterator();
		try {
			while (it.hasNext()) {
				DailyPosition dp = it.next();
				// 持仓数为0的股票去掉 可以在sql中优化
				if (BigDecimal.ZERO.compareTo(dp.getPositionNum()) == 0) {
					it.remove();
				} else {
					BigDecimal nowPrice = null;
					if (hashtable != null) {
						nowPrice = hashtable.get(dp.getCode());
					}
					if (nowPrice == null) {
						nowPrice = dp.getNowPrice();
					}
					dp.setNowPrice(nowPrice);
					dp.setProfit(nowPrice.subtract(dp.getTranPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN))
							.multiply(dp.getPositionNum()));
					// 当前持仓数量*当前股票价格
					dp.setPositionPrice(dp.getPositionNum().multiply(nowPrice));
					dailyMoney = dailyMoney.add(dp.getPositionNum().multiply(nowPrice));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		theDplist.setDpList(dplist);
		if (tminincome != null) {
			theDplist.setTotalAssets(tminincome.getMoney());// 实盘
		} else {
			if (account != null) {
				theDplist.setTotalAssets(account.getMoneyTotal());
			}
		}
		if (account != null) {
			theDplist.setUsableAssets(account.getSurplusMoney());// 可用金额
			theDplist.setNowSpace(dailyMoney// 持仓金额
					.divide(theDplist.getTotalAssets(), 2, BigDecimal.ROUND_HALF_DOWN));// 当前仓位

		}
		theDplist.setPositionAssets(dailyMoney);
		theDplist.setTime(DateUtils.TODAY);
		return theDplist;
	}

	@Override
	public IncomeNowSituationDto findNowIncomeSituation(String groupId, String strategyId, String testId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("groupId", groupId);
		Integer subAccountId = tstrategyparamsService.queryGroupIdByTestId(Integer.valueOf(testId));
		// 查询收益率
		IncomeNowSituationDto dto = fitDto(groupId, strategyId, testId, subAccountId);
		// 获取实盘最新资产
		TMinIncome tminincome = dao.find(new DataItem(TMinIncome.class, "findTMinMoeny"), map);
		// 查询我的可用资金
		TSubAccount account = dao.find(new DataItem(DailyAssets.class, "findTSubAccount"), map);
		// 我的当日持仓持仓
		dto.setTheDplist(getTheDplist(groupId, strategyId, testId, tminincome, account));
		// 我的当日交易
		dto.setTheTclist(getTheTclist(testId));

		// 设置实盘指标
		dto.setIndexDto(tstrategyparamsService.queryIndexDtoByTestId(Integer.valueOf(testId)));
		return dto;
	}

	private TransactionDto getTheTclist(String testId) {
		HashMap<String, Object> map = new HashMap<>();
		TransactionDto theTclist = new TransactionDto();
		theTclist.setTheTime(DateUtils.TODAY);
		map.put("startTime", DateUtils.getSatrtToDay());
		map.put("endTime", DateUtils.getEndToDay());
		map.put("testId", testId);
		List<TTransactionDetail> ttrList = dao.getDataList(new DataItem(TTransactionDetail.class, "findNowTtrDetail"),
				map);
		if (ttrList != null && ttrList.size() > 0) {
			theTclist.setSum(ttrList.size());// 设置交易记录数
			BigDecimal sale = BigDecimal.ZERO;
			BigDecimal buy = BigDecimal.ZERO;
			for (TTransactionDetail detail : ttrList) {
				if (TradingDirectType.BUY.getValue().equals(detail.getTradingDirection())) {// 买入
					buy = buy.add(detail.getAmount());
				} else if (TradingDirectType.SALE.getValue().equals(detail.getTradingDirection())) {
					sale = sale.add(detail.getAmount());
				}
				BigDecimal tranNum = new BigDecimal(detail.getTranNum());
				detail.setAveTranPrice(detail.getAmount().divide(tranNum, BigDecimal.ROUND_HALF_DOWN));
			}
			theTclist.setSale(sale);
			theTclist.setBuying(buy);

		}
		theTclist.setTtrList(ttrList);
		return theTclist;
	}

	@Override
	public DailyPositionDto findPosition(String groupId, String strategyId, String testId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("groupId", groupId);
		DailyPositionDto theDplist = new DailyPositionDto();
		theDplist.setTime(new Date());
		List<DailyPosition> dplist = dailypositionService.findNowDaily(groupId, strategyId, testId, null, null);
		// 这里要用最新的价格来算
		TSubAccount account = dao.find(new DataItem(DailyAssets.class, "findTSubAccount"), map);
		BigDecimal dailyMoney = new BigDecimal(0);// 持仓金额
		Hashtable<String, BigDecimal> hashtable = StockUtil.get();
		for (DailyPosition dp : dplist) {// 计算收益
			if (dp != null) {
				BigDecimal nowPrice = hashtable.get(dp.getCode());
				if (nowPrice == null) {
					nowPrice = dp.getNowPrice();
				}
				dp.setNowPrice(nowPrice);
				try {
					if (dp.getNowPrice() == null) {
						dp.setProfit(new BigDecimal(0));
					} else {
						dp.setProfit(dp.getPositionNum().multiply(nowPrice).subtract(dp.getPositionPrice()).setScale(2,
								BigDecimal.ROUND_HALF_DOWN));
						dailyMoney = dailyMoney.add(dp.getPositionNum().multiply(dp.getNowPrice()));
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		TMinIncome tminincome = dao.find(new DataItem(TMinIncome.class, "findTMinMoeny"), map);// 获取实盘最新资产
		theDplist.setDpList(dplist);
		if (tminincome != null) {
			theDplist.setTotalAssets(tminincome.getMoney());
		} else {
			theDplist.setTotalAssets(dailyMoney.add(account.getSurplusMoney()));// 总资产
		}
		if (account != null) {
			theDplist.setUsableAssets(account.getSurplusMoney());// 可用金额
			theDplist.setNowSpace(dailyMoney.divide(theDplist.getTotalAssets(), 2, BigDecimal.ROUND_HALF_DOWN));// 当前仓位
		}

		return theDplist;
	}

	@Override
	public IncomeSituation findBySubAccountId(Integer subAccountId) {
		return dao.find(new DataItem(IncomeSituation.class, "findBySubAccountId"), subAccountId);
	}

	@Override
	public IncomeNowSituationDto findSituationByDate(Map<String, Object> map) {
		return dao.find(new DataItem(IncomeSituation.class, "findSituationByDate"), map);
	}

}
