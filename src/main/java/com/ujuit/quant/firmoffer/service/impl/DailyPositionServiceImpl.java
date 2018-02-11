package com.ujuit.quant.firmoffer.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.ujuit.quant.firmoffer.dto.ChartGradeDto;
import com.ujuit.quant.firmoffer.dto.ChartNowDateDto;
import com.ujuit.quant.firmoffer.dto.ChartPointDto;
import com.ujuit.quant.firmoffer.dto.DailyPositionByPageDto;
import com.ujuit.quant.firmoffer.dto.DailyPositionDto;
import com.ujuit.quant.firmoffer.dto.MixPositionDto;
import com.ujuit.quant.firmoffer.dto.TransactionDto;
import com.ujuit.quant.firmoffer.dto.gradeDto;
import com.ujuit.quant.firmoffer.model.DailyAssets;
import com.ujuit.quant.firmoffer.model.DailyPosition;
import com.ujuit.quant.firmoffer.model.TMinIncome;
import com.ujuit.quant.firmoffer.model.TSubAccount;
import com.ujuit.quant.firmoffer.model.TTransactionDetail;
import com.ujuit.quant.firmoffer.service.DailyPositionService;
import com.ujuit.quant.utils.BusinessUtil;
import com.ujuit.quant.utils.DataUtils;
import com.ujuit.quant.utils.PageModel;
import com.ujuit.quant.utils.StockUtil;
import com.ujuit.quantcalation.api.service.CalculationApiService;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;
import com.ujuit.sysmanager.core.mybatis.DataResult;

