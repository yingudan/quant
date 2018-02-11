package com.ujuit.quant.utils;

import java.math.BigDecimal;
import java.util.Hashtable;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ujuit.sysmanager.cache.MemcachedService;
import com.ujuit.sysmanager.util.Res;

/**
 * @Description 系统用户缓存工具类
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @date 2017年3月24日
 * @company 有据信息技术有限公司
 * @version 1.0
 */
public final class StockUtil {
	/**
	 * 缓存标志
	 */
	private static String KEY_CACHE_LABEL = "CACHE_STOCK";

	private static String MARKET_STOCK_DATA = "MARKET_STOCK_DATA";

	static

	{
		String env = Res.getProperties("env");

		KEY_CACHE_LABEL = "CACHE_STOCK" + env;

	}

	// private static MemcachedService memcachedService;
	protected static MemcachedService memcachedUtil;

	static {
		try {
			memcachedUtil = new MemcachedService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	static {
		try {
			@SuppressWarnings("unchecked")
			Hashtable<String, BigDecimal> stockCache = (Hashtable<String, BigDecimal>) memcachedUtil
					.get(KEY_CACHE_LABEL);
			if (stockCache == null) {
				memcachedUtil.put(KEY_CACHE_LABEL, new Hashtable<String, BigDecimal>());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据ID获取用户信息（未刷新的缓存）
	 * 
	 * @param id
	 * @return
	 * @date 2017年3月24日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	@SuppressWarnings("unchecked")
	public static synchronized Hashtable<String, BigDecimal> get() {
		// return (Hashtable<String, BigDecimal>)
		// memcachedUtil.get(KEY_CACHE_LABEL);
		String res = RedisUtil.getRedisValue(MARKET_STOCK_DATA);
		if (res == null) {
			return null;
		}
		Gson gson = new Gson();
		Hashtable<String, BigDecimal> hashtable = gson.fromJson(res, new TypeToken<Hashtable<String, BigDecimal>>() {
		}.getType());
		return hashtable;

	}
	/*
	 * public static void main(String[] args) { System.out.println(get()); }
	 */

	/**
	 * @param code
	 * @param price
	 * @date 2017年4月27日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	@SuppressWarnings("unchecked")
	public static synchronized void put(String code, BigDecimal price) {

		Hashtable<String, BigDecimal> userCache = (Hashtable<String, BigDecimal>) memcachedUtil.get(KEY_CACHE_LABEL);
		if (userCache == null) {
			userCache = new Hashtable<String, BigDecimal>();
		}
		userCache.put(code, price);

		memcachedUtil.put(KEY_CACHE_LABEL, userCache);
	}

}
