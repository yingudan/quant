package com.ujuit.quant.bus.service;

import java.math.BigDecimal;

import com.ujuit.quant.bus.model.TSubAccount;

/**
 * @Description 子账号表
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @date 2017年4月19日
 * @company 有据信息技术有限公司
 * @version 1.0
 */
public interface BusTSubAccountService {

	/**
	 * 添加子账号数据
	 * 
	 * @param sub
	 * @return
	 * @date 2017年4月19日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 * @throws Exception
	 */
	boolean insert(TSubAccount sub) throws Exception;

	/**
	 * @param paramGroupId
	 *            测试参数组标识
	 * @param userId
	 *            用户标识
	 * @param moneyTotal
	 *            总金额
	 * @return
	 * @date 2017年4月19日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 * @throws Exception
	 */
	boolean insert(Integer paramGroupId, Integer userId, BigDecimal moneyTotal) throws Exception;

}
