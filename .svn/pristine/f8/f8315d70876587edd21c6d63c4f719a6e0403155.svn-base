package com.ujuit.quant.constants;

import com.ujuit.sysmanager.core.constant.IStatus;
import com.ujuit.sysmanager.core.constant.SystemStatus;

/**
 * @Description 
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @date 2017年3月13日
 * @company 有据信息技术有限公司
 * @version 1.0
 */
public enum ResultStatus implements IStatus {
	//统一系统异常的状态
	SUCCESS(SystemStatus.SUCCESS),//成功
	ERROR(SystemStatus.ERROR),//异常
	HAVElOGIN(SystemStatus.HAVElOGIN),//需要登录
	;
	private int value;
	private String description;

	private ResultStatus(SystemStatus status) {
		this.value = status.getValue();
		this.description = status.getDescription();
	}
	private ResultStatus(int value, String description) {
		this.value = value;
		this.description = description;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
