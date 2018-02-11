package com.ujuit.quant.firmoffer.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ujuit.quant.firmoffer.model.DailyPosition;

/**
 * 每日持仓
 */
public class DailyPositionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 日期
	 */
	private Date time;

	/**
	 * 总资产
	 */
	private BigDecimal totalAssets;

	/**
	 * 持仓资产
	 */
	private BigDecimal positionAssets;

	/**
	 * 可用资产
	 */
	private BigDecimal usableAssets;

	/**
	 * 总收益
	 */
	private BigDecimal totalRevenue;

	/**
	 * 总交易费用
	 */
	private BigDecimal allCost;

	/**
	 * 当前仓位
	 */
	private BigDecimal nowSpace;

	/**
	 * 当日持仓列表
	 */
	List<DailyPosition> dpList;

	/**
	 * 交易次数
	 */
	private Integer transNum;

	public Integer getTransNum() {
		return transNum;
	}

	public void setTransNum(Integer transNum) {
		this.transNum = transNum;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<DailyPosition> getDpList() {
		return dpList;
	}

	public void setDpList(List<DailyPosition> dpList) {
		this.dpList = dpList;
	}

	public BigDecimal getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(BigDecimal totalAssets) {
		this.totalAssets = totalAssets;
	}

	public BigDecimal getPositionAssets() {
		return positionAssets;
	}

	public void setPositionAssets(BigDecimal positionAssets) {
		this.positionAssets = positionAssets;
	}

	public BigDecimal getUsableAssets() {
		return usableAssets;
	}

	public void setUsableAssets(BigDecimal usableAssets) {
		this.usableAssets = usableAssets;
	}

	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public BigDecimal getAllCost() {
		return allCost;
	}

	public void setAllCost(BigDecimal allCost) {
		this.allCost = allCost;
	}

	public BigDecimal getNowSpace() {
		return nowSpace;
	}

	public void setNowSpace(BigDecimal nowSpace) {
		this.nowSpace = nowSpace;
	}

}
