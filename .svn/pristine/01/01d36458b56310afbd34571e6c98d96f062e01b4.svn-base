package com.ujuit.quant.firmoffer.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ujuit.quant.firmoffer.constant.RecordType;
import com.ujuit.quant.firmoffer.dto.ParamGroupListDto;
import com.ujuit.quant.firmoffer.dto.RecordParamDto;
import com.ujuit.quant.firmoffer.dto.TTestRecordListDto;
import com.ujuit.quant.firmoffer.dto.TTestRecordWithTestDurationDto;
import com.ujuit.quant.firmoffer.model.DailyAssets;
import com.ujuit.quant.firmoffer.model.DailyPosition;
import com.ujuit.quant.firmoffer.model.IncomeSituation;
import com.ujuit.quant.firmoffer.model.Stock;
import com.ujuit.quant.firmoffer.model.TMinIncome;
import com.ujuit.quant.firmoffer.model.TParamGroup;
import com.ujuit.quant.firmoffer.model.TRecordParam;
import com.ujuit.quant.firmoffer.model.TStrategicReturn;
import com.ujuit.quant.firmoffer.model.TTestRecord;
import com.ujuit.quant.firmoffer.service.TTestRecordService;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;
import com.ujuit.sysmanager.core.mybatis.DataResult;

/**
 * @author shadow
 */
@Service

public class TTestRecordServiceImpl implements TTestRecordService {
	@Resource
	Dao dao;

	@Override
	public Integer insert(TTestRecord tTestRecord) {
		return dao.add(new DataItem(TTestRecord.class, "insert"), tTestRecord).getId();
	}

	@Override
	public TTestRecord queryTestRecordByStgId(String StgId) {
		return dao.find(new DataItem(TTestRecord.class, "queryTestRecordByStgId"), StgId);
	}

	@Override
	public Integer queryOfferNum(String StgId, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("StgId", StgId);
		map.put("type", type);
		return dao.find(new DataItem(TTestRecord.class, "queryOfferNum"), map);
	}
	
	/* (non-Javadoc)
	 * @see com.ujuit.quant.firmoffer.service.TTestRecordService#queryTestRecordByPrimaryKeyWithTime(java.lang.Integer)
	 */
	@Override
	public TTestRecordWithTestDurationDto queryTestRecordWithDurationByPrimaryKey(Integer id) {
		TTestRecordWithTestDurationDto dto = dao.find(new DataItem(TTestRecord.class, "selectByPrimaryKeyWithDuration"), id);
		//因为sql 查询出来的是 测试记录中  test_status 的最小值  这边要转化下
		if(dto!=null){
			if(dto.getTestStatus() == 0||dto.getTestStatus() == 1){
				//如果 某个测试记录  各个参数组 的测试状态最小值  是 0 或者 1  那么对于测试记录来说都是测试中
				dto.setTestStatus(1);
			}else{
				//否则  就是测试成功
				dto.setTestStatus(2);
			}
		}
		
		return dto;
	}
	
	/* (non-Javadoc)
	 * @see com.ujuit.quant.firmoffer.service.TTestRecordService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public boolean deleteByPrimaryKey(Integer testRecordId) {
		return dao.delete(new DataItem(TTestRecord.class, "deleteByPrimaryKey"), testRecordId);
	}

	/* (non-Javadoc)
	 * @see com.ujuit.quant.firmoffer.service.TTestRecordService#queryTestRecordByStgId(java.lang.String, int, java.lang.String, int, int, int)
	 */
	@Override
	public DataResult<TTestRecordListDto> queryTestRecordListByStgId(Integer stgId,RecordType type,int orderType,boolean isDesc,int pageIndex,int pageSize) {
		String orderKey = "";
		Map<String, Object> map = new HashMap<>();
		if(orderType == 1){
			orderKey = "test_time";
		}else if(orderType == 2){
			orderKey ="accumulated_return";
		}else{
			//默认 以test_time 倒序
			orderKey = "test_time";
			isDesc = true;
		}
		map.put("strategyId", stgId);
		map.put("type", type.getValue());
		map.put("orderKey", orderKey);
		map.put("order", isDesc?"desc":"asc");
		
		DataResult<TTestRecordListDto> dataResult= dao.getData(new DataItem(TTestRecord.class, "queryTestRecordListByStgId"), map, pageIndex, pageSize);
		
		return dataResult;
	}
	
