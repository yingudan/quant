package com.ujuit.quant.firmoffer.dto;

import java.math.BigDecimal;

/**
 * @author shandowF
 * @date 2018年2月7日
 */
public class TestIndexDto {

	/**
	 * 平均单次盈利
	 */
	private BigDecimal avgProfit;

	/**
	 * 平均单次亏损
	 */
	private BigDecimal avgLoss;

	/**
	 * 1交易日总天数
	 */
	private Integer tradeDay;

	/**
	 * 交易总次数
	 */
	private Integer tradeAllNum;

	/**
	 * 2平均交易间隔
	 */
	private BigDecimal timeGap;

	/**
	 * 盈亏比
	 */
	private BigDecimal profitAndLossScale;

	/**
	 * 盈利总次数
	 */
	private Integer profitNum;

	/**
	 * 亏损总次数
	 */
	private Integer lossNum;

	/**
	 * 亏损概率
	 */
	private BigDecimal lossPr;

	/**
	 * 盈利概率
	 */
	private BigDecimal profitPr;

	/**
	 * 胜率
	 */
	private BigDecimal winRate;

	/**
	 * 单次最大盈利
	 */
	private BigDecimal maxProfit;

	/**
	 * 单次最大亏损
	 */
	private BigDecimal maxLoss;

	/**
	 * 最长整理天数
	 */
	private Integer clearUpDay;

	/**
	 * 卖出总次数
	 */
	private Integer saleAllNum;

	/**
	 * 卖出盈利总次数
	 */
	private Integer saleProfitNum;
	
	public Integer getSaleProfitNum() {
		return saleProfitNum;
	}

	public void setSaleProfitNum(Integer saleProfitNum) {
		this.saleProfitNum = saleProfitNum;
	}

	public Integer getSaleAllNum() {
		return saleAllNum;
	}

	public void setSaleAllNum(Integer saleAllNum) {
		this.saleAllNum = saleAllNum;
	}

	public BigDecimal getAvgProfit() {
		return avgProfit;
	}

	public void setAvgProfit(BigDecimal avgProfit) {
		this.avgProfit = avgProfit;
	}

	public BigDecimal getAvgLoss() {
		return avgLoss;
	}

	public void setAvgLoss(BigDecimal avgLoss) {
		this.avgLoss = avgLoss;
	}

	public Integer getTradeDay() {
		return tradeDay;
	}

	public void setTradeDay(Integer tradeDay) {
		this.tradeDay = tradeDay;
	}

	public Integer getTradeAllNum() {
		return tradeAllNum;
	}

	public void setTradeAllNum(Integer tradeAllNum) {
		this.tradeAllNum = tradeAllNum;
	}

	public BigDecimal getTimeGap() {
		return timeGap;
	}

	public void setTimeGap(BigDecimal timeGap) {
		this.timeGap = timeGap;
	}

	public BigDecimal getProfitAndLossScale() {
		return profitAndLossScale;
	}

	public void setProfitAndLossScale(BigDecimal profitAndLossScale) {
		this.profitAndLossScale = profitAndLossScale;
	}

	public Integer getProfitNum() {
		return profitNum;
	}

	public void setProfitNum(Integer profitNum) {
		this.profitNum = profitNum;
	}

	public Integer getLossNum() {
		return lossNum;
	}

	public void setLossNum(Integer lossNum) {
		this.lossNum = lossNum;
	}

	public BigDecimal getLossPr() {
		return lossPr;
	}

	public void setLossPr(BigDecimal lossPr) {
		this.lossPr = lossPr;
	}

	public BigDecimal getProfitPr() {
		return profitPr;
	}

	public void setProfitPr(BigDecimal profitPr) {
		this.profitPr = profitPr;
	}

	public BigDecimal getWinRate() {
		return winRate;
	}

	public void setWinRate(BigDecimal winRate) {
		this.winRate = winRate;
	}

	public BigDecimal getMaxProfit() {
		return maxProfit;
	}

	public void setMaxProfit(BigDecimal maxProfit) {
		this.maxProfit = maxProfit;
	}

	public BigDecimal getMaxLoss() {
		return maxLoss;
	}

	public void setMaxLoss(BigDecimal maxLoss) {
		this.maxLoss = maxLoss;
	}

	public Integer getClearUpDay() {
		return clearUpDay;
	}

	public void setClearUpDay(Integer clearUpDay) {
		this.clearUpDay = clearUpDay;
	}

}
