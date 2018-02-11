package com.ujuit.quant.firmoffer.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 基本信息
 */
public class TStrategyDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 策略名称
	 */
	private String strategyName;

	/**
	 * 策略状态
	 */
	private String status;

	/**
	 * 模拟历史次数
	 */
	private Integer hisOfferNum;

	/**
	 * 模拟时间开始
	 */
	private Date startTime;
	/**
	 * 模拟时间结束
	 */
	private Date endTime;

	/**
	 * 结束时间
	 */
	private Long overTime;

	/**
	 * 实际用时
	 */
	private Long actualTime;

	/**
	 * 初始资金
	 */
	private String iniFunding;

	/**
	 * 策略频率
	 */
	private String frequency;

	/**
	 * 模拟状态 0等待中 1测试中 2已完成 3失败
	 */
	private String testStatus;

	/**
	 * 测试参数组id
	 */
	private String paramId;

	/**
	 * 模拟参数列表
	 */
	List<RecordParamDto> ParamList;

	/*
	 * 预计用时
	 */
	private Integer predict;

	/**
	 * 共多少组
	 */
	private Integer allParamNum;

	/**
	 * 已完成
	 */
	private Integer overParam;

	/**
	 * 回测进度
	 */
	private BigDecimal testPlan;

	private String grade;

	public Long getActualTime() {
		return actualTime;
	}

	public void setActualTime(Long actualTime) {
		this.actualTime = actualTime;
	}

	public Long getOverTime() {
		return overTime;
	}

	public void setOverTime(Long overTime) {
		this.overTime = overTime;
	}

	public BigDecimal getTestPlan() {
		return testPlan;
	}

	public void setTestPlan(BigDecimal testPlan) {
		this.testPlan = testPlan;
	}

	public Integer getOverParam() {
		return overParam;
	}

	public void setOverParam(Integer overParam) {
		this.overParam = overParam;
	}

	public Integer getPredict() {
		return predict;
	}

	public void setPredict(Integer predict) {
		this.predict = predict;
	}

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getHisOfferNum() {
		return hisOfferNum;
	}

	public void setHisOfferNum(Integer hisOfferNum) {
		this.hisOfferNum = hisOfferNum;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getIniFunding() {
		return iniFunding;
	}

	public void setIniFunding(String iniFunding) {
		this.iniFunding = iniFunding;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public List<RecordParamDto> getParamList() {
		return ParamList;
	}

	public void setParamList(List<RecordParamDto> paramList) {
		ParamList = paramList;
	}

	public Integer getAllParamNum() {
		return allParamNum;
	}

	public void setAllParamNum(Integer allParamNum) {
		this.allParamNum = allParamNum;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
