package com.ujuit.quant.firmoffer.service;

import java.util.List;
import java.util.Map;

import com.ujuit.quant.firmoffer.dto.ChartGradeDto;
import com.ujuit.quant.firmoffer.dto.DailyPositionByPageDto;
import com.ujuit.quant.firmoffer.dto.DailyPositionDto;
import com.ujuit.quant.firmoffer.dto.MixPositionDto;
import com.ujuit.quant.firmoffer.model.DailyPosition;

public interface DailyPositionService {

	Integer insert(DailyPosition dailyPosition);

	/**
	 * 查询当日持仓
	 * 
	 * @param strategyId
	 * @param testId
	 * @return
	 */
	public DailyPositionByPageDto findDailyList(String groupId, String strategyId, String testId, Long startTime,
			Long endTime, Integer pageSize, Integer pageNum);

	/**
	 * 查询当日持仓
	 * 
	 * @author shandowF
	 * @date 2017年12月21日
	 *
	 */
	public List<DailyPositionDto> findDailyListAll(String groupId, String strategyId, String testId, Long startTime,
			Long endTime);

	/**
	 * 根据子账号查询我的持仓(这里list只有一个)
	 */
	public List<DailyPositionDto> findMyDaily(String strategyId, String testId);

	/**
	 * 查询实时持仓
	 */
	public List<DailyPosition> findNowDaily(String groupId, String strategyId, String testId, Long startTime,
			Long endTime);

	/**
	 * 查询持仓期日(按天 用于查询)
	 */
	public List<DailyPosition> findThedateList();

	/**
	 * 实盘每日持仓
	 */
	DailyPositionByPageDto findNowDailyList(String groupId, String strategyId, String testId, Long startTime,
			Long endTime, Integer pageSize, Integer pageNum);

	/**
	 * 实盘持仓 查询最新一条实盘模拟
	 * 
	 * @author shandowF
	 * @date 2017年12月29日
	 *
	 */
	List<DailyPosition> queryActualDailyList(Integer strategyId, Integer testId);

	/**
	 * 实盘每日持仓All
	 */
	List<DailyPositionDto> findNowDailyListAll(String groupId, String strategyId, String testId, Long startTime,
			Long endTime);

	public ChartGradeDto queryGradeDtoList(String testId, String strategyId, Long startTime, Long endTime);

	/**
	 * 每日调仓
	 * 
	 * @param testId
	 * @param strategyId
	 * @return
	 */
	List<MixPositionDto> queryMixSpace(String testId, String strategyId);

	/**
	 * 获取资金情况
	 * @author shandowF
	 * @date 2017年12月29日
	 *
	 */
	Map<String, Object> queryAssetInfo(Integer testId, Integer strategyId);
}
