/*
*@Title:  TTestRecordListDto.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月21日 上午10:37:05   
 * @version 
*/
package com.ujuit.quant.firmoffer.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/*
 * 测试记录列表  
 * @author: cly     
 * @date:   2017年4月21日 上午10:37:05   
 * @version 
*/
public class TTestRecordListDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 回测记录
	 */
	private Integer id;
	
	/**
	 * 策略标识
	 */ 
	
	private Integer strategyId;
	
	/**
	 * 回测时间
	 */
	private Date testTime;
	
	/**
	 *  测试结束时间
	 */
	private Date endTime;
	
	/**
	 * 历史数据起始时间
	 */
	private Date hisDataStart;
	
	/**
	 * 历史数据结束时间
	 */
	private Date hisDataEnd;
	
	/**
	 * 策略频率 0.TICK 1.每天
	 */
	private String frequency;
	
	/**
	 * 初始资金
	 */
	private String iniFunding;
	
	/**
	 * 测试类型 0.历史回测 1.实时测试
	 */
	private String type;
	
	/**
	 * 这边指的是 同一个测试记录  最终的      累计收益
	 */
	private BigDecimal accumulatedReturn;
	
	private int status;
	
	private int paramGroupId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(Integer strategyId) {
		this.strategyId = strategyId;
	}

	public Date getTestTime() {
		return testTime;
	}

	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	public Date getHisDataStart() {
		return hisDataStart;
	}

	public void setHisDataStart(Date hisDataStart) {
		this.hisDataStart = hisDataStart;
	}

	public Date getHisDataEnd() {
		return hisDataEnd;
	}

	public void setHisDataEnd(Date hisDataEnd) {
		this.hisDataEnd = hisDataEnd;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getIniFunding() {
		return iniFunding;
	}

	public void setIniFunding(String iniFunding) {
		this.iniFunding = iniFunding;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAccumulatedReturn() {
		return accumulatedReturn;
	}

	public void setAccumulatedReturn(BigDecimal accumulatedReturn) {
		this.accumulatedReturn = accumulatedReturn;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getParamGroupId() {
		return paramGroupId;
	}

	public void setParamGroupId(int paramGroupId) {
		this.paramGroupId = paramGroupId;
	}
	
	
	
	

}
