/*
 * TStrategy.java
 * Copyright(C) 2015 uju
 * All rights reserved.
 * -----------------------------------------------
 * 2017-04-19 Created
 */
package com.ujuit.quant.firmoffer.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author uju
 * @version 1.0
 * @date 2017-04-19
 */
public class TStrategyQh implements Serializable {

	/**
	 * 策略标识
	 */
	private Integer id;
	/**
	 * 策略名
	 */
	private String strategyName;
	/**
	 * 用户标识
	 */
	private Integer userId;
	/**
	 * 状态 0.实盘模拟中 1.历史回测中 2.未就绪 3.就绪
	 */
	private String status;
	/**
	 * 创建时间
	 */
	private Date createDate;
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName == null ? null : strategyName.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}