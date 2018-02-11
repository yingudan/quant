/*
*@Title:  SmsCodeCacheUtil.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月19日 下午2:42:30   
 * @version 
*/
package com.ujuit.quant.utils;

import java.util.Date;

import com.ujuit.quant.user.constants.ValidateType;
import com.ujuit.sysmanager.cache.MemcachedService;
 
/*
 * 缓存短信验证码   
 * @author: cly     
 * @date:   2017年4月19日 下午2:42:30   
 * @version 
*/
public class MemCacheSmsCodeUtil {

	public static int Second = 1000;
	
	public static int Minute = 1000*60;
	
	public static int Hour = 1000*60*60;
	
	public static int Day = 1000*60*60*23;
	
	private static final String MEM_SMS_CODE = "mem_sms_code";
	
	protected static MemcachedService memcachedUtil;
	static{
		try {
			memcachedUtil = new MemcachedService( );
//			@SuppressWarnings("unchecked")
//			Map<String, String> appTokens = (Map<String, String>) memcachedUtil.get(MEM_SMS_CODE);
//			if(appTokens == null){
//				appTokens = new Hashtable<String, String>();
//			}
//			memcachedUtil.put(MEM_SMS_CODE, appTokens);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存 短信验证码
	 * @author  cly
	 * @param phoneNum    手机号
	 * @param type        类型
	 * @param code        验证码
	 * @param secondNum   秒数
	 * @date 2017年4月19日 下午2:58:25       
	 * @return: void
	 */
	public static void saveSmsCode(String phoneNum,ValidateType type,String code,int secondNum){
		String key = generateSmsCodeKey(phoneNum, type);
		memcachedUtil.put(key, code, new Date(System.currentTimeMillis()+secondNum*Second));
//		System.out.println("缓存短信验证码"+memcachedUtil.get(key));
	}
	
	/**
	 * 从缓存取出验证码
	 * @author  cly
	 * @param phoneNum
	 * @param type
	 * @return
	 * @date 2017年4月19日 下午2:59:03       
	 * @return: String
	 */
	public static String getSmsCode(String phoneNum,ValidateType type){
		String key = generateSmsCodeKey(phoneNum, type);
		return (String) memcachedUtil.get(key);
	}
	
	/**
	 * 清除某个验证码
	 * @author  cly
	 * @param phoneNum
	 * @param type
	 * @date 2017年4月19日 下午2:59:18       
	 * @return: void
	 */
	public static void removeSmsCode(String phoneNum,ValidateType type){
		String key = generateSmsCodeKey(phoneNum, type);
		memcachedUtil.remove(key);
	}
	
	
	private static String generateSmsCodeKey(String phoneNum,ValidateType type){
		return MEM_SMS_CODE+"_"+type.getKey()+"_"+phoneNum;
	}
	
	
	
}
