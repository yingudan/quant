/*
*@Title:  DailyAssets.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月24日 下午6:39:09   
 * @version 
*/
package com.ujuit.quant.firmoffer.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/*
 * 当日总资产
 * @author: cly     
 * @date:   2017年4月24日 下午6:39:09   
 * @version 
*/
public class DailyAssets implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	/**
	 * 子账号标识
	 */
	private Integer subAccountId;
	
	/**
	 * 日期
	 */
	private Date time;
	
	/**
	 * 当前资产
	 */
	private BigDecimal money;
	
	/**
	 * 差值
	 */
	private BigDecimal gapMoney;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getSubAccountId() {
		return subAccountId;
	}

	public void setSubAccountId(Integer subAccountId) {
		this.subAccountId = subAccountId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getGapMoney() {
		return gapMoney;
	}

	public void setGapMoney(BigDecimal gapMoney) {
		this.gapMoney = gapMoney;
	}
	
	
	
	
}
