package com.ujuit.quant.firmoffer.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ujuit.quant.firmoffer.dto.RadarMapDto;
import com.ujuit.quant.firmoffer.model.IndustryFactor;
import com.ujuit.quant.firmoffer.model.StrategyMonthAnalysis;
import com.ujuit.quant.firmoffer.service.StrategyMonthAnalysisService;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;

@Service
public class StrategyMonthAnalysisServiceImpl implements StrategyMonthAnalysisService {

	@Resource
	Dao dao;

	public RadarMapDto queryRadarMap(Long theMonth,String testId, String strategyId) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RadarMapDto dto = new RadarMapDto();
		map.put("testId", testId);
		map.put("strategyId", strategyId);
		if (theMonth != null) {
			calendar.setTime(new Date(theMonth));
			calendar.add(Calendar.MONTH, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			map.put("startTime", calendar.getTime());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			map.put("endTime", calendar.getTime());
			dto.setTheMonth(new Date(theMonth));
		} else {
			StrategyMonthAnalysis Analysis = dao.find(new DataItem(StrategyMonthAnalysis.class, "queryAnalysis"),map);
			if (Analysis != null) {
				calendar.setTime(Analysis.getPublishDate());
				calendar.add(Calendar.MONTH, 0);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				map.put("startTime", calendar.getTime());
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				map.put("endTime", calendar.getTime());
				dto.setTheMonth(Analysis.getPublishDate());
			} else {
				dto.setTheMonth(new Date());
			}
		}
		List<StrategyMonthAnalysis> monthanalysisList = dao
				.getDataList(new DataItem(StrategyMonthAnalysis.class, "queryList"), map);

		List<IndustryFactor> IndustryFactorList = dao
				.getDataList(new DataItem(StrategyMonthAnalysis.class, "IndustryList"), map);

		dto.setMonthanalysisList(monthanalysisList);
		dto.setIndustryFactorList(IndustryFactorList);
		return dto;
	}

}
