/*
 * StrategyMonthAnalysis.java
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
public class StrategyMonthAnalysis implements Serializable {

	private Integer id;
	/**
	 * create_time
	 */
	private Timestamp createTime;
	/**
	 * 值
	 */
	private BigDecimal profit;
	/**
	 * 类型1、市净率倒数2、市盈率倒数3、贝塔4、收益波动率5、销售毛利率6、5日换手率7、5日收益率8、账面杠杆9、市值对数
	 */
	private Integer type;
	/**
	 * 发布时间
	 */
	private Date publishDate;
	
	
	private static final long serialVersionUID = 1L;

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

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
}