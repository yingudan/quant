package com.ujuit.quant.firmoffer.model;
import java.math.BigDecimal; 
import java.util.*; 
 
  
/**
 * 自定义指标
 * @author shadow
 */ 
public class CustomIndicators  {
	/**
	 *id
	 */
	private Integer id;
	/**
	 *参数组id
	 */
	private Integer groupId;
	/**
	 *指标名
	 */
	private String indicators;
	/** 
	 *id
	 */
	public Integer getId() {
		return id;
	}
	/** 
	 *id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 
	 *参数组id
	 */
	public Integer getGroupId() {
		return groupId;
	}
	/** 
	 *参数组id
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	/** 
	 *指标名
	 */
	public String getIndicators() {
		return indicators;
	}
	/** 
	 *指标名
	 */
	public void setIndicators(String indicators) {
		this.indicators = indicators;
	}
}