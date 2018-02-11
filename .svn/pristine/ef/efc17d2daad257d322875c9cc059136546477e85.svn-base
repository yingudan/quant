package com.ujuit.quant.utils;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ujuit.quant.firmoffer.service.RedisService;

/**
 * @author shandowF
 * @date 2017年11月30日
 */
public class RedisUtil implements ApplicationListener<ContextRefreshedEvent> {

	@Resource
	private RedisService redisCacheService;

	public static RedisService redisService;

	private static RedisUtil redisUtil;

	public static String getRedisValue(String key) {
		if(redisService==null){
			return null;
		}
		return redisService.get(key);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {// root
																// application
																// context
																// 没有parent，他就是老大.
			redisUtil = this;
			redisUtil.redisService = redisCacheService;
		}

	}

}
