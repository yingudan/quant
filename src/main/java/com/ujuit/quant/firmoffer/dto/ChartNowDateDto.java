package com.ujuit.quant.firmoffer.dto;

import java.io.Serializable;

public class ChartNowDateDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 累计收益
	 */
	private ChartPointDto accumulated;

	/**
	 * 基准收益率
	 */
	private ChartPointDto benchmark;
	
	/**
	 * 对数收益（实盘一分钟一算）
	 */
	private ChartPointDto logarithmicreturn;

	/**
	 * 相对收益
	 */
	private ChartPointDto relativereturn;

	
	/**
	 * 基准收益率上证50
	 */
	private ChartPointDto benchmarkSz50;
	
	/**
	 * 累计基准收益率上证50
	 */
	private ChartPointDto totalBenchmarkSz50;
	
	/**
	 * 相对收益上证50
	 */
	private ChartPointDto relativeReturnSz50;
	
	/**
	 *  基准收益率中证500
	 */
	private ChartPointDto benchmarkZz500;
	
	/**
	 * 累计基准收益率中证500
	 */
	private ChartPointDto totalBenchmarkZz500;
	
	/**对数收益中证500
	 * relative_return_zz500
	 */
	private ChartPointDto relativeReturnZz500;
	
	/**
	 * 手续费
	 */
	private ChartPointDto cost;
	
	/**
	 * 累计手续费
	 */
	private ChartPointDto benchCost;

	public ChartPointDto getLogarithmicreturn() {
		return logarithmicreturn;
	}

	public void setLogarithmicreturn(ChartPointDto logarithmicreturn) {
		this.logarithmicreturn = logarithmicreturn;
	}

	public ChartPointDto getRelativereturn() {
		return relativereturn;
	}

	public void setRelativereturn(ChartPointDto relativereturn) {
		this.relativereturn = relativereturn;
	}

	public ChartPointDto getAccumulated() {
		return accumulated;
	}

	public void setAccumulated(ChartPointDto accumulated) {
		this.accumulated = accumulated;
	}

	public ChartPointDto getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(ChartPointDto benchmark) {
		this.benchmark = benchmark;
	}

	public ChartPointDto getBenchmarkSz50() {
		return benchmarkSz50;
	}

	public void setBenchmarkSz50(ChartPointDto benchmarkSz50) {
		this.benchmarkSz50 = benchmarkSz50;
	}

	public ChartPointDto getTotalBenchmarkSz50() {
		return totalBenchmarkSz50;
	}

	public void setTotalBenchmarkSz50(ChartPointDto totalBenchmarkSz50) {
		this.totalBenchmarkSz50 = totalBenchmarkSz50;
	}

	public ChartPointDto getRelativeReturnSz50() {
		return relativeReturnSz50;
	}

	public void setRelativeReturnSz50(ChartPointDto relativeReturnSz50) {
		this.relativeReturnSz50 = relativeReturnSz50;
	}

	public ChartPointDto getBenchmarkZz500() {
		return benchmarkZz500;
	}

	public void setBenchmarkZz500(ChartPointDto benchmarkZz500) {
		this.benchmarkZz500 = benchmarkZz500;
	}

	public ChartPointDto getTotalBenchmarkZz500() {
		return totalBenchmarkZz500;
	}

	public void setTotalBenchmarkZz500(ChartPointDto totalBenchmarkZz500) {
		this.totalBenchmarkZz500 = totalBenchmarkZz500;
	}

	public ChartPointDto getRelativeReturnZz500() {
		return relativeReturnZz500;
	}

	public void setRelativeReturnZz500(ChartPointDto relativeReturnZz500) {
		this.relativeReturnZz500 = relativeReturnZz500;
	}

	public ChartPointDto getCost() {
		return cost;
	}

	public void setCost(ChartPointDto cost) {
		this.cost = cost;
	}

	public ChartPointDto getBenchCost() {
		return benchCost;
	}

	public void setBenchCost(ChartPointDto benchCost) {
		this.benchCost = benchCost;
	}

	
}
