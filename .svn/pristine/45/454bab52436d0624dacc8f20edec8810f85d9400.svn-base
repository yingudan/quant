package com.ujuit.quant.firmoffer.service;

import java.util.List;

import com.ujuit.quant.firmoffer.dto.ChartHisDateDto;
import com.ujuit.quant.firmoffer.dto.ChartNowDateDto;
import com.ujuit.quant.firmoffer.dto.HisDataDto;
import com.ujuit.quant.firmoffer.model.DailyAssets;
import com.ujuit.quant.firmoffer.model.TMinIncome;
import com.ujuit.quant.firmoffer.model.TTestRecord;

public interface TMinIncomeService {

	/**
	 * 实盘模拟-收益概览-图表数据
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public ChartNowDateDto findChartNowDateDto(String strategyId, String testId, Long startTime, Long endTime,
			String type);

	/**
	 * 历史回测-收益概览-图表数据
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public ChartHisDateDto findHisCharDateDto(String groupId, String strategyId, String testId, Long startTime,
			Long endTime);

	/**
	 * 
	 * @param groupId
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<TMinIncome> findTMinIncomeMap(String groupId, String strategyId, String testId, Long startTime,
			Long endTime);

	/**
	 * 
	 * @param groupId
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<DailyAssets> findDailyAssetsMap(String groupId, String strategyId, String testId, Long startTime,
			Long endTime);

	/**
	 * 查询历史数据
	 * 
	 * @param groupId
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public HisDataDto findHisDataDto(String groupId, String strategyId, String testId, Long startTime, Long endTime);

	/**
	 * 查询实盘
	 */
	public HisDataDto findNowDataMap(TTestRecord test);
}
