/*
*@Title:  ParamGroupListDto.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月23日 下午5:02:18   
 * @version 
*/
package com.ujuit.quant.firmoffer.dto;


import com.ujuit.quant.firmoffer.model.IncomeSituation;

/*
 * 某一次 测试记录的 参数组列表  包含  最终收益 
 * @author: cly     
 * @date:   2017年4月23日 下午5:02:18   
 * @version 
*/
public class ParamGroupListDto extends IncomeSituation {
	

	private static final long serialVersionUID = 1L;

	/**
	 * 参数名 列表 逗号隔开
	 */
	private String paramNameList;
	
	/**
	 * 参数值列表  逗号隔开
	 */
	private String paramValueList;
	
	private String paramIdList;
	
	/**
	 * 测试状态 0.等待中 1. 测试中 2.已完成3.失败  
	 */
	private int testStatus;
	
	private Integer groupId;

	public String getParamNameList() {
		return paramNameList;
	}

	public void setParamNameList(String paramNameList) {
		this.paramNameList = paramNameList;
	}

	public String getParamValueList() {
		return paramValueList;
	}

	public void setParamValueList(String paramValueList) {
		this.paramValueList = paramValueList;
	}

	public int getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(int testStatus) {
		this.testStatus = testStatus;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getParamIdList() {
		return paramIdList;
	}

	public void setParamIdList(String paramIdList) {
		this.paramIdList = paramIdList;
	}
	
	
	
	

}
