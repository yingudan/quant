/*
*@Title:  TTestRecordWithTestTimeDto.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月24日 上午10:18:25   
 * @version 
*/
package com.ujuit.quant.firmoffer.dto;

import com.ujuit.quant.firmoffer.model.TTestRecord;

/*
 * 测试记录  带有测试持续时间   状态 
 * @author: cly     
 * @date:   2017年4月24日 上午10:18:25   
 * @version 
*/
public class TTestRecordWithTestDurationDto extends TTestRecord{

	private static final long serialVersionUID = 1L;
	
	/**
	 *  1. 测试中 2.已完成
	 */
	private int testStatus;
	
	/**
	 * 测试持续时间  秒数
	 */
	private int duration;

	public int getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(int testStatus) {
		this.testStatus = testStatus;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
	
	
	
	

}
