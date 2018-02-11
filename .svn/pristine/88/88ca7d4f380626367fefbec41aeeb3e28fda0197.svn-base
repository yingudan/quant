package com.ujuit.quant.bus.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 策略参数值
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @date 2017年4月19日
 * @company 有据信息技术有限公司
 * @version 1.0
 */
public class StrategyParamValue implements   Comparable<StrategyParamValue> {

	   private int id;//策略参数ID
	   private int strategyId  ;
	   private String paramName;
	   private String fieldName;//字段名称 ;
	   private   String fieldValue;//字段值 默认值，固定值已逗号隔开
	   private int type;//类型 0，固定 1循环
		/**
		 * 最大值
		 */
		private BigDecimal maxValue;
		 
		/**
		 * 步长
		 */
		private BigDecimal stepValue;
	
	   private List<BigDecimal> list;//多个固定值或循环值需要
	   private BigDecimal value;//固定值需要
	
	public List<BigDecimal> getList() {
		return list;
	}
	public void setList(List<BigDecimal> list) {
		this.list = list;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public void setMaxValue(String maxValue) {
		this.maxValue = new BigDecimal(maxValue);
	}
	public void setStepValue(String stepValue) {
		this.stepValue = new BigDecimal(stepValue);
	}
	
	
	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}
	public void setStepValue(BigDecimal stepValue) {
		this.stepValue = stepValue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStrategyId() {
		return strategyId;
	}
	public void setStrategyId(int strategyId) {
		this.strategyId = strategyId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	   
 
	public BigDecimal getMaxValue() {
		return maxValue;
	}
	public BigDecimal getStepValue() {
		return stepValue;
	}
 
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	@Override
	public int compareTo(StrategyParamValue o) {
		if (id< o.id) //这里比较的是什么 sort方法实现的就是按照此比较的东西从小到大排列
			return - 1 ;  
			if (id> o.id)  
			return 1 ;  
		 
		return 0;
	}
	   
	   
}
