package com.ujuit.quant.firmoffer.dto;

import java.io.Serializable;

public class ChartGradeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 累计收益
	 */
	private ChartPointDto gradeDto;

	public ChartPointDto getGradeDto() {
		return gradeDto;
	}

	public void setGradeDto(ChartPointDto gradeDto) {
		this.gradeDto = gradeDto;
	}

}
