package com.ujuit.quant.firmoffer.service;

import java.util.Date;
import java.util.List;

import com.ujuit.quant.firmoffer.dto.RiskIndicatorDto;
import com.ujuit.quant.firmoffer.dto.StrategyListDto;
import com.ujuit.quant.firmoffer.dto.TStrategicReturnDto;
import com.ujuit.quant.firmoffer.model.TStrategicReturn;
import com.ujuit.sysmanager.core.mybatis.DataResult;

public interface TStrategicReturnService {

	/**
	 * 新增测试记录
	 * 
	 * @param tTestRecord
	 * @return
	 */
	Integer insert(TStrategicReturn tStrategicReturn);

	/**
	 * 策略列表
	 * 
	 * @author shadow
	 * @param userId
	 * @param searchText
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public DataResult<StrategyListDto> findStrategyList(Integer userId, String searchText, Integer pageNum,
			Integer pageSize);

	/**策略收益
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @return
	 */
	
//	public DataResult<TStrategicReturnDto> findTStrategicReturnList(String strategyId, String testId,Long startTime,Long endTime);
	
	/**
	 * @author shadow 策略收益(及风险策略)
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @return
	 */
	public List<TStrategicReturnDto> findTStrategicReturnList(String groupId,String strategyId, String testId, Long startTime,
			Long endTime, String type);
	
	/**
	 * 查询每个月的策略收益
	 * @param month
	 * @param date
	 * @return
	 */
	TStrategicReturn findtsrBymonth(Date date,String strategyId,String testId)throws Exception;

	/**
	 * 风险指标
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public RiskIndicatorDto findRiskIndicator(String groupId,String strategyId, String testId,Long startTime,
			Long endTime,String dataType);
}
