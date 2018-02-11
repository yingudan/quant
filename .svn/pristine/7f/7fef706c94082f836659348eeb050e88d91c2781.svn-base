package com.ujuit.quant.firmoffer.service;

import java.util.List;
import java.util.Map;

import com.ujuit.quant.firmoffer.dto.TStrategyDto;
import com.ujuit.quant.firmoffer.dto.startStrategyDto;
import com.ujuit.quant.firmoffer.model.TStrategy;

public interface TStrategyService {

	boolean deleteTStrategy(int id);

	Integer insert(TStrategy tStrategy);

	/**
	 * 历史回测基本信息
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @param groupId
	 * @return
	 */
	TStrategyDto findTStrategyDto(String groupId, String strategyId, String testId, String type);

	Map<String, Object> queryTime(String testId, String strategyId, String groupId);

	/**
	 * 
	 */
	TStrategyDto findNowTestTStrategyDto(String strategyId, String testId);

	/**
	 * 根据策略ID 进行查找
	 * 
	 * @param id
	 *            -
	 * @return
	 * @date 2017年4月25日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	TStrategy findById(Integer id);

	/**
	 * 获取历史回测耗时
	 * 
	 * @param strategyId
	 * @param testId
	 * @return
	 */
	Map<String, Object> getHisBacktoTake(startStrategyDto dto);

	/**
	 * 根据测试记录id 获取 策略信息
	 * 
	 * @author cly
	 * @param id
	 * @return
	 * @date 2017年5月3日 下午12:08:10
	 * @return: TStrategy
	 */
	TStrategy selectByTestRecordId(Integer id);

	Integer findRunTestId(String strategyId, String type);

	/**
	 * @param strategyId
	 *            策略id
	 * @param type
	 *            类型  测试类型 0.历史回测 1.实时测试
	 * @author shandowF
	 * @date 2017年12月29日
	 *
	 */
	Integer findLatestId(Integer strategyId, String type);

	Integer queryUserIsAttenTion(Integer userId, String strategyType);

	List<TStrategy> queryTStrategyByUserId(Integer userId, String strategyType);

	/**
	 * 查询已就绪的策略
	 * 
	 * @author shandowF
	 * @date 2017年12月13日
	 *
	 */
	List<TStrategy> queryTStrategyListByUserId(Integer userId);

}
