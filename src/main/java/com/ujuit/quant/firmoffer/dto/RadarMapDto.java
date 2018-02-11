package com.ujuit.quant.firmoffer.dto;

import java.util.Date;
import java.util.List;

import com.ujuit.quant.firmoffer.model.IndustryFactor;
import com.ujuit.quant.firmoffer.model.StrategyMonthAnalysis;

public class RadarMapDto {

	List<StrategyMonthAnalysis> monthanalysisList;// 风格

	List<IndustryFactor> IndustryFactorList;// 行业因子

	private Date theMonth;

	public Date getTheMonth() {
		return theMonth;
	}

	public void setTheMonth(Date theMonth) {
		this.theMonth = theMonth;
	}

	public List<StrategyMonthAnalysis> getMonthanalysisList() {
		return monthanalysisList;
	}

	public void setMonthanalysisList(List<StrategyMonthAnalysis> monthanalysisList) {
		this.monthanalysisList = monthanalysisList;
	}

	public List<IndustryFactor> getIndustryFactorList() {
		return IndustryFactorList;
	}

	public void setIndustryFactorList(List<IndustryFactor> industryFactorList) {
		IndustryFactorList = industryFactorList;
	}

}
