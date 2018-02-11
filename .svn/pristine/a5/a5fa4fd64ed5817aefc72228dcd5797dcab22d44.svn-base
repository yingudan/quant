package com.ujuit.quant.firmoffer.service;

import java.math.BigDecimal;
import java.util.List;

import com.ujuit.dealserver.constant.ApplayType;
import com.ujuit.quant.firmoffer.dto.TestIndexDto;
import com.ujuit.quant.firmoffer.model.TStrategy;
import com.ujuit.quant.firmoffer.model.TStrategyParams;

public interface TStrategyParamsService {

	public TestIndexDto queryIndexDtoByTestId(Integer testId);

	public List<TStrategyParams> findParams(String strategyId, String testId);

	public TStrategy queryTsByGroupId(Integer groupId);

	/**
	 * 查询子账户id
	 * 
	 * @author shandowF
	 * @date 2018年1月31日
	 * @return subAccountId
	 */
	public Integer queryGroupIdByTestId(Integer testId);

	/**
	 * 
	 * 1.交易日总天数 策略所经历的时间内的交易日总天数
	 * 
	 * @author shadow
	 * @date 2018年2月1日
	 */
	public Integer queryStrategyDay(Integer testId);

	/**
	 * 4.交易总次数 以开仓次数为准，统计交易时间段内的所有开仓单
	 * 
	 * @author shadow
	 * @date 2018年2月1日
	 */
	public Integer queryTradeNum(Integer testId, ApplayType applayType);

	/**
	 * 2.平均交易间隔 交易日总天数除以交易总次数；
	 */
	public BigDecimal queryTimeGap(Integer testId);

	/**
	 * 5.盈利总次数
	 */
	public Integer queryProfitNum(Integer testId);

	/**
	 * 6.亏损次数
	 */
	public Integer querylossNum(Integer testId);

	/**
	 * 7.盈亏比
	 */
	public BigDecimal queryProfitAndLossScale(Integer testId);

	/**
	 * 8.平均单次盈利 盈利单盈利金额总和÷盈利单总数
	 */
	public BigDecimal queryAvgProfit(Integer testId);

	/**
	 * 9.平均单次亏损 亏损单亏损金额总和÷亏损单总数
	 */
	public BigDecimal queryAvgLoss(Integer testId);

}
