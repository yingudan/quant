package com.ujuit.quant.bus.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ujuit.quant.bus.constant.LockStatus;
import com.ujuit.quant.bus.model.TSubAccount;
import com.ujuit.quant.bus.model.TTestRecord;
import com.ujuit.quant.bus.service.BusTSubAccountService;
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
public class BusTSubAccountServiceImpl implements BusTSubAccountService {

	@Resource
	Dao dao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean insert(TSubAccount sub) throws Exception {
		
		
		if(!dao.insert(new DataItem(TTestRecord.class,"insert"), sub)){
			throw new Exception("添加子账号出错");
		}
		
		
		return true ;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public boolean insert(Integer paramGroupId, Integer userId,
			BigDecimal moneyTotal) throws Exception {
		TSubAccount sub=new TSubAccount();
		sub.setCreateTime(new Date());
		sub.setLockStatus(LockStatus.unlock.getValue());
		sub.setMoneyTotal(moneyTotal);
		sub.setParamGroupId(paramGroupId);
		sub.setSurplusMoney(moneyTotal);
		sub.setUserId(userId);
		if(!dao.insert(new DataItem(TSubAccount.class,"insert"), sub)){
			throw new Exception("添加子账号出错");
		}
		
		return true;
	}

}
