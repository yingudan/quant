package com.ujuit.quant.firmoffer.service;

import java.util.List;

import com.ujuit.quant.firmoffer.constant.RecordType;
import com.ujuit.quant.firmoffer.dto.ParamGroupListDto;
import com.ujuit.quant.firmoffer.dto.TTestRecordListDto;
import com.ujuit.quant.firmoffer.dto.TTestRecordWithTestDurationDto;
import com.ujuit.quant.firmoffer.model.TParamGroup;
import com.ujuit.quant.firmoffer.model.TTestRecord;
import com.ujuit.sysmanager.core.mybatis.DataResult;

public interface TTestRecordService {

	/**
	 * 新增测试记录
	 * 
	 * @param tTestRecord
	 * @return
	 */
	Integer insert(TTestRecord tTestRecord);
	
	boolean update(TTestRecord tTestRecord);
	
	/**
	 * 根据策略id查找最近一条测试记录
	 */
	TTestRecord queryTestRecordByStgId(String StgId);
	
	/**
	 * 根据测试id查找测试记录
	 */
	com.ujuit.quant.firmoffer.model.TTestRecord  queryTestRecordById(String id);
	
	/**
	 * 根据主键查询测试巨鹿  要带 运行时间的
	 * TODO(这里用一句话描述这个方法的作用)  
	 * @author  cly
	 * @param id
	 * @return
	 * @date 2017年4月24日 上午9:57:48       
	 * @return: TTestRecord
	 */
	TTestRecordWithTestDurationDto queryTestRecordWithDurationByPrimaryKey(Integer id);
	
	/**
	 * 根据策略id 查询数量
	 * @param StgId
	 * @param RecordType
	 * @return
	 */
	Integer queryOfferNum(String StgId, String type);
	
	
	
	/**
	 * 删除测试记录  
	 * @author  cly
	 * @param testRecordId
	 * @return
	 * @date 2017年4月23日 下午9:20:29       
	 * @return: boolean
	 */
	boolean deleteByPrimaryKey(Integer testRecordId);
	
	
	/**
	 * 根据策略id  测试记录  列表
	 * @author  cly
	 * @param StgId    策略id
	 * @param type     类型  0：历史回测   1：实盘模拟
	 * @param orderType 排序的 字段   1:测试时间  test_time   2:收益率  accumulated_return
	 * @param isDesc    是否倒序
	 * @param pageNum   页码
	 * @param pageSize   一页多少条数据
	 * @return
	 * @date 2017年4月22日 下午12:18:34       
	 * @return: DataResult<TTestRecordListDto>
	 */
	DataResult<TTestRecordListDto> queryTestRecordListByStgId(Integer stgId,RecordType type,int orderType,boolean isDesc,int pageIndex,int pageSize);
	
	/**
	 * 
	 * 获取某一次测试记录 的 参数组列表
	 * @author  cly
	 * @param testRecordId
	 * @param orderType   排序类型     1.回测收益  2.回测年华收益  3.基准收益  4.阿尔法  5.贝塔  6.夏普比率  7.收益波动率   8.最大回测
	 * @param isDesc     是否倒序
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @date 2017年4月23日 下午5:08:03       
	 * @return: DataResult<ParamGroupListDto>
	 */
	DataResult<ParamGroupListDto> queryParamGroupList(Integer testRecordId,int orderType,boolean isDesc,int pageIndex,int pageSize);
	
	
	/**
	 * 查询   实盘模拟 或者历史回测   最后一条
	 * @author  cly
	 * @param stgId
	 * @param type
	 * @return
	 * @date 2017年5月27日 上午11:03:34       
	 * @return: TTestRecordListDto
	 */
	TTestRecordListDto findLastTestRecordByType(Integer stgId,RecordType type);
	
	/**
	 * 查询 相关联的  子账户
	 * @author  cly
	 * @param testRecordIds  测试记录id 数组
	 * @return
	 * @date 2017年4月24日 下午5:18:27       
	 * @return: List<String>
	 */
	List<String> findRelatedSubAccount(List<String> testRecordIds);
	
	/**
	 * 查询 相关联的  参数组
	 * @author  cly
	 * @param testRecordIds  测试记录id 数组
	 * @return
	 * @date 2017年4月24日 下午5:18:27       
	 * @return: List<String>
	 */
	List<String> findRelatedParamGroup(List<String> testRecordIds);
	
	/**
	 * 批量删除
	 * @author  cly
	 * @param testRecordIds    测试记录id 数组
	 * @return
	 * @date 2017年4月24日 下午5:20:10       
	 * @return: boolean
	 */
	boolean batchDeleteByPrimaryKeyList(List<String> testRecordIds); 
	
	TParamGroup selectRunningGroup(int backtestId);
	
	
	
	
}
