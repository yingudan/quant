package com.ujuit.quant.bus.constant;
/**
 * @Description 
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @date 2017年4月19日
 * @company 有据信息技术有限公司
 * @version 1.0
 */
public enum LockStatus {
unlock("0"),lock("1")
	;
private String value;

public String getValue() {
	return value;
}

private LockStatus(String value) {
	this.value = value;
}

}
