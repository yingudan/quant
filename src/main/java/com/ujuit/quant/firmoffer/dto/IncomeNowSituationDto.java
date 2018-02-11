package com.ujuit.quant.firmoffer.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class IncomeNowSituationDto implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 回测收益
	 */
	private BigDecimal profit;
	/**
	 * 回测年化收益(最新一分钟)
	 */
	private BigDecimal yield;
	/**
	 * 基准收益(最新一分钟)
	 */
	private BigDecimal benchmark;
	/**
	 * 阿尔法
	 */
	private BigDecimal alpha;
	/**
	 * 贝塔
	 */
	private BigDecimal beta;
	/**
	 * 夏普比率
	 */
	private BigDecimal shapratio;
	/**
	 * 收益波动率
	 */
	private BigDecimal returnVolatlity;
	/**
	 * 最大回撤
	 */
	private BigDecimal maxRetrace;

	/**
	 * 模拟收益(最新一分钟)
	 */
	private BigDecimal accumulatedReturn;

	/**
	 * 实盘指标
	 */
	private TestIndexDto indexDto;

	
	public TestIndexDto getIndexDto() {
		return indexDto;
	}

	public void setIndexDto(TestIndexDto indexDto) {
		this.indexDto = indexDto;
	}

	/**
	 * 我的持仓
	 */
	// private List<DailyPositionDto> theDplist;
	private DailyPositionDto theDplist;

	/**
	 * 我的当日交易
	 */
	// private List<TransactionDto> theTclist;
	private TransactionDto theTclist;

	public BigDecimal getAccumulatedReturn() {
		return accumulatedReturn;
	}

	public void setAccumulatedReturn(BigDecimal accumulatedReturn) {
		this.accumulatedReturn = accumulatedReturn;
	}

	public DailyPositionDto getTheDplist() {
		return theDplist;
	}

	public void setTheDplist(DailyPositionDto theDplist) {
		this.theDplist = theDplist;
	}

	public TransactionDto getTheTclist() {
		return theTclist;
	}

	public void setTheTclist(TransactionDto theTclist) {
		this.theTclist = theTclist;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getYield() {
		return yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	public BigDecimal getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(BigDecimal benchmark) {
		this.benchmark = benchmark;
	}

	public BigDecimal getAlpha() {
		return alpha;
	}

	public void setAlpha(BigDecimal alpha) {
		this.alpha = alpha;
	}

	public BigDecimal getBeta() {
		return beta;
	}

	public void setBeta(BigDecimal beta) {
		this.beta = beta;
	}

	public BigDecimal getShapratio() {
		return shapratio;
	}

	public void setShapratio(BigDecimal shapratio) {
		this.shapratio = shapratio;
	}

	public BigDecimal getReturnVolatlity() {
		return returnVolatlity;
	}

	public void setReturnVolatlity(BigDecimal returnVolatlity) {
		this.returnVolatlity = returnVolatlity;
	}

	public BigDecimal getMaxRetrace() {
		return maxRetrace;
	}

	public void setMaxRetrace(BigDecimal maxRetrace) {
		this.maxRetrace = maxRetrace;
	}

}
