/*
 * TParamGroup.java
 * Copyright(C) 2015 uju
 * All rights reserved.
 * -----------------------------------------------
 * 2017-04-19 Created
 */
package com.ujuit.quant.bus.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @version 1.0 
 * @company 有据信息技术有限公司
 * @date 2017-04-19
 */
public class TParamGroup implements Serializable {

    private Integer id;
    /**
     * 测试记录标识
     */
    private Integer backtestId;
    /**
     * 测试状态 0.测试中 1.完毕
     */
    private String testStatus;
    private Date startTime;
    private Date endTime;
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getBacktestId() {
        return backtestId;
    }
    public void setBacktestId(Integer backtestId) {
        this.backtestId = backtestId;
    }
    public String getTestStatus() {
        return testStatus;
    }
    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus == null ? null : testStatus.trim();
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
}