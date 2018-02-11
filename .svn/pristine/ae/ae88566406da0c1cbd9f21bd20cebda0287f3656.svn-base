package com.ujuit.quant.firmoffer.service;

import java.util.Map;

import com.ujuit.quant.firmoffer.dto.DailyPositionDto;
import com.ujuit.quant.firmoffer.dto.IncomeNowSituationDto;
import com.ujuit.quant.firmoffer.model.IncomeSituation;

public interface IncomeSituationService {

	/**
	 * 查询收益最大的收入详情(需求 为 最近历史回测中 回测收益最大的一组)
	 * 
	 * @author shadow
	 * @param userId
	 * @return
	 */
	IncomeSituation queryInsTheMaxProfit(String userId, Integer testId);

	/**
	 * 历史回测-收益概览-非图表数据
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	IncomeSituation findHisIncomeSituation(String groupId, String strategyId, String testId, Long startTime,
			Long endTime);

	/**
	 * 实盘模拟-收益概览-非图表数据
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return , Long startTime, Long endTime
	 */
	IncomeNowSituationDto findNowIncomeSituation(String groupId, String strategyId, String testId);

	/**
	 * 
	 * @param groupId
	 * @param strategyId
	 * @param testId
	 * @return
	 */
	DailyPositionDto findPosition(String groupId, String strategyId, String testId);

	/**
	 * 查询最近回测的收益(按天计算)
	 * 
	 * @author shandowF
	 * @date 2018年1月31日
	 */
	IncomeSituation findBySubAccountId(Integer subAccountId);

	/**
	 * 返回当日实盘收益(按天计算)
	 * 
	 * @author shandowF
	 * @date 2018年2月1日
	 */
	IncomeNowSituationDto findSituationByDate(Map<String, Object> map);
}
