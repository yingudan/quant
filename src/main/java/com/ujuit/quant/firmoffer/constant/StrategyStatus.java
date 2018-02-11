package com.ujuit.quant.firmoffer.constant;

/**
 * 策略状态
 * 
 * @author shadow
 * @date 2017年4月19日
 */
public enum StrategyStatus {

	/**
	 * 模拟中
	 */
	offer_ing("0"),
	/**
	 * 回测中
	 */
	offer_reing("1"),
	/**
	 * 未就绪
	 */
	not_ready("2"),
	/**
	 * 就绪
	 */
	ready("3");
	private String value;

	private StrategyStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
