package com.ujuit.quant.bus.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ujuit.quant.bus.model.TRecordParam;
import com.ujuit.quant.bus.service.BusTRecordParamService;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;

/**
 * @Description 
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @date 2017年4月19日
 * @company 有据信息技术有限公司
 * @version 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class BusTRecordParamServiceImpl implements BusTRecordParamService {

	@Resource
	Dao dao;
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean insert(TRecordParam record)throws Exception {
		
		
		if(!dao.insert(new DataItem(TRecordParam.class,""), record)){
			throw new Exception("添加参数失败");
		}
		
		return true;
	}

	@Override
	public boolean insert(List<TRecordParam> list, int groupId) throws Exception {
		
		
		for (TRecordParam tRecordParam : list) {
			tRecordParam.setGroupId(groupId);
			if(!dao.insert(new DataItem(TRecordParam.class,"insert"), tRecordParam)){
				throw new Exception("添加参数失败");
			}
		}
		
		
		return true;
	}

}
