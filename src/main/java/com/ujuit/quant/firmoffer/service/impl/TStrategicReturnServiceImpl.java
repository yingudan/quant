package com.ujuit.quant.firmoffer.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ujuit.quant.firmoffer.constant.TStrategicType;
import com.ujuit.quant.firmoffer.dto.RiskIndicatorDto;
import com.ujuit.quant.firmoffer.dto.StrategyListDto;
import com.ujuit.quant.firmoffer.dto.TStrategicReturnDto;
import com.ujuit.quant.firmoffer.dto.TStrategyDto;
import com.ujuit.quant.firmoffer.dto.TestIndexDto;
import com.ujuit.quant.firmoffer.model.IncomeSituation;
import com.ujuit.quant.firmoffer.model.TStrategicReturn;
import com.ujuit.quant.firmoffer.model.TStrategy;
import com.ujuit.quant.firmoffer.model.TTestRecord;
import com.ujuit.quant.firmoffer.service.IncomeSituationService;
import com.ujuit.quant.firmoffer.service.TStrategicReturnService;
import com.ujuit.quant.firmoffer.service.TStrategyParamsService;
import com.ujuit.quant.firmoffer.service.TTestRecordService;
import com.ujuit.quant.utils.DateUtils;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;
import com.ujuit.sysmanager.core.mybatis.DataResult;

