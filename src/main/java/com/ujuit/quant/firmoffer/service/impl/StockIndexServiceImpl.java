package com.ujuit.quant.firmoffer.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ujuit.dealserver.constant.ApplayType;
import com.ujuit.dealserver.service.StockIndexService;
import com.ujuit.dealservice.entity.StockApply;
import com.ujuit.quant.firmoffer.model.Dailytarget;
import com.ujuit.quant.firmoffer.model.TTransactionDetail;
import com.ujuit.quant.firmoffer.service.DailytargetService;
import com.ujuit.quant.utils.BusinessUtil;
import com.ujuit.quant.utils.DateUtils;
import com.ujuit.quant.utils.StockUtil;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;

/**
 * @author shadow
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class StockIndexServiceImpl implements StockIndexService {
	@Resource
	Dao dao;
	@Resource
	DailytargetService dailytargetService;

	@Override
	public boolean calculateStockIndex(Integer subAccountId) {
		Dailytarget dailytarget = new Dailytarget();
		dailytarget.setTime(DateUtils.TODAY);
		dailytarget.setSubAccountId(subAccountId);

		// 先查询子账户开仓单
		List<StockApply> listStockApplys = queryStockApplyList(subAccountId, ApplayType.BUY);
		dailytarget = calculateNum(listStockApplys, dailytarget);

		Integer key = dailytargetService.insert(dailytarget);

		if (key != null && key > 0) {
			return true;
		}
		return false;
	}

	private Dailytarget calculateNum(List<StockApply> listStockApplys, Dailytarget dailytarget) {
		dailytarget = fixDailytarget(dailytarget);
		Hashtable<String, BigDecimal> hashtable = StockUtil.get();
		// 遍历开仓单
		for (StockApply stockapply : listStockApplys) {
			// System.out.println("appid=" + stockapply.getId());
			// 成交总价
			BigDecimal totalAllMoney = stockapply.getTotalDealMoney();
			// 成交总数量
			Integer totalAllNum = stockapply.getTotalDealNum();
			// 开仓单手续费
			BigDecimal cost = stockapply.getCost();

			totalAllMoney = totalAllMoney.add(cost);// 成交总价加上手续费

			// // 平均开仓价格
			// BigDecimal dealAvgMoney = BusinessUtil.divide(totalAllMoney, new
			// BigDecimal(totalAllNum));

			Integer applyId = stockapply.getId();

			// 根据开仓单反找平仓单
			List<StockApply> orderRelationList = queryOrderRelation(applyId);

			// 循环获取卖出单的数量
			Integer allDealNum = 0;
			// 卖出总价格
			BigDecimal allDealMoney = BigDecimal.ZERO;
			BigDecimal dealCost = BigDecimal.ZERO;
			if (orderRelationList != null && orderRelationList.size() > 0) {
				for (StockApply apply : orderRelationList) {
					Integer dealNum = 0;
					if (apply.getDealNum() != null) {
						dealNum = apply.getDealNum();
						allDealNum += dealNum;
					}
					allDealMoney = allDealMoney.add(BusinessUtil.mulMoney(apply.getDealPrice(), dealNum));
					if (apply.getCost() != null) {
						dealCost = dealCost.add(apply.getCost());
					}
				}
				// 判断是否全部成交
				if (totalAllNum.intValue() == allDealNum.intValue()) {
					dailytarget = allDeal(dailytarget, totalAllMoney, allDealMoney);
				} else {
					// 部分成交
					Integer noDealNum = totalAllNum - allDealNum;
					dailytarget = saleSome(dailytarget, allDealMoney, noDealNum, stockapply, hashtable);
				}
			} else {// 没有卖出
				dailytarget = notSale(dailytarget, stockapply, hashtable,totalAllMoney);
			}
		}

		return dailytarget;
	}

	/**
	 * 部分成交
	 * 
	 * @author shandowF
	 * @date 2018年2月7日
	 */
	private Dailytarget saleSome(Dailytarget dailytarget, BigDecimal allDealMoney, Integer noDealNum,
			StockApply stockapply, Hashtable<String, BigDecimal> hashtable) {
		BigDecimal totalAllMoney = BusinessUtil.AddMoney(stockapply.getTotalDealMoney(), stockapply.getCost());
		BigDecimal positionMoney = allDealMoney;
		BigDecimal nowPrice = hashtable.get(stockapply.getStockCode());
		if (nowPrice == null) {
			System.err.println("股票价格为空:stockCode=" + stockapply.getStockCode());
		}
		positionMoney = BusinessUtil.AddMoney(positionMoney, BusinessUtil.mulMoney(nowPrice, noDealNum));
		if (positionMoney.compareTo(totalAllMoney) == 1) {
			dailytarget.setProfitNum(dailytarget.getProfitNum() + 1);
		} else {
			dailytarget.setLossNum(dailytarget.getLossNum() + 1);
		}
		return dailytarget;
	}

	/**
	 * 还未卖出
	 * 
	 * @author shandowF
	 * @date 2018年2月7日
	 */
	private Dailytarget notSale(Dailytarget dailytarget, StockApply stockapply,
			Hashtable<String, BigDecimal> hashtable,BigDecimal totalAllMoney) {
		BigDecimal positionMoney = BigDecimal.ZERO;
		Integer totalNum = stockapply.getTotalDealNum();
		BigDecimal nowPrice = hashtable.get(stockapply.getStockCode());
		if (nowPrice == null) {
			System.err.println("股票价格为空:stockCode=" + stockapply.getStockCode());
		}
		positionMoney = BusinessUtil.mulMoney(nowPrice, totalNum);
		// 盈利
		if (positionMoney.compareTo(totalAllMoney) == 1) {
			dailytarget.setProfitNum(dailytarget.getProfitNum() + 1);
		} else {
			dailytarget.setLossNum(dailytarget.getLossNum() + 1);
		}
		return dailytarget;
	}

	/**
	 * 全部成交 totalAllMoney 成本价 allDealMoney 卖出价格
	 * 
	 * @author shandowF
	 * @date 2018年2月7日
	 */
	private Dailytarget allDeal(Dailytarget dailytarget, BigDecimal totalAllMoney, BigDecimal allDealMoney) {
		if (allDealMoney.compareTo(totalAllMoney) == 1) {
			dailytarget.setProfitNum(dailytarget.getProfitNum() + 1);// 盈利
			dailytarget.setSaleProfitNum(dailytarget.getSaleProfitNum() + 1);// 卖出盈利总次数
			BigDecimal profit = allDealMoney.subtract(totalAllMoney);
			dailytarget.setProfitTotal(dailytarget.getProfitTotal().add(profit));
			BigDecimal maxProfit = BusinessUtil.divide(profit, totalAllMoney);
			if (maxProfit.compareTo(dailytarget.getMaxLoss()) == 1) {// 所有成交单里，盈利幅度最大，百分比
				dailytarget.setMaxProfit(maxProfit);
			}
		} else {
			dailytarget.setLossNum(dailytarget.getLossNum() + 1);// 亏损
			BigDecimal loss = totalAllMoney.subtract(allDealMoney);
			dailytarget.setLossTotal(dailytarget.getLossTotal().add(loss));
			BigDecimal maxLoss = BusinessUtil.divide(loss, totalAllMoney);
			if (maxLoss.compareTo(dailytarget.getMaxLoss()) == 1) {// 所有成交单里，盈利幅度最大，百分比
				dailytarget.setMaxLoss(maxLoss);
			}
		}
		dailytarget.setSaleNum(dailytarget.getSaleNum() + 1);
		return dailytarget;
	}

	/**
	 * 初始化组装
	 * 
	 * @author shandowF
	 * @date 2018年2月7日
	 */
	private Dailytarget fixDailytarget(Dailytarget dailytarget) {
		// 盈利总次数
		dailytarget.setProfitNum(0);
		// 亏损总次数
		dailytarget.setLossNum(0);
		// 总盈利
		dailytarget.setProfitTotal(BigDecimal.ZERO);
		// 总亏损
		dailytarget.setLossTotal(BigDecimal.ZERO);
		// 卖出总数
		dailytarget.setSaleNum(0);
		// 卖出盈利总次数
		dailytarget.setSaleProfitNum(0);
		// 最大单次盈利
		dailytarget.setMaxProfit(BigDecimal.ZERO);
		// 最大单次亏损
		dailytarget.setMaxLoss(BigDecimal.ZERO);
		return dailytarget;
	}

	@Override
	public List<StockApply> queryStockApplyList(Integer subAccountId, ApplayType applayType) {
		Map<String, Object> map = new HashMap<>();
		map.put("subAccountId", subAccountId);
		if (applayType != null) {
			map.put("applayType", applayType.getValue());
		}
		return dao.getDataList(new DataItem(TTransactionDetail.class, "queryStockApplyList"), map);
	}

	@Override
	public List<StockApply> queryOrderRelation(Integer applyId) {
		Map<String, Object> map = new HashMap<>();
		map.put("applyId", applyId);
		return dao.getDataList(new DataItem(TTransactionDetail.class, "queryOrderRelation"), map);
	}

	@Override
	public StockApply queryStockApplyById(Integer id) {
		return dao.find(new DataItem(TTransactionDetail.class, "queryStockApplyById"), id);
	}
}
