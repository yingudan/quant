/*
 * TRecordParam.java
 * Copyright(C) 2015 uju
 * All rights reserved.
 * -----------------------------------------------
 * 2017-04-19 Created
 */
package com.ujuit.quant.bus.model;

import java.io.Serializable;

/**
 * @Description 
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @version 1.0 
 * @company 有据信息技术有限公司
 * @date 2017-04-19
 */
public class TRecordParam implements Serializable {

    /**
     * 参数标识
     */
    private Integer id;
    /**
     * 参数组标识
     */
    private Integer groupId;
    /**
     * 参数名
     */
    private String paramName;
    /**
     * 参数值
     */
    private String paramValue;
    
    private String fieldName ;//          varchar(100) comment '字段名称',
    
    public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getGroupId() {
        return groupId;
    }
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    public String getParamName() {
        return paramName;
    }
    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }
    public String getParamValue() {
        return paramValue;
    }
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }
 
    
    
}