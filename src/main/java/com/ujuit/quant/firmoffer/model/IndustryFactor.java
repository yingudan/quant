/*
 * IndustryFactor.java
 * Copyright(C) 2015 uju
 * All rights reserved.
 * -----------------------------------------------
 * 2017-07-14 Created
 */
package com.ujuit.quant.firmoffer.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * 
 * @author uju
 * @version 1.0
 * @date 2017-07-14
 */
public class IndustryFactor implements Serializable {

	private Integer id;
	/**
	 * create_time
	 */
	private Timestamp createTime;
	/**
	 * 值
	 */
	private BigDecimal value;
	/**
	 * 行业ID
	 */
	private Integer industryId;
	/**
	 * 发布时间
	 */
	private Date publishDate;

	private String name;
	private static final long serialVersionUID = 1L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
}