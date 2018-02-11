/*
 * TSubAccount.java
 * Copyright(C) 2015 uju
 * All rights reserved.
 * -----------------------------------------------
 * 2017-04-19 Created
 */
package com.ujuit.quant.firmoffer.model;

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
public class TSubAccount implements Serializable {

    /**
     * 子账号标识
     */
    private Integer id;
    /**
     * 测试参数组标识
     */
    private Integer paramGroupId;
    /**
     * 用户标识
     */
    private Integer userId;
    /**
     * 总金额
     */
    private BigDecimal moneyTotal;
    /**
     * 开户时间 创建时间
     */
    private Date createTime;
    /**
     * 锁定状态为2的时候是锁定账户
     */
    private String lockStatus;
    /**
     * 可用金额
     */
    private BigDecimal surplusMoney;
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getParamGroupId() {
        return paramGroupId;
    }
    public void setParamGroupId(Integer paramGroupId) {
        this.paramGroupId = paramGroupId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public BigDecimal getMoneyTotal() {
        return moneyTotal;
    }
    public void setMoneyTotal(BigDecimal moneyTotal) {
        this.moneyTotal = moneyTotal;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getLockStatus() {
        return lockStatus;
    }
    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus == null ? null : lockStatus.trim();
    }
    public BigDecimal getSurplusMoney() {
        return surplusMoney;
    }
    public void setSurplusMoney(BigDecimal surplusMoney) {
        this.surplusMoney = surplusMoney;
    }
}