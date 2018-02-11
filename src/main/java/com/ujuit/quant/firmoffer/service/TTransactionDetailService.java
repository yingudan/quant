package com.ujuit.quant.firmoffer.service;

import java.util.List;

import com.ujuit.quant.firmoffer.dto.SaleMapDto;
import com.ujuit.quant.firmoffer.dto.TransactionDto;
import com.ujuit.quant.firmoffer.model.TTransactionDetail;
import com.ujuit.sysmanager.core.mybatis.DataResult;

public interface TTransactionDetailService {

	/**
	 * 新增测试记录
	 * 
	 * @param tTestRecord
	 * @return
	 */
	Integer insert(TTransactionDetail tTransactionDetail);

	/**
	 * 交易详情列表
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public DataResult<TTransactionDetail> findTransactionList(String groupId, String strategyId, String testId,
			Long beginDate, Long endDate, String type, Integer pageSize, Integer pageNum);

	/**
	 * 实盘交易详情
	 */
	public DataResult<TTransactionDetail> findNowTtrDetail(String groupId, String strategyId, String testId,
			Long startTime, Long endTime, Integer pageSize, Integer pageNum);

	/**
	 * 实盘交易详情
	 * 
	 * @author shandowF
	 * @date 2017年12月21日
	 *
	 */
	public List<TTransactionDetail> findNowTtrDetailAll(String groupId, String strategyId, String testId,
			Long startTime, Long endTime);

	/**
	 * 历史回测 交易详情
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public DataResult<TransactionDto> findTransactionDto(String groupId, String strategyId, String testId,
			Long beginDate, Long endDate, String type, Integer pageSize, Integer pageNum);

	/**
	 * 历史回测 交易详情
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<TransactionDto> findTransactionDtoAll(String groupId, String strategyId, String testId, Long beginDate,
			Long endDate, String type);

	/**
	 * 
	 * @param groupId
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<SaleMapDto> findByAndSaleMap(String groupId, String strategyId, String testId, Long startTime,
			Long endTime);
}
