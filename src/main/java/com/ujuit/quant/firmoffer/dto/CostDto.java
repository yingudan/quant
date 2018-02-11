package com.ujuit.quant.firmoffer.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author shandowF
 * @date 2018年2月1日
 */
public class CostDto {

	private Date time;

	/**
	 * 手续费
	 */
	private BigDecimal cost;

	/**
	 * 累计手续费
	 */
	private BigDecimal benchCost;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getBenchCost() {
		return benchCost;
	}

	public void setBenchCost(BigDecimal benchCost) {
		this.benchCost = benchCost;
	}

}