/**
 * @author shadow
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TStrategicReturnServiceImpl implements TStrategicReturnService {
	@Resource
	Dao dao;

	@Resource
	TTestRecordService recordService;
	@Resource
	IncomeSituationService incomesituationService;
	@Resource
	TStrategyParamsService tstrategyparamsService;

	@Override
	public Integer insert(TStrategicReturn tStrategicReturn) {
		return dao.add(new DataItem(TStrategicReturn.class, "insert"), tStrategicReturn).getId();
	}

	@Override
	public DataResult<StrategyListDto> findStrategyList(Integer userId, String searchText, Integer pageNum,
			Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchText", searchText);
		map.put("userId", userId);
		DataResult<StrategyListDto> rs = dao.getData(new DataItem(TStrategicReturn.class, "findStrategyList"), map,
				pageNum, pageSize);
		List<StrategyListDto> list = rs.getRecords();
		if (list != null && list.size() > 0) {
			TTestRecord record = new TTestRecord();
			for (StrategyListDto dto : list) {
				record = recordService.queryTestRecordByStgId(dto.getId());// c
				if (record == null) {
					continue;
				}
				dto.setEndTime(record.getTestTime());// 设置上次运作的开始时间
				dto.setTestId(record.getId());
				Integer subAccountId = tstrategyparamsService.queryGroupIdByTestId(record.getId());
				// 设置回测收益那些
				changeStrategyDto(dto, subAccountId);
				Integer nowTestId = dao.find(new DataItem(TStrategicReturn.class, "findnowTestId"), dto.getId());
				map.put("strategyId", dto.getId());
				map.put("testId", nowTestId);
				TStrategyDto thedto = dao.find(new DataItem(TStrategy.class, "findTStrategyDto"), map);
				if (thedto != null) {
					dto.setGrade(thedto.getGrade());
				}
				TestIndexDto indexDto = tstrategyparamsService.queryIndexDtoByTestId(nowTestId);
				dto.setIndexDto(indexDto);
			}
		}
		return rs;
	}

	/**
	 * 设置策略列表对象的值
	 * 
	 * @author shandowF
	 * @date 2018年1月31日
	 */
	private StrategyListDto changeStrategyDto(StrategyListDto dto, Integer subAccountId) {
		IncomeSituation situation = incomesituationService.findBySubAccountId(subAccountId);
		if (situation == null) {
			return dto;
		}
		// 这里还需要胜率和盈亏比
		if (situation.getProfit() != null) {
			dto.setProfit(String.valueOf(situation.getAccumulatedReturn()));// 回测收益率
		}
		if (situation.getAlpha() != null) {
			dto.setAlpha(String.valueOf(situation.getAlpha()));
		}
		if (situation.getShapratio() != null) {
			dto.setShapratio(String.valueOf(situation.getShapratio()));
		}
		if (situation.getMaxRetrace() != null) {
			dto.setMaxRetrace(String.valueOf(situation.getMaxRetrace()));
		}
		return dto;
	}

	@Override
	public List<TStrategicReturnDto> findTStrategicReturnList(String groupId, String strategyId, String testId,
			Long startTime, Long endTime, String datatype) {
		HashMap<String, Object> map = new HashMap<>();
		if (startTime == null) {
			startTime = System.currentTimeMillis();
		}
		if (endTime == null) {
			endTime = System.currentTimeMillis() + 1;
		}
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("datatype", datatype);
		if (StringUtils.isEmpty(groupId)) {
			map.put("status", "1");
		} else {
			map.put("groupId", groupId);
		}
		List<TStrategicReturnDto> list = dao
				.getDataList(new DataItem(TStrategicReturn.class, "findTStrategicDtoByTime"), map);
		return list;
	}

	/**
	 * @author shadow 查询mark个月
	 * @param lDate
	 * @param type
	 * @param mark
	 *            第mark个月
	 * @return
	 */
	public List<TStrategicReturnDto> Month(String strategyId, String testId, List<Date> lDate, String type,
			Integer mark) {
		List<TStrategicReturnDto> list = oneMonth(lDate, TStrategicType.yield.getValue(), strategyId, testId);
		if (mark == 3) {
			calculateTsrList(list, type, 2, strategyId, testId);
		} else if (mark == 6) {
			calculateTsrList(list, type, 2, strategyId, testId);
			calculateTsrList(list, type, 5, strategyId, testId);
		} else if (mark == 12) {
			calculateTsrList(list, type, 2, strategyId, testId);
			calculateTsrList(list, type, 5, strategyId, testId);
			calculateTsrList(list, type, 11, strategyId, testId);
		}
		return list;
	}

	/**
	 * @author shadow
	 * @return
	 */
	public List<TStrategicReturnDto> calculateTsrList(List<TStrategicReturnDto> list, String type, Integer point,
			String strategyId, String testId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();// 获取日历
		HashMap<String, Object> map = new HashMap<>();
		map.put("type", type);
		for (int i = point; i < list.size() - point; i++) {// 从第二个开始执行
			TStrategicReturnDto dto = list.get(i);
			map.put("endTime", sdf.format(dto.getTime()));
			// calendar.setTime(dto.getTime());
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - point); // 设置为上point个月
			map.put("startTime", sdf.format(calendar.getTime()));
			TStrategicReturn ts = dao.find(new DataItem(TStrategicReturn.class, "findSumTsr"), map);// 计算每个月的值
			if (ts != null) {
				if (point == 2) {
					dto.setThreeMonth(ts.getValue());
				} else if (point == 5) {
					dto.setSixMonth(ts.getValue());
				} else if (point == 11) {
					dto.setTwelveMonth(ts.getValue());
				}
			}
		}
		return list;
	}

	/**
	 * 查询一个月 这个肯定是有的
	 * 
	 * @author shadow
	 * @param lDate
	 * @param type
	 * @return
	 */
	public List<TStrategicReturnDto> oneMonth(List<Date> lDate, String type, String strategyId, String testId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		List<TStrategicReturnDto> list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		for (Date date : lDate) {
			TStrategicReturnDto dto = new TStrategicReturnDto();
			String thedate = sdf.format(date);
			map.put("thedate", thedate);
			List<TStrategicReturn> tslist = dao
					.getDataList(new DataItem(TStrategicReturn.class, "findTStrategicListByTime"), map);
			BigDecimal oneMonth = new BigDecimal("0");
			if (tslist != null && tslist.size() > 0) {
				for (TStrategicReturn strategicReturn : tslist) {
					oneMonth = oneMonth.add(strategicReturn.getValue());
				}
			}
			// dto.setTime(date);
			dto.setOneMonth(oneMonth);// 这个地方要四舍五入
			list.add(dto);
		}
		return list;
	}

	/**
	 * 做详细每个月的计算
	 * 
	 * @author shadow
	 * @param month
	 * @param date
	 * @param strategyId
	 * @param testId
	 * @return
	 */
	public BigDecimal getThevalue(Date date, String strategyId, String testId) {
		// TStrategicReturn strategicReturn =findtsrBymonth(date, strategyId,
		// testId);
		// if(strategicReturn!=null){
		// return strategicReturn.getValue();
		// }
		return null;
	}

	@Override
	public TStrategicReturn findtsrBymonth(Date date, String strategyId, String testId) throws Exception {
		String start = "2017-01";
		String end = "2017-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date dBegin = sdf.parse(start);
		Date dEnd = sdf.parse(end);
		List<Date> lDate = DateUtils.findMonth(dBegin, dEnd);
		lDate.add(dEnd);
		// List<TStrategicReturnDto> list = oneMonth(lDate,
		// TStrategicType.shap_ratio.getValue(), strategyId, testId);
		// HashMap<String, Object> map = new HashMap<>();
		//// map.put("month", month);
		// map.put("date", date);
		// map.put("strategyId", strategyId);
		// map.put("testId", testId);
		// map.put("type", TStrategicType.yield.getValue());
		// dao.find(new DataItem(TStrategicReturn.class,"findtsrBymonth"),map);
		return null;
	}

	@Override
	public RiskIndicatorDto findRiskIndicator(String groupId, String strategyId, String testId, Long startTime,
			Long endTime, String dataType) {
		RiskIndicatorDto riskIndicatorDto = new RiskIndicatorDto();
		if (TStrategicType.alpha.getValue().equals(dataType)) {
			riskIndicatorDto.setAlphaList(this.findTStrategicReturnList(groupId, strategyId, testId, startTime, endTime,
					TStrategicType.alpha.getValue()));
		}

		else if (TStrategicType.beta.getValue().equals(dataType)) {

			riskIndicatorDto.setBetaList(this.findTStrategicReturnList(groupId, strategyId, testId, startTime, endTime,
					TStrategicType.beta.getValue()));
		} else if (TStrategicType.shap_ratio.getValue().equals(dataType)) {

			riskIndicatorDto.setShapratioList(this.findTStrategicReturnList(groupId, strategyId, testId, startTime,
					endTime, TStrategicType.shap_ratio.getValue()));
		} else if (TStrategicType.sotiro_ratio.getValue().equals(dataType)) {

			riskIndicatorDto.setSotiroratioList(this.findTStrategicReturnList(groupId, strategyId, testId, startTime,
					endTime, TStrategicType.sotiro_ratio.getValue()));
		} else if (TStrategicType.infomation_ratio.getValue().equals(dataType)) {

			riskIndicatorDto.setInfomationratioList(this.findTStrategicReturnList(groupId, strategyId, testId,
					startTime, endTime, TStrategicType.infomation_ratio.getValue()));
		} else if (TStrategicType.return_volatlity.getValue().equals(dataType)) {

			riskIndicatorDto.setReturnVolatlityList(this.findTStrategicReturnList(groupId, strategyId, testId,
					startTime, endTime, TStrategicType.return_volatlity.getValue()));
		} else if (TStrategicType.max_retrace.getValue().equals(dataType)) {

			riskIndicatorDto.setMaxRetraceList(this.findTStrategicReturnList(groupId, strategyId, testId, startTime,
					endTime, TStrategicType.max_retrace.getValue()));
		}
		return riskIndicatorDto;
	}

}
