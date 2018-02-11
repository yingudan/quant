package com.ujuit.quant.firmoffer.dto;

import java.io.Serializable;

/**
 * 收益概览非图标数据
 */
public class OfferDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String profit;
	private String yield;
	private String benchmark;
	private String alpha;
	private String beta;
	private String shapratio;
	private String returnVolatlity;
	private String maxRetrace;

	//我的持仓list
	
	//我的当日交易list
	
	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	public String getBeta() {
		return beta;
	}

	public void setBeta(String beta) {
		this.beta = beta;
	}

	public String getShapratio() {
		return shapratio;
	}

	public void setShapratio(String shapratio) {
		this.shapratio = shapratio;
	}

	public String getReturnVolatlity() {
		return returnVolatlity;
	}

	public void setReturnVolatlity(String returnVolatlity) {
		this.returnVolatlity = returnVolatlity;
	}

	public String getMaxRetrace() {
		return maxRetrace;
	}

	public void setMaxRetrace(String maxRetrace) {
		this.maxRetrace = maxRetrace;
	}

}