	/* (non-Javadoc)
	 * @see com.ujuit.quant.firmoffer.service.TTestRecordService#findLastTestRecordByType(java.lang.Integer, com.ujuit.quant.firmoffer.constant.RecordType)
	 */
	@Override
	public TTestRecordListDto findLastTestRecordByType(Integer stgId, RecordType type) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("strategyId", stgId);
		map.put("type", type.getValue());
		return dao.find(new DataItem(TTestRecord.class, "findLastTestRecordByType"), map);
	}

	/* (non-Javadoc)
	 * @see com.ujuit.quant.firmoffer.service.TTestRecordService#queryParamGroupList(java.lang.String, java.lang.String, boolean, int, int)
	 */
	@Override
	public DataResult<ParamGroupListDto> queryParamGroupList(Integer testRecordId, int orderType, boolean isDesc,
			int pageIndex, int pageSize) {
		String orderKey = "";
		Map<String, Object> map = new HashMap<>();
		
		switch(orderType){
		case 1:
			orderKey = "accumulated_return";
			break;
		case 2:
			orderKey = "yield";
			break;
		case 3:
			orderKey = "total_benchmark";
			break;
		case 4:
			orderKey = "alpha";
			break;
		case 5:
			orderKey = "beta";
			break;
		case 6:
			orderKey = "shapratio";
			break;
		case 7:
			orderKey = "return_volatlity";
			break;
		case 8:
			orderKey = "max_retrace";
			break;
		default:
			//默认 以 id 倒序
			orderKey = "id";
			isDesc = true;
			break;
		}
		map.put("testRecordId", testRecordId);
		map.put("orderKey", orderKey);
		map.put("order", isDesc?"desc":"asc");
		
		
		
		DataResult<ParamGroupListDto> dataResult= dao.getData(new DataItem(TTestRecord.class, "queryParamGroupList"), map, pageIndex, pageSize);
		List<ParamGroupListDto> list = dataResult.getRecords();
		for(ParamGroupListDto dto:list){
			String[] ids = dto.getParamIdList().split(",");
			String[] names = dto.getParamNameList().split(",");
			String[] values = dto.getParamValueList().split(",");
			List<RecordParamDto> paramList = new ArrayList<>();
			for(int i = 0;i<ids.length;i++){
				RecordParamDto recordParamDto = new RecordParamDto();
				recordParamDto.setParamId(ids[i]);
				recordParamDto.setParamName(names[i]);
				recordParamDto.setParamValue(values[i]);
				paramList.add(recordParamDto);
				
			}
			Collections.sort(paramList, new Comparator<RecordParamDto>(){

				@Override
				public int compare(RecordParamDto o1, RecordParamDto o2) {
					// TODO Auto-generated method stub
					int id1 = toInt(o1.getParamId(), 0);
					int id2 = toInt(o2.getParamId(), 0);
					return id1-id2;
				}
				
			});
			
			List<String> idList = new ArrayList<>();
			List<String> nameList = new ArrayList<>();
			List<String> valueList = new ArrayList<>();
			for(RecordParamDto recrodParamDto:paramList){
				idList.add(recrodParamDto.getParamId());
				nameList.add(recrodParamDto.getParamName());
				valueList.add(recrodParamDto.getParamValue());
			}
			dto.setParamIdList(org.apache.commons.lang.StringUtils.join(idList, ","));
			dto.setParamNameList(org.apache.commons.lang.StringUtils.join(nameList, ","));
			dto.setParamValueList(org.apache.commons.lang.StringUtils.join(valueList, ","));
			
		}
		
		
		return dataResult;
	}

	/* (non-Javadoc)
	 * @see com.ujuit.quant.firmoffer.service.TTestRecordService#findRelatedSubAccount(java.lang.String[])
	 */
	@Override
	public List<String> findRelatedSubAccount(List<String> testRecordIds) {
		return dao.getDataList(new DataItem(TTestRecord.class, "findRelatedSubAccount"),testRecordIds);
	}

	/* (non-Javadoc)
	 * @see com.ujuit.quant.firmoffer.service.TTestRecordService#findRelatedParamGroup(java.lang.String[])
	 */
	@Override
	public List<String> findRelatedParamGroup(List<String> testRecordIds) {
		return dao.getDataList(new DataItem(TTestRecord.class, "findRelatedParamGroup"),testRecordIds);
	}

	/* (non-Javadoc)
	 * @see com.ujuit.quant.firmoffer.service.TTestRecordService#batchDeleteByPrimaryKeyArr(java.lang.String[])
	 */
	@Override
	@Transactional(rollbackFor={ RuntimeException.class},propagation=Propagation.REQUIRED)
	public boolean batchDeleteByPrimaryKeyList(List<String> testRecordIds) {
		//关联的子账号id 列表
		List<String> relatedSubAccountIdList = findRelatedSubAccount(testRecordIds);
		//关联的参数组 id 列表
		List<String> relatedParamGroupIdList = findRelatedParamGroup(testRecordIds);
		
		
		
		if(relatedParamGroupIdList != null&&relatedParamGroupIdList.size() >0){
		//  删除测试参数
			dao.delete(new DataItem(TRecordParam.class, "batchDeleteByParamGroupIdList"),relatedParamGroupIdList);
			
			//  删除参数组
			dao.delete(new DataItem(TParamGroup.class, "batchDeleteByPrimaryKeyList"),relatedParamGroupIdList);
		}

		if(relatedSubAccountIdList != null&&relatedSubAccountIdList.size() > 0){
			//收入详情
			dao.delete(new DataItem(IncomeSituation.class, "batchDeleteBySubAccountIdList"),relatedSubAccountIdList);
			//当日总资产（一天算一次）
			dao.delete(new DataItem(DailyAssets.class, "batchDeleteBySubAccountIdList"),relatedSubAccountIdList);
			//实盘每分钟收益记录
			dao.delete(new DataItem(TMinIncome.class, "batchDeleteBySubAccountIdList"),relatedSubAccountIdList);
			// 每日持仓（一天一算）
			dao.delete(new DataItem(DailyPosition.class, "batchDeleteBySubAccountIdList"),relatedSubAccountIdList);
//			//交易详情表
//			dao.delete(new DataItem(TTransactionDetail.class, "batchDeleteBySubAccountIdList"),relatedSubAccountIdList);
			// 策略累计收益  一月一次
			dao.delete(new DataItem(TStrategicReturn.class, "batchDeleteBySubAccountIdList"),relatedSubAccountIdList);
			
			//stock_account
			dao.delete(new DataItem(Stock.class, "batchDeleteStockAccountBySubAccountIdList"),relatedSubAccountIdList);
			//stock_cancel_list
			dao.delete(new DataItem(Stock.class, "batchDeleteStockCancelListBySubAccountIdList"),relatedSubAccountIdList);
			//stock_deal_list
			dao.delete(new DataItem(Stock.class, "batchDeleteStockDealListBySubAccountIdList"),relatedSubAccountIdList);
			//stock_applay_list
			dao.delete(new DataItem(Stock.class, "batchDeleteStockApplayListBySubAccountIdList"),relatedSubAccountIdList);
			
			//  删除子账户
			dao.delete(new DataItem(TParamGroup.class, "batchDeleteByPrimaryKeyList"),relatedSubAccountIdList);
		}
		
		
		// 删除 测试记录
		dao.delete(new DataItem(TTestRecord.class, "batchDeleteByPrimaryKeyList"), testRecordIds);
		return true;
	}

	@Override
	public TTestRecord queryTestRecordById(String id) {
		 	return dao.find(new DataItem(TTestRecord.class, "queryTestRecordById"), id);
	}
	
	
	private int toInt(String s,int defaultValue){
		try{
			int i = Integer.parseInt(s);
			return i;
		}catch (Exception e) {
			return defaultValue;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.ujuit.quant.firmoffer.service.TTestRecordService#selectRunningGroup(int)
	 */
	@Override
	public TParamGroup selectRunningGroup(int backtestId) {
		return dao.find(new DataItem(TParamGroup.class, "selectRunningGroup"), backtestId);
	}

	@Override
	public boolean update(TTestRecord tTestRecord) {
		return dao.update(new DataItem(TTestRecord.class, "updateByPrimaryKeySelective"), tTestRecord);
	}
	
	
	
}