/**
 * @author shadow
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DailyPositionServiceImpl implements DailyPositionService {
	@Resource
	Dao dao;
	@Resource
	CalculationApiService calculationapiService;

	@Override
	public Integer insert(DailyPosition dailyPosition) {
		return dao.add(new DataItem(DailyPosition.class, "insert"), dailyPosition).getId();
	}

	/**
	 * 每日持仓
	 */
	@Override
	public DailyPositionByPageDto findDailyList(String groupId, String strategyId, String testId, Long startTime,
			Long endTime, Integer pageSize, Integer pageNum) {
		DailyPositionByPageDto pagedto = new DailyPositionByPageDto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("groupId", groupId);
		if (startTime != null) {
			map.put("startTime", new Date(startTime));
		}
		if (endTime != null) {
			map.put("endTime", new Date(endTime));
		}
		if (groupId == null || "".equals(groupId)) {
			map.put("status", "1");
		}
		DataResult<DailyPositionDto> rs = dao.getData(new DataItem(DailyPosition.class, "DailyPositionDto"), map,
				pageNum, 50);// 金松需求修改
		List<DailyPositionDto> list = rs.getRecords();
		if (list != null && list.size() > 0) {
			for (DailyPositionDto dto : list) {
				map.put("thedate", sdf.format(dto.getTime()));
				map.put("beginDate", sdf.format(dto.getTime()));
				map.put("endDate", sdf.format(dto.getTime()));
				BigDecimal positionAssets = new BigDecimal(0);
				BigDecimal totalRevenue = new BigDecimal(0);
				List<DailyPosition> dplist = dto.getDpList();

				for (DailyPosition dailyPosition : dplist) {
					positionAssets = positionAssets.add(dailyPosition.getPositionPrice());
					totalRevenue = totalRevenue.add(dailyPosition.getProfit());
				}
				DailyAssets account = dao.find(new DataItem(DailyAssets.class, "findDailyAssetsByGroupId"), map);
				List<TTransactionDetail> ttrlist = dao
						.getDataList(new DataItem(TTransactionDetail.class, "findTransactionList"), map);
				BigDecimal allCost = new BigDecimal(0);
				if (ttrlist != null && ttrlist.size() > 0) {
					for (TTransactionDetail ttr : ttrlist) {
						allCost = allCost.add(ttr.getCost());
					}
					dto.setTransNum(ttrlist.size());
				}
				dto.setAllCost(DataUtils.formateFour(allCost));
				dto.setTotalRevenue(DataUtils.formateFour(totalRevenue));// 计算总收益
				dto.setPositionAssets(DataUtils.formateFour(positionAssets));// 持仓金额
				dto.setDpList(dplist);
				if (account != null) {
					dto.setTotalAssets(DataUtils.formateFour(account.getMoney()));// 总资产
					dto.setUsableAssets(DataUtils.formateFour(account.getMoney().subtract(positionAssets)));
				}
			}
		}
		pagedto.setList(list);
		pagedto.setTotalPage(rs.getTotalPage());
		return pagedto;
	}

	@Override
	public List<DailyPosition> findThedateList() {
		String thedate = "2017-04-21";
		return dao.getDataList(new DataItem(DailyPosition.class, "findDpList"), thedate);
	}

	@Override
	public List<DailyPositionDto> findMyDaily(String strategyId, String testId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		List<DailyPositionDto> list = new ArrayList<>();
		DailyPositionDto dto = new DailyPositionDto();
		Hashtable<String, BigDecimal> hashtable = StockUtil.get();// 获取hash值
		List<DailyPosition> dplist = dao.getDataList(new DataItem(DailyPosition.class, "findMyDaily"), map);

		Map<String, BigDecimal> param = new HashMap<String, BigDecimal>();
		for (DailyPosition position : dplist) {
			param.put(position.getCode(), null);
			if (hashtable != null) {
				position.setNowPrice(hashtable.get(position.getCode()));
			}
		}
		dto.setDpList(dplist);
		list.add(dto);
		return list;
	}

	@Override
	public List<DailyPosition> findNowDaily(String groupId, String strategyId, String testId, Long startTime,
			Long endTime) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		List<DailyPosition> list = dao.getDataList(new DataItem(DailyPosition.class, "findNowDaily"), map);
		return list;
	}

	/**
	 * @author shandowF
	 * @date 2017年12月29日
	 * 
	 */
	@Override
	public List<DailyPosition> queryActualDailyList(Integer strategyId, Integer testId) {
		List<DailyPosition> dplist = this.findNowDaily(null, strategyId.toString(), testId.toString(), null, null);
		Hashtable<String, BigDecimal> hashtable = StockUtil.get();
		// BigDecimal dailyMoney = new BigDecimal(0);// 持仓金额
		Iterator<DailyPosition> it = dplist.iterator();
		while (it.hasNext()) {
			DailyPosition dp = it.next();
			if (new BigDecimal(0).compareTo(dp.getPositionNum()) == 0) {
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
				try {
					BigDecimal bfPositionPrice = dp.getPositionPrice();
					dp.setProfit(dp.getPositionNum().multiply(nowPrice).subtract(bfPositionPrice).setScale(2,
							BigDecimal.ROUND_HALF_DOWN));
					dp.setPositionPrice(dp.getPositionNum().multiply(nowPrice));
					dp.setProfitScale(dp.getNowPrice().subtract(dp.getTranPrice())
							.divide(dp.getTranPrice(), 2, RoundingMode.HALF_UP).toString());
					// dailyMoney =
					// dailyMoney.add(dp.getPositionNum().multiply(dp.getNowPrice()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return dplist;
	}

	@Override
	public DailyPositionByPageDto findNowDailyList(String groupId, String strategyId, String testId, Long startTime,
			Long endTime, Integer pageSize, Integer pageNum) {
		DailyPositionByPageDto pagedto = new DailyPositionByPageDto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat thesdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("groupId", groupId);
		if (startTime != null) {
			map.put("startTime", new Date(startTime));
			map.put("beginDate", new Date(startTime));
		}
		try {
			if (endTime != null) {
				map.put("endTime", thesdf.parse(sdf.format(new Date(endTime)) + " 23:59:59"));
				map.put("endDate", new Date(endTime));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<DailyPositionDto> list = dao.getDataList(new DataItem(DailyPosition.class, "findNowDailyList"), map);
		// 这个地方做分页处理
		PageModel pm = new PageModel(list, pageSize);// 分页大小
		list = pm.getObjects(pageNum);
		for (DailyPositionDto dto : list) {
			map.put("thedate", sdf.format(dto.getTime()));
			BigDecimal positionAssets = new BigDecimal(0);
			BigDecimal totalRevenue = new BigDecimal(0);
			List<DailyPosition> dplist = dto.getDpList();
			for (DailyPosition dailyPosition : dplist) {
				positionAssets = positionAssets.add(dailyPosition.getPositionPrice());
				totalRevenue = totalRevenue.add(dailyPosition.getProfit());
			}
			DailyAssets account = dao.find(new DataItem(DailyAssets.class, "findDailyAssetsByTestId"), map);
			map.put("beginDate", dto.getTime());
			map.put("endDate", dto.getTime());
			// 查询交易记录
			List<TTransactionDetail> ttrlist = dao
					.getDataList(new DataItem(TTransactionDetail.class, "findNowTransactionList"), map);
			BigDecimal allCost = new BigDecimal(0);
			if (ttrlist != null && ttrlist.size() > 0) {
				for (TTransactionDetail ttr : ttrlist) {
					if (ttr.getCost() != null) {
						allCost = allCost.add(ttr.getCost());
					}
				}
				dto.setTransNum(ttrlist.size());
			}
			dto.setAllCost(DataUtils.formateFour(allCost));
			dto.setTotalRevenue(DataUtils.formateFour(totalRevenue));// 计算总收益
			dto.setPositionAssets(DataUtils.formateFour(positionAssets));// 持仓金额
			dto.setDpList(dplist);
			if (account != null) {
				dto.setTotalAssets(DataUtils.formateFour(account.getMoney()));// 总资产
				dto.setUsableAssets(DataUtils.formateFour(account.getMoney().subtract(positionAssets)));// 可用资产查询上一天的资产
			}
		}
		pagedto.setTotalPage(pm.getTotalPages());
		pagedto.setList(list);
		return pagedto;
	}

	@Override
	public ChartGradeDto queryGradeDtoList(String testId, String strategyId, Long startTime, Long endTime) {
		Map<String, Object> map = new HashMap<>();
		if (startTime != null) {
			map.put("startTime", new Date(startTime));
		}
		if (endTime != null) {
			map.put("endTime", new Date(endTime));
		}
		map.put("testId", testId);
		map.put("strategyId", strategyId);
		List<gradeDto> list = dao.getDataList(new DataItem(DailyPosition.class, "queryGradeDtoList"), map);

		ChartGradeDto dto = new ChartGradeDto();

		ChartPointDto grade = new ChartPointDto();// 累计收益
		if (list != null && list.size() > 0) {
			List<Date> times = new ArrayList<>();
			List<BigDecimal> gradevalues = new ArrayList<>();

			for (gradeDto gradeDto : list) {
				times.add(gradeDto.getGradeTime());
				gradevalues.add(gradeDto.getGrade());
			}
			grade.setTimes(times);
			grade.setValues(gradevalues);
		}
		dto.setGradeDto(grade);
		return dto;
	}

	@Override
	public List<MixPositionDto> queryMixSpace(String testId, String strategyId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat thesdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = new HashMap<>();
		map.put("testId", testId);
		map.put("strategyId", strategyId);
		List<MixPositionDto> mxList = dao.getDataList(new DataItem(DailyPosition.class, "queryMixSpace"), map);
		TMinIncome tminincome = dao.find(new DataItem(TMinIncome.class, "findTMinMoeny"), map);// 获取实盘最新资产
		Hashtable<String, BigDecimal> hashtable = StockUtil.get();// 获取hash值
		TSubAccount afday = dao.find(new DataItem(DailyAssets.class, "findTSubAccount"), map);
		BigDecimal afdayAllMoney = null;// 获取前一天总资产
		// 这个地方缓存 每天的资产
		List<DailyAssets> daList = dao.getDataList(new DataItem(DailyAssets.class, "allDaList"), map);
		Map<String, BigDecimal> daMap = new HashMap<String, BigDecimal>();
		for (DailyAssets da : daList) {
			daMap.put(sdf.format(da.getTime()), da.getMoney());
		}
		for (MixPositionDto dto : mxList) {// 这个地方应该除以当天的总资产
			if (daMap.get(dto.getDealDate()) != null) {
				dto.setAfMixPosition(dto.getDealPrice().multiply(new BigDecimal(dto.getTradeNum()))
						.divide(daMap.get(dto.getDealDate()), 2, RoundingMode.HALF_UP));// 现在仓位
				dto.setBfMixPosition(dto.getDealPrice().multiply(new BigDecimal(dto.getDealNum()))
						.divide(daMap.get(dto.getDealDate()), 2, RoundingMode.HALF_UP));// 变化仓位
			} else {
				dto.setBfMixPosition(new BigDecimal(0));
			}
		}
		return mxList;
	}

	@Override
	public List<DailyPositionDto> findNowDailyListAll(String groupId, String strategyId, String testId, Long startTime,
			Long endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat thesdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("groupId", groupId);
		if (startTime != null) {
			map.put("startTime", new Date(startTime));
			map.put("beginDate", new Date(startTime));
		}
		try {
			if (endTime != null) {
				map.put("endTime", thesdf.parse(sdf.format(new Date(endTime)) + " 23:59:59"));
				map.put("endDate", new Date(endTime));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<DailyPositionDto> list = dao.getDataList(new DataItem(DailyPosition.class, "findNowDailyList"), map);
		// // 这个地方做分页处理
		// PageModel pm = new PageModel(list, pageSize);// 分页大小
		// list = pm.getObjects(pageNum);
		for (DailyPositionDto dto : list) {
			map.put("thedate", sdf.format(dto.getTime()));
			BigDecimal positionAssets = new BigDecimal(0);
			BigDecimal totalRevenue = new BigDecimal(0);
			List<DailyPosition> dplist = dto.getDpList();
			for (DailyPosition dailyPosition : dplist) {
				positionAssets = positionAssets.add(dailyPosition.getPositionPrice());
				totalRevenue = totalRevenue.add(dailyPosition.getProfit());
			}
			DailyAssets account = dao.find(new DataItem(DailyAssets.class, "findDailyAssetsByTestId"), map);
			map.put("beginDate", dto.getTime());
			map.put("endDate", dto.getTime());
			// 查询交易记录
			List<TTransactionDetail> ttrlist = dao
					.getDataList(new DataItem(TTransactionDetail.class, "findNowTransactionList"), map);
			BigDecimal allCost = new BigDecimal(0);
			if (ttrlist != null && ttrlist.size() > 0) {
				for (TTransactionDetail ttr : ttrlist) {
					if (ttr.getCost() != null) {
						allCost = allCost.add(ttr.getCost());
					}
				}
				dto.setTransNum(ttrlist.size());
			}
			dto.setAllCost(DataUtils.formateFour(allCost));
			dto.setTotalRevenue(DataUtils.formateFour(totalRevenue));// 计算总收益
			dto.setPositionAssets(DataUtils.formateFour(positionAssets));// 持仓金额
			dto.setDpList(dplist);
			if (account != null) {
				dto.setTotalAssets(DataUtils.formateFour(account.getMoney()));// 总资产
				dto.setUsableAssets(DataUtils.formateFour(account.getMoney().subtract(positionAssets)));// 可用资产查询上一天的资产
			}
		}
		// pagedto.setTotalPage(pm.getTotalPages());
		// pagedto.setList(list);
		return list;
	}

	@Override
	public List<DailyPositionDto> findDailyListAll(String groupId, String strategyId, String testId, Long startTime,
			Long endTime) {
		// DailyPositionByPageDto pagedto = new DailyPositionByPageDto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("groupId", groupId);
		if (startTime != null) {
			map.put("startTime", new Date(startTime));
		}
		if (endTime != null) {
			map.put("endTime", new Date(endTime));
		}
		if (groupId == null || "".equals(groupId)) {
			map.put("status", "1");
		}
		List<DailyPositionDto> list = dao.getDataList(new DataItem(DailyPosition.class, "DailyPositionDto"), map);// 金松需求修改
		// List<DailyPositionDto> list = rs.getRecords();
		if (list != null && list.size() > 0) {
			for (DailyPositionDto dto : list) {
				map.put("thedate", sdf.format(dto.getTime()));
				map.put("beginDate", sdf.format(dto.getTime()));
				map.put("endDate", sdf.format(dto.getTime()));
				BigDecimal positionAssets = new BigDecimal(0);
				BigDecimal totalRevenue = new BigDecimal(0);
				List<DailyPosition> dplist = dto.getDpList();

				for (DailyPosition dailyPosition : dplist) {
					positionAssets = positionAssets.add(dailyPosition.getPositionPrice());
					totalRevenue = totalRevenue.add(dailyPosition.getProfit());
				}
				DailyAssets account = dao.find(new DataItem(DailyAssets.class, "findDailyAssetsByGroupId"), map);
				List<TTransactionDetail> ttrlist = dao
						.getDataList(new DataItem(TTransactionDetail.class, "findTransactionList"), map);
				BigDecimal allCost = new BigDecimal(0);
				if (ttrlist != null && ttrlist.size() > 0) {
					for (TTransactionDetail ttr : ttrlist) {
						allCost = allCost.add(ttr.getCost());
					}
					dto.setTransNum(ttrlist.size());
				}
				dto.setAllCost(DataUtils.formateFour(allCost));
				dto.setTotalRevenue(DataUtils.formateFour(totalRevenue));// 计算总收益
				dto.setPositionAssets(DataUtils.formateFour(positionAssets));// 持仓金额
				dto.setDpList(dplist);
				if (account != null) {
					dto.setTotalAssets(DataUtils.formateFour(account.getMoney()));// 总资产
					dto.setUsableAssets(DataUtils.formateFour(account.getMoney().subtract(positionAssets)));
				}
			}
		}
		// pagedto.setList(list);
		// pagedto.setTotalPage(rs.getTotalPage());
		return list;
	}

	/**
	 * @author shandowF
	 * @date 2017年12月29日
	 * 
	 */
	@Override
	public Map<String, Object> queryAssetInfo(Integer testId, Integer strategyId) {
		// 资产总量（剩余资金+总持仓价值），剩余资金，当日购买股票所用资金，当日卖出股票所用资金，当日产生的手续费
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		param.put("testId", testId);
		param.put("strategyId", strategyId);
		// 查询我的可用资金
		TSubAccount account = dao.find(new DataItem(DailyAssets.class, "findTSubAccount"), param);
		if (account == null) {
			return null;
		}
		BigDecimal dailyMoney = new BigDecimal(0);// 持仓金额
		List<DailyPosition> dplist = this.findNowDaily(null, strategyId.toString(), testId.toString(), null, null);
		Hashtable<String, BigDecimal> hashtable = StockUtil.get();
		Iterator<DailyPosition> it = dplist.iterator();
		while (it.hasNext()) {
			DailyPosition dp = it.next();
			if (new BigDecimal(0).compareTo(dp.getPositionNum()) == 0) {
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
				try {
					// dp.setProfit(dp.getPositionNum().multiply(nowPrice).subtract(dp.getPositionPrice()).setScale(2,
					// BigDecimal.ROUND_HALF_DOWN));
					dailyMoney = dailyMoney.add(dp.getPositionNum().multiply(dp.getNowPrice()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// 我的交易记录
		SimpleDateFormat thesdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date thedate = new Date();
		try {
			param.put("startTime", thesdf.parse(sdf.format(thedate) + " 00:00:00"));
			param.put("endTime", thesdf.parse(sdf.format(thedate) + " 23:59:59"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<TTransactionDetail> ttrList = dao.getDataList(new DataItem(TTransactionDetail.class, "findNowTtrDetail"),
				param);
		// 当日购买股票所有资金，当日卖出股票所有资金，当日产生的手续费
		BigDecimal sale = new BigDecimal(0);
		BigDecimal buy = new BigDecimal(0);
		BigDecimal cost = new BigDecimal(0);
		if (ttrList != null && ttrList.size() > 0) {
			for (TTransactionDetail detail : ttrList) {
				if ("1".equals(detail.getTradingDirection())) {
					buy = buy.add(detail.getAmount());
				} else if ("2".equals(detail.getTradingDirection())) {
					sale = sale.add(detail.getAmount());
				}
				if (detail.getCost() != null) {
					cost = cost.add(detail.getCost());
				}
			}
		}
		map.put("sale", sale);// 卖出金额
		map.put("buying", buy);// 买入金额
		map.put("cost", cost);// 交易费用
		BigDecimal surplusmoney = account.getMoneyTotal();
		map.put("totalAssets", BusinessUtil.AddMoney(dailyMoney, surplusmoney));// 资产总量
		map.put("dailyMoney", dailyMoney);// 持仓金额
		map.put("surplusMoney", surplusmoney);// 设置可用资金
		return map;
	}

}
