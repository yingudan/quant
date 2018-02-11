/*
*@Title:  TMinIncome.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月24日 下午6:49:06   
 * @version 
*/
package com.ujuit.quant.firmoffer.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/*
 * 每分钟收益  
 * @author: cly     
 * @date:   2017年4月24日 下午6:49:06   
 * @version 
*/
public class TMinIncome implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 子账户id
	 */
	private Integer subAccountId;

	/**
	 * 对数收益
	 */
	private BigDecimal logarithmicReturn;

	/**
	 * 相对收益
	 */
	private BigDecimal relativeReturn;

	/**
	 * 累计收益
	 */
	private BigDecimal accumulatedReturn;

	/**
	 * 累计基准收益率
	 */
	private BigDecimal benchmarkReturn;

	/**
	 * 资产
	 */
	private BigDecimal money;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 新增基准收益上证50
	 */
	private BigDecimal benchmarkSz50;

	/**
	 * 累计基准收益率上证50
	 */
	private BigDecimal totalBenchmarkSz50;

	/**
	 * 相对收益上证50
	 */
	private BigDecimal relativeReturnSz50;

	/**
	 * 新增基准收益中证500
	 */
	private BigDecimal benchmarkZz500;

	/**
	 * 累计基准收益率中证500
	 */
	private BigDecimal totalBenchmarkZz500;

	/**
	 * 相对收益中证500
	 */
	private BigDecimal relativeReturnZz500;

	public BigDecimal getBenchmarkSz50() {
		return benchmarkSz50;
	}

	public void setBenchmarkSz50(BigDecimal benchmarkSz50) {
		this.benchmarkSz50 = benchmarkSz50;
	}

	public BigDecimal getTotalBenchmarkSz50() {
		return totalBenchmarkSz50;
	}

	public void setTotalBenchmarkSz50(BigDecimal totalBenchmarkSz50) {
		this.totalBenchmarkSz50 = totalBenchmarkSz50;
	}

	public BigDecimal getRelativeReturnSz50() {
		return relativeReturnSz50;
	}

	public void setRelativeReturnSz50(BigDecimal relativeReturnSz50) {
		this.relativeReturnSz50 = relativeReturnSz50;
	}

	public BigDecimal getBenchmarkZz500() {
		return benchmarkZz500;
	}

	public void setBenchmarkZz500(BigDecimal benchmarkZz500) {
		this.benchmarkZz500 = benchmarkZz500;
	}

	public BigDecimal getTotalBenchmarkZz500() {
		return totalBenchmarkZz500;
	}

	public void setTotalBenchmarkZz500(BigDecimal totalBenchmarkZz500) {
		this.totalBenchmarkZz500 = totalBenchmarkZz500;
	}

	public BigDecimal getRelativeReturnZz500() {
		return relativeReturnZz500;
	}

	public void setRelativeReturnZz500(BigDecimal relativeReturnZz500) {
		this.relativeReturnZz500 = relativeReturnZz500;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getBenchmarkReturn() {
		return benchmarkReturn;
	}

	public void setBenchmarkReturn(BigDecimal benchmarkReturn) {
		this.benchmarkReturn = benchmarkReturn;
	}

	public Integer getSubAccountId() {
		return subAccountId;
	}

	public void setSubAccountId(Integer subAccountId) {
		this.subAccountId = subAccountId;
	}

	public BigDecimal getLogarithmicReturn() {
		return logarithmicReturn;
	}

	public void setLogarithmicReturn(BigDecimal logarithmicReturn) {
		this.logarithmicReturn = logarithmicReturn;
	}

	public BigDecimal getRelativeReturn() {
		return relativeReturn;
	}

	public void setRelativeReturn(BigDecimal relativeReturn) {
		this.relativeReturn = relativeReturn;
	}

	public BigDecimal getAccumulatedReturn() {
		return accumulatedReturn;
	}

	public void setAccumulatedReturn(BigDecimal accumulatedReturn) {
		this.accumulatedReturn = accumulatedReturn;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
