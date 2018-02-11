/*
 * TWechatAttention.java
 * Copyright(C) 2015 uju
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-14 Created
 */
package com.ujuit.quant.firmoffer.model;

import java.io.Serializable;

/**
 * 
 * 
 * @author uju
 * @version 1.0
 * @date 2017-07-14
 */
public class TWechatAttention implements Serializable {

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 被关注的用户id
	 */
	private Integer userId;
	/**
	 * 被关注的策略id
	 */
	private Integer strategyId;
	/**
	 * 是否开启通知（1开启 0取消）
	 */
	private Integer isPush;
	/**
	 * 微信openid
	 */
	private String openId;
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(Integer strategyId) {
		this.strategyId = strategyId;
	}

	public Integer getIsPush() {
		return isPush;
	}

	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
	}
}