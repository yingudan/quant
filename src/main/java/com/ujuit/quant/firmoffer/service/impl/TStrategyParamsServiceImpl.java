package com.ujuit.quant.firmoffer.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ujuit.dealserver.constant.ApplayType;
import com.ujuit.quant.firmoffer.constant.RecordType;
import com.ujuit.quant.firmoffer.dto.TestIndexDto;
import com.ujuit.quant.firmoffer.model.Dailytarget;
import com.ujuit.quant.firmoffer.model.TStrategy;
import com.ujuit.quant.firmoffer.model.TStrategyParams;
import com.ujuit.quant.firmoffer.model.TTestRecord;
import com.ujuit.quant.firmoffer.service.DailytargetService;
import com.ujuit.quant.firmoffer.service.TStrategyParamsService;
import com.ujuit.quant.firmoffer.service.TTestRecordService;
import com.ujuit.quant.utils.BusinessUtil;
import com.ujuit.quant.utils.DateUtils;
import com.ujuit.quantcalation.api.service.CalculationApiService;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TStrategyParamsServiceImpl implements TStrategyParamsService {

	private static final Logger log = LoggerFactory.getLogger(TStrategyParamsServiceImpl.class);

	@Resource
	Dao dao;

	@Resource
	TTestRecordService ttestrecordService;

	@Resource
	CalculationApiService calculationapiService;

	@Resource
	DailytargetService dailytargetService;


	@Override
	public List<TStrategyParams> findParams(String strategyId,String testId) {
//		Map<String,Object> map = new HashMap<>();
//		map.put("strategyId", strategyId);
//		map.put("testId", testId);
		List<TStrategyParams> list =dao.getDataList(new DataItem(TStrategyParams.class, "findParams"), strategyId);
		
		TTestRecord test =ttestrecordService.queryTestRecordById(testId);
		if(test!=null){
			list.get(0).setInFunding(test.getIniFunding());
		}
		return list;
	}

	@Override
	public TStrategy queryTsByGroupId(Integer groupId) {
		return dao.find(new DataItem(TStrategyParams.class, "queryTsByGroupId"), groupId);
	}

	@Override
	public Integer queryGroupIdByTestId(Integer testId) {
		return dao.find(new DataItem(TStrategyParams.class, "queryGroupIdByTestId"), testId);
	}

	@Override
	public TestIndexDto queryIndexDtoByTestId(Integer testId) {
		TestIndexDto dto = new TestIndexDto();
		if (testId == null) {
			return dto;
		}
		TTestRecord test = ttestrecordService.queryTestRecordById(String.valueOf(testId));
		if (test == null) {
			log.error("测试记录不存在testId=" + testId);
			return dto;
		}
		// 交易日总天数
		Integer tradeDay = calculationapiService.transactionCount(getStartDay(test), getEndDay(test));
		dto.setTradeDay(tradeDay);
		// 子账户id
		Integer subAccountId = queryGroupIdByTestId(testId);
		Map<String, Object> map = new HashMap<>();
		map.put("subAccountId", subAccountId);
		map.put("applayType", ApplayType.BUY.getValue());

		// 交易总次数
		Integer tradeAllNum = dao.find(new DataItem(TStrategyParams.class, "queryTradeNum"), map);
		dto.setTradeAllNum(tradeAllNum);

		// 平均交易间隔 交易日总天数除以交易总次数
		BigDecimal timeGap = BusinessUtil.divide(new BigDecimal(tradeDay), new BigDecimal(tradeAllNum));
		dto.setTimeGap(timeGap);

		Dailytarget dailytarget = dao.find(new DataItem(Dailytarget.class, "findBySubAccountId"), subAccountId);
		setIndex(dto, dailytarget);

		return dto;
	}

	/**
	 * 设置盈亏比等
	 * 
	 * @author shandowF
	 * @date 2018年2月7日
	 */
	private void setIndex(TestIndexDto dto, Dailytarget dailytarget) {
		if (dailytarget != null) {
			Integer profitNum = dailytarget.getProfitNum();
			dto.setProfitNum(profitNum);
			Integer lossNum = dailytarget.getLossNum();
			dto.setLossNum(lossNum);
			BigDecimal tradeAllNum = new BigDecimal(dto.getTradeAllNum());
			BigDecimal profitPr = BusinessUtil.divide(new BigDecimal(profitNum), tradeAllNum);
			dto.setProfitPr(profitPr);
			BigDecimal lossPr = BusinessUtil.divide(new BigDecimal(lossNum), tradeAllNum);
			dto.setLossPr(lossPr);

			BigDecimal avgProfit = BusinessUtil.divide(dailytarget.getProfitTotal(), new BigDecimal(profitNum));
			dto.setAvgProfit(avgProfit);

			BigDecimal avgLoss = BusinessUtil.divide(dailytarget.getLossTotal(), new BigDecimal(lossNum));
			dto.setAvgLoss(avgLoss);

			dto.setMaxProfit(dailytarget.getMaxProfit());
			dto.setMaxLoss(dailytarget.getMaxLoss());

			// 盈利总次数
			dto.setSaleProfitNum(dailytarget.getSaleProfitNum());

			dto.setSaleAllNum(dailytarget.getSaleNum());

			dto.setWinRate(BusinessUtil.divideInt(dailytarget.getSaleProfitNum(), dailytarget.getSaleNum()));

			dto.setProfitAndLossScale(BusinessUtil.divide(avgProfit, avgLoss));
		} else {
			dto.setProfitNum(0);
			dto.setLossNum(0);
			dto.setProfitPr(BigDecimal.ZERO);
			dto.setLossPr(BigDecimal.ZERO);
			dto.setAvgProfit(BigDecimal.ZERO);
			dto.setAvgLoss(BigDecimal.ZERO);
			dto.setMaxProfit(BigDecimal.ZERO);
			dto.setMaxLoss(BigDecimal.ZERO);
			dto.setWinRate(BigDecimal.ZERO);
			dto.setProfitAndLossScale(BigDecimal.ZERO);
		}
	}

	/**
	 * 交易日总天数
	 */
	@Override
	public Integer queryStrategyDay(Integer testId) {
		if (testId == null) {
			return 0;
		}
		TTestRecord test = ttestrecordService.queryTestRecordById(String.valueOf(testId));
		if (test == null) {
			log.error("测试记录不存在testId=" + testId);
			return 0;
		}
		return calculationapiService.transactionCount(getStartDay(test), getEndDay(test));
	}

	/**
	 * 交易总次数
	 */
	@Override
	public Integer queryTradeNum(Integer testId, ApplayType applayType) {
		if (testId == null) {
			return 0;
		}
		TTestRecord test = ttestrecordService.queryTestRecordById(String.valueOf(testId));
		if (test == null) {
			log.error("测试记录不存在testId=" + testId);
			return 0;
		}
		// Date startDay = getStartDay(test);
		// Date endDay = getEndDay(test);
		Integer subAccountId = queryGroupIdByTestId(testId);
		Map<String, Object> map = new HashMap<>();
		// map.put("startDay", startDay);
		// map.put("endDay", endDay);
		map.put("subAccountId", subAccountId);
		map.put("applayType", applayType.getValue());
		return dao.find(new DataItem(TStrategyParams.class, "queryTradeNum"), map);
	}

	/**
	 * 获取策略的开始时间
	 * 
	 * @author shandowF
	 * @date 2018年2月2日
	 */
	private Date getStartDay(TTestRecord test) {
		String testType = test.getType();
		if (RecordType.hisTest.getValue().equals(testType)) {
			return test.getHisDataStart();
		}
		if (RecordType.nowTest.getValue().equals(testType)) {
			return test.getTestTime();
		}
		return null;
	}

	/**
	 * 获取策略的结束时间
	 * 
	 * @author shandowF
	 * @date 2018年2月2日
	 */
	private Date getEndDay(TTestRecord test) {
		String testType = test.getType();
		if (RecordType.hisTest.getValue().equals(testType)) {
			return test.getHisDataEnd();
		}
		if (RecordType.nowTest.getValue().equals(testType)) {
			return DateUtils.TODAY;
		}
		return null;
	}

	/**
	 * 交易日总天数除以交易总次数；
	 */
	@Override
	public BigDecimal queryTimeGap(Integer testId) {
		Integer allDay = queryStrategyDay(testId);
		// 所有开仓单
		Integer tradNum = queryTradeNum(testId, ApplayType.BUY);
		if (allDay == null || allDay == 0) {
			return BigDecimal.ZERO;
		}
		if (tradNum == null || tradNum == 0) {
			return BigDecimal.ZERO;
		}
		BigDecimal allDaybd = new BigDecimal(allDay);
		BigDecimal tradNumbd = new BigDecimal(tradNum);
		return allDaybd.divide(tradNumbd, 4, BigDecimal.ROUND_HALF_DOWN);
	}

	@Override
	public Integer queryProfitNum(Integer testId) {
		Integer subAcountId = queryGroupIdByTestId(testId);
		return dailytargetService.queryProfitNum(subAcountId);
	}

	@Override
	public Integer querylossNum(Integer testId) {
		Integer subAcountId = queryGroupIdByTestId(testId);
		return dailytargetService.querylossNum(subAcountId);
	}

	@Override
	public BigDecimal queryProfitAndLossScale(Integer testId) {
		return null;
	}

	/**
	 * @author shandowF
	 * @date 2018年2月6日
	 * 
	 */
	@Override
	public BigDecimal queryAvgProfit(Integer testId) {
		Integer subAccountId = queryGroupIdByTestId(testId);
		Dailytarget dailytarget = dao.find(new DataItem(Dailytarget.class, "findBySubAccountId"), subAccountId);
		if (dailytarget != null) {
			BigDecimal profit = dailytarget.getProfitTotal();
			BigDecimal num = new BigDecimal(dailytarget.getProfitNum());
			return BusinessUtil.divide(profit, num);
		}
		return BigDecimal.ZERO;
	}

	/**
	 * @author shandowF
	 * @date 2018年2月6日
	 * 
	 */
	@Override
	public BigDecimal queryAvgLoss(Integer testId) {
		Integer subAccountId = queryGroupIdByTestId(testId);
		Dailytarget dailytarget = dao.find(new DataItem(Dailytarget.class, "findBySubAccountId"), subAccountId);
		if (dailytarget != null) {
			BigDecimal profit = dailytarget.getLossTotal();
			BigDecimal num = new BigDecimal(dailytarget.getLossNum());
			return BusinessUtil.divide(profit, num);
		}
		return BigDecimal.ZERO;
	}

}
