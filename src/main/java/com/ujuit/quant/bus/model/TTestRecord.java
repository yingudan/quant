/*
 * TTestRecord.java
 * Copyright(C) 2015 uju
 * All rights reserved.
 * -----------------------------------------------
 * 2017-04-19 Created
 */
package com.ujuit.quant.bus.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @version 1.0 
 * @company 有据信息技术有限公司
 * @date 2017-04-19
 */
public class TTestRecord implements Serializable {

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
    private BigDecimal iniFunding;
    /**
     * 测试类型 0.历史回测 1.实时测试
     */
    private String type;
    private static final long serialVersionUID = 1L;

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
        this.frequency = frequency == null ? null : frequency.trim();
    }
    
    
    public BigDecimal getIniFunding() {
		return iniFunding;
	}
	public void setIniFunding(BigDecimal iniFunding) {
		this.iniFunding = iniFunding;
	}
	public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}