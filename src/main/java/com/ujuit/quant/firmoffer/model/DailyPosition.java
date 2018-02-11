/*
 * DailyPosition.java
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
 * 
 * 每日持仓
 * 
 * @author uju
 * @version 1.0
 * @date 2017-04-19
 */
public class DailyPosition implements Serializable {

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 子账号标识
	 */
	private BigDecimal subAccountId;
	/**
	 * 日期
	 */
	private Date time;
	/**
	 * 股票名称
	 */
	private String stockName;
	/**
	 * 收盘价
	 */
	private BigDecimal closingPrice;
	/**
	 * 持仓数量
	 */
	private BigDecimal positionNum;
	/**
	 * 成交价
	 */
	private BigDecimal tranPrice;

	/**
	 * 现价
	 */
	private BigDecimal nowPrice;
	/**
	 * 持仓金额
	 */
	private BigDecimal positionPrice;
	/**
	 * 收益
	 */
	private BigDecimal profit;

	private String thedate;

	private String profitScale;

	/**
	 * 股票代码
	 */
	private String code;
	
	private static final long serialVersionUID = 1L;

	public String getProfitScale() {
		return profitScale;
	}

	public void setProfitScale(String profitScale) {
		this.profitScale = profitScale;
	}

	public BigDecimal getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(BigDecimal nowPrice) {
		this.nowPrice = nowPrice;
	}

	public String getThedate() {
		return thedate;
	}

	public void setThedate(String thedate) {
		this.thedate = thedate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getSubAccountId() {
		return subAccountId;
	}

	public void setSubAccountId(BigDecimal subAccountId) {
		this.subAccountId = subAccountId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName == null ? null : stockName.trim();
	}

	public BigDecimal getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(BigDecimal closingPrice) {
		this.closingPrice = closingPrice;
	}

	public BigDecimal getPositionNum() {
		return positionNum;
	}

	public void setPositionNum(BigDecimal positionNum) {
		this.positionNum = positionNum;
	}

	public BigDecimal getTranPrice() {
		return tranPrice;
	}

	public void setTranPrice(BigDecimal tranPrice) {
		this.tranPrice = tranPrice;
	}

	public BigDecimal getPositionPrice() {
		return positionPrice;
	}

	public void setPositionPrice(BigDecimal positionPrice) {
		this.positionPrice = positionPrice;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}