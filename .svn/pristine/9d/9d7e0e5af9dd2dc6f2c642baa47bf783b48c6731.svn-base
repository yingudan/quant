/*
 * TStrategicReturn.java
 * Copyright(C) 2015 uju
 * All rights reserved.
 * -----------------------------------------------
 * 2017-04-19 Created
 */
package com.ujuit.quant.firmoffer.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 策略收益
 * 
 * @author uju
 * @version 1.0
 * @date 2017-04-19
 */
public class TStrategicReturn implements Serializable {

	private Integer id;
	/**
	 * 子账号标识
	 */
	private BigDecimal subAccountId;
	/**
	 * 月份
	 */
	private Integer month;
	/**
	 * 类型
	 */
	private String dataType;
	/**
	 * 计算日期
	 */
	private Date time;
	/**
	 * 数值
	 */
	private BigDecimal value;

	/**
	 * 创建时间
	 */
	private Date createTime;
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getSubAccountId() {
		return subAccountId;
	}

	public void setSubAccountId(BigDecimal subAccountId) {
		this.subAccountId = subAccountId;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType == null ? null : dataType.trim();
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}