/*
 * TStrategyParams.java
 * Copyright(C) 2015 uju
 * All rights reserved.
 * -----------------------------------------------
 * 2017-04-19 Created
 */
package com.ujuit.quant.firmoffer.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 策略参数表
 * 
 * @author uju
 * @version 1.0
 * @date 2017-04-19
 */
public class TStrategyParams implements Serializable {

	/**
	 * 参数标识
	 */
	private Integer id;
	/**
	 * 策略标识
	 */
	private Integer strategyId;
	/**
	 * 策略名称
	 */
	private String strategyName;

	/**
	 * 参数名
	 */
	private String paramName;
	/**
	 * 字段名
	 */
	private String fieldName;

	/**
	 * 默认值
	 */
	private String defaultVal;

	/**
	 * 初始化
	 */
	private BigDecimal inFunding;

	private static final long serialVersionUID = 1L;

	public BigDecimal getInFunding() {
		return inFunding;
	}

	public void setInFunding(BigDecimal inFunding) {
		this.inFunding = inFunding;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(Integer strategyId) {
		this.strategyId = strategyId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName == null ? null : paramName.trim();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName == null ? null : fieldName.trim();
	}

	public String getDefaultVal() {
		return defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

}