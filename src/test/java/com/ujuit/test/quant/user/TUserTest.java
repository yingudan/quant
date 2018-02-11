package com.ujuit.test.quant.user;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ujuit.quant.firmoffer.constant.StrategyStatus;
import com.ujuit.quant.firmoffer.model.TTestRecord;
import com.ujuit.quant.firmoffer.model.TTransactionDetail;
import com.ujuit.quant.firmoffer.service.TTestRecordService;
import com.ujuit.quant.firmoffer.service.TTransactionDetailService;
import com.ujuit.quant.utils.MemCacheCommonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class TUserTest {

	

	@Test
	public void insertDataType() {
		MemCacheCommonUtil.put("test1", "123123", 1000*60*5);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("过期时间"+format.format(new Date(System.currentTimeMillis()+1000*60*60*24)));
		System.out.println("取出缓存数据"+MemCacheCommonUtil.get("test1"));
		
		
//		
//		MemCacheCommonUtil.put("test1", "123");
//		System.out.println("取出缓存数据test1 =  "+MemCacheCommonUtil.get("test1"));
		
	}

	
	
}
