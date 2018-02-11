package com.ujuit.quant.firmoffer.constant;

/**
 * @author shadow
 *
 */
public enum TStrategicType {

	/**
	 * 策略收益
	 */
	yield("1"),
	/**
	 * 阿尔法
	 */
	alpha("2"),
	/**
	 * 贝塔
	 */
	beta("3"),
	/**
	 * 夏普比率
	 */
	shap_ratio("4"),
	/**
	 * 索提洛比率
	 */
	sotiro_ratio("5"),

	/**
	 * 信息比率
	 */
	infomation_ratio("6"),
	/**
	 * 收益波动率
	 */
	return_volatlity("7"),

	/**
	 * 最大回撤
	 */
	max_retrace("8");
	private String value;

	private TStrategicType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	

}
