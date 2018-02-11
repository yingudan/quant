/*
*@Title:  SmsCodeCacheUtil.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月19日 下午2:42:30   
 * @version 
*/
package com.ujuit.quant.utils;

import java.util.Date;

import com.ujuit.sysmanager.cache.MemcachedService;
 

/*
 * 通用缓存 
 * @author: cly     
 * @date:   2017年4月19日 下午2:42:30   
 * @version 
*/
public class MemCacheCommonUtil {

	public static int Second = 1000;
	
	public static int Minute = 1000*60;
	
	public static int Hour = 1000*60*60;
	
	public static int Day = 1000*60*60*23;
	
	public static final String MEM_LOGIN_FAIL = "mem_login_fail";
	
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
	 * 存数据
	 * @author  cly
	 * @param key
	 * @param objcect
	 * @param expiredTime
	 * @date 2017年4月19日 下午5:41:59       
	 * @return: void
	 */
	public static void put(String key,Object objcect,long expiredTime){
		memcachedUtil.put(key, objcect, new Date(System.currentTimeMillis()+expiredTime));
	}
	
	public static void put(String key,Object objcect){
		memcachedUtil.put(key, objcect);
	}
	
	/**
	 * 拿数据
	 * @author  cly
	 * @param key
	 * @return
	 * @date 2017年4月19日 下午5:41:46       
	 * @return: Object
	 */
	public static Object get(String key){
		return  memcachedUtil.get(key);
	}
	
	/**
	 * 删除
	 * @author  cly
	 * @param key
	 * @date 2017年4月19日 下午5:40:50       
	 * @return: void
	 */
	public static void remove(String key){
		memcachedUtil.remove(key);
	}
	
	/**
	 * 清除所有
	 * @author  cly
	 * @date 2017年4月19日 下午5:41:34       
	 * @return: void
	 */
	public static void clear(){
		memcachedUtil.clear();
	}
	
	
	
}
