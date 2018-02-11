package com.ujuit.quant.firmoffer.dto;

import java.math.BigDecimal;
import java.util.Date;

public class gradeDto {

	/**
	 * 分数
	 */
	private BigDecimal grade;

	/**
	 * 评分时间
	 */
	private Date gradeTime;

	public BigDecimal getGrade() {
		return grade;
	}

	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}

	public Date getGradeTime() {
		return gradeTime;
	}

	public void setGradeTime(Date gradeTime) {
		this.gradeTime = gradeTime;
	}

}
