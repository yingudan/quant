package com.ujuit.quant.firmoffer.dto;

import java.math.BigDecimal;

/**
 * 每日调仓
 */
public class MixPositionDto {

	/**
	 * 股票名称
	 */
	private String stockName;

	/**
	 * 股票代码
	 */
	private String code;

	/**
	 * 成交均价
	 */
	private BigDecimal dealPrice;

	/**
	 * 买卖方向 1，是买入 2，是卖出
	 */
	private String applayType;

	/**
	 * 仓位变化前
	 */
	private BigDecimal bfMixPosition;

	/**
	 * 仓位变化后
	 */
	private BigDecimal afMixPosition;

	/**
	 * 成交时间
	 */
	private String dealTime;

	/**
	 * 成交数量
	 */
	private Integer dealNum;

	/**
	 * 交易后数量
	 */
	private Integer tradeNum;

	private String dealDate;

	/**
	 * 仓位变化
	 */
	private String changes;

	/**
	 * 成交仓位
	 */
	private String bh;

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getChanges() {
		return changes;
	}

	public void setChanges(String changes) {
		this.changes = changes;
	}

	public String getDealDate() {
		return dealDate;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	public Integer getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(Integer tradeNum) {
		this.tradeNum = tradeNum;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(BigDecimal dealPrice) {
		this.dealPrice = dealPrice;
	}

	public String getApplayType() {
		return applayType;
	}

	public void setApplayType(String applayType) {
		this.applayType = applayType;
	}

	public BigDecimal getBfMixPosition() {
		return bfMixPosition;
	}

	public void setBfMixPosition(BigDecimal bfMixPosition) {
		this.bfMixPosition = bfMixPosition;
	}

	public BigDecimal getAfMixPosition() {
		return afMixPosition;
	}

	public void setAfMixPosition(BigDecimal afMixPosition) {
		this.afMixPosition = afMixPosition;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public Integer getDealNum() {
		return dealNum;
	}

	public void setDealNum(Integer dealNum) {
		this.dealNum = dealNum;
	}

}
