package com.ujuit.quant.bus.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ujuit.quant.bus.model.TTestRecord;
import com.ujuit.quant.bus.service.BusTTestRecordService;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;

/**
 * @Description 测试记录表
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @date 2017年4月19日
 * @company 有据信息技术有限公司
 * @version 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class BusTTestRecordServiceImpl implements BusTTestRecordService {
	
	@Resource
	Dao dao;
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean insert(TTestRecord record) throws Exception {
		
		if(!dao.insert(new DataItem(TTestRecord.class,"insert"), record)){
			throw new Exception("添加测试记录出错");
		}
		
		return true;
	}

	/**
	 * @author shandowF
	 * @date 2018年2月9日
	 * 
	 */
	@Override
	public boolean update(TTestRecord record) throws Exception {
		if(!dao.update(new DataItem(TTestRecord.class,"update"), record)){
			throw new Exception("修改测试记录出错");
		}
		return true;
	}
	
	
	

}
