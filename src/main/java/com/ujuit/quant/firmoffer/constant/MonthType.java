package com.ujuit.quant.firmoffer.constant;

public enum MonthType {

	one(1),

	six(6),

	nine(9),

	twelve(12);

	private Integer value;

	private MonthType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
