/*
 * TTransactionDetail.java
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
 * 交易详情
 * 
 * @author uju
 * @version 1.0
 * @date 2017-04-19
 */
public class TTransactionDetail implements Serializable {

	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 子账号标识
	 */
	private Integer subaccountId;
	/**
	 * 股票名称
	 */
	private String stockName;
	/**
	 * 股票代码
	 */
	private String stockCode;
	/**
	 * 成交时间
	 */
	private Date time;
	/**
	 * 买卖方向 1买入 2卖出
	 */
	private String tradingDirection;
	/**
	 * 成交均价
	 */
	private BigDecimal aveTranPrice;
	/**
	 * 成交数量
	 */
	private Integer tranNum;
	/**
	 * 成交金额
	 */
	private BigDecimal amount;
	/**
	 * 交易费用
	 */
	private BigDecimal cost;

	/**
	 * 现价
	 */
	private BigDecimal nowPrice;

	/**
	 * 成交日期
	 */
	private String applayDate;

	/**
	 * 成交时间
	 */
	private String applayTime;

	private String nameAndCode;

	private static final long serialVersionUID = 1L;

	public String getNameAndCode() {
		return nameAndCode;
	}

	public void setNameAndCode(String nameAndCode) {
		this.nameAndCode = nameAndCode;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getApplayDate() {
		return applayDate;
	}

	public void setApplayDate(String applayDate) {
		this.applayDate = applayDate;
	}

	public String getApplayTime() {
		return applayTime;
	}

	public void setApplayTime(String applayTime) {
		this.applayTime = applayTime;
	}

	public BigDecimal getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(BigDecimal nowPrice) {
		this.nowPrice = nowPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubaccountId() {
		return subaccountId;
	}

	public void setSubaccountId(Integer subaccountId) {
		this.subaccountId = subaccountId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName == null ? null : stockName.trim();
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTradingDirection() {
		return tradingDirection;
	}

	public void setTradingDirection(String tradingDirection) {
		this.tradingDirection = tradingDirection == null ? null : tradingDirection.trim();
	}

	public BigDecimal getAveTranPrice() {
		return aveTranPrice;
	}

	public void setAveTranPrice(BigDecimal aveTranPrice) {
		this.aveTranPrice = aveTranPrice;
	}

	public Integer getTranNum() {
		return tranNum;
	}

	public void setTranNum(Integer tranNum) {
		this.tranNum = tranNum;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
}