package com.ujuit.quant.firmoffer.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ujuit.quant.bus.dto.StrategyParamValue;
import com.ujuit.quant.bus.model.TRecordParam;
import com.ujuit.quant.bus.utils.CommonUtil;
import com.ujuit.quant.firmoffer.constant.RecordType;
import com.ujuit.quant.firmoffer.dto.RecordParamDto;
import com.ujuit.quant.firmoffer.dto.TStrategyDto;
import com.ujuit.quant.firmoffer.dto.startStrategyDto;
import com.ujuit.quant.firmoffer.model.DailyPosition;
import com.ujuit.quant.firmoffer.model.TParamGroup;
import com.ujuit.quant.firmoffer.model.TStrategy;
import com.ujuit.quant.firmoffer.model.TStrategyQh;
import com.ujuit.quant.firmoffer.model.TTestRecord;
import com.ujuit.quant.firmoffer.service.TStrategyService;
import com.ujuit.quant.firmoffer.service.TTestRecordService;
import com.ujuit.quantcalation.api.service.CalculationApiService;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TStrategyServiceImpl implements TStrategyService {

	@Resource(name = "dao")
	Dao dao;
	@Resource(name = "daoEEx")
	Dao daoEEx;

	@Resource
	TTestRecordService ttestrecordService;

	@Resource
	CalculationApiService calculationapiService;

	@Resource
	TTestRecordService recordService;

	@Override
	public Integer insert(TStrategy tStrategy) {
		return dao.add(new DataItem(TStrategy.class, "insert"), tStrategy).getId();
	}

	@Override
	public TStrategyDto findTStrategyDto(String groupId, String strategyId, String testId, String type) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("type", type);// 已经是历史回测
		map.put("groupId", groupId); // 根据groupId 来区分 已跑完还是在运行中
		TStrategyDto dto = dao.find(new DataItem(TStrategy.class, "findTStrategyDto"), map);
		com.ujuit.quant.firmoffer.model.TTestRecord ttr = ttestrecordService.queryTestRecordById(testId);
		if (dto != null) {
			dto.setHisOfferNum(ttestrecordService.queryOfferNum(strategyId, type));
			if (groupId == null || "".equals(groupId)) {
				map.put("status", "1");
			}
			List<RecordParamDto> paramList = dao.getDataList(new DataItem(TStrategy.class, "findRecordParamlist"), map);
			if (paramList != null && paramList.size() > 0) {
				dto.setParamId(paramList.get(0).getParamId());
			}
			// 这个是查询 有多少组参数
			List<TParamGroup> theparamList = dao.getDataList(new DataItem(TStrategy.class, "findParamGrouplist"), map);
			dto.setParamList(paramList);
			Integer groupNum = 0;
			Integer overParam = 0;
			if (theparamList != null && theparamList.size() > 0) {
				groupNum = theparamList.size();// 有多少组参数
				for (TParamGroup group : theparamList) {
					if ("2".equals(group.getTestStatus()) || "3".equals(group.getTestStatus())) {
						overParam = overParam + 1;
					}
				}
			}

			int predict = 1;
			// 为交易日
			int x = 1;
			if (RecordType.hisTest.getValue().equals(type)) {
				try {
					x = calculationapiService.transactionCount(ttr.getHisDataStart(), ttr.getHisDataEnd());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if ("1".equals(ttr.getFrequency())) {// 0.TICK 1.每天
					predict = x * groupNum;
				} else if ("0".equals(ttr.getFrequency())) {
					predict = x * 48 * groupNum;
				}
				// afday 已经测完的交易日
				int afday = dao.find(new DataItem(DailyPosition.class, "findAfDay"), map);
				BigDecimal den = new BigDecimal(groupNum).multiply(new BigDecimal(x));
				BigDecimal plan = new BigDecimal("0");
				if (!den.toString().equals("0")) {
					plan = new BigDecimal(overParam).multiply(new BigDecimal(x)).add(new BigDecimal(afday)).divide(den,
							4, RoundingMode.HALF_UP);
				}
				int a = plan.compareTo(new BigDecimal(1));
				if (-1 == a) {
					dto.setTestPlan(plan);
				} else {
					dto.setTestPlan(new BigDecimal("1.00"));
				}
				dto.setPredict(predict); // 计算要多少时间(预计用时)
			}
			// dto.setTestPlan(
			// new BigDecimal(overParam).divide(new
			// BigDecimal(groupNum)).setScale(4, BigDecimal.ROUND_HALF_DOWN));
			dto.setAllParamNum(groupNum);

			// 开始时间和结束时间 实际用时 ÷ 进度 X (1-进度)

			TParamGroup overTime = dao.find(new DataItem(TParamGroup.class, "findOverTime"), map);
			if (overTime != null) {
				if (overTime.getEndTime() != null) {
					dto.setOverTime((overTime.getEndTime().getTime() - overTime.getStartTime().getTime()) / 1000);
				}
				Long ActualTime = 0l;// 实际用时
				if ("1".equals(dto.getTestStatus())) {
					ActualTime = (new Date().getTime() - overTime.getStartTime().getTime()) / 1000;
				} else {
					if (overTime.getEndTime() != null) {
						ActualTime = ((overTime.getEndTime().getTime() - overTime.getStartTime().getTime()) / 1000);
					}
				}

				dto.setActualTime(ActualTime);
				if (ActualTime != null) {
					Long oTime = null;
					if (dto.getTestPlan() != null && dto.getTestPlan().compareTo(new BigDecimal(0)) == 1) {
						BigDecimal cz = new BigDecimal(1).subtract(dto.getTestPlan());
						oTime = new BigDecimal(ActualTime).divide(dto.getTestPlan(), 2, RoundingMode.HALF_UP)
								.multiply(cz).longValue();
					}
					// new BigDecimal(ActualTime) .longValue();
					dto.setOverTime(oTime);
				}
			}
			dto.setOverParam(overParam);

		}

		return dto;
	}

	@Override
	public TStrategy findById(Integer id) {
		return dao.find(new DataItem(TStrategy.class, "findById"), id);
	}

	@Override
	public Map<String, Object> getHisBacktoTake(startStrategyDto dto) {
		Integer groupNum = 1;
		try {
			groupNum = testStartQuant2(dto.getList());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		long predict = 1;
		long x = 1;
		try {
			x = calculationapiService.transactionCount(new Date(dto.getStartTime()), new Date(dto.getEndTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("1".equals(dto.getFrequency())) {// 0.TICK 1.每天
			predict = x * groupNum;
		} else if ("0".equals(dto.getFrequency())) {
			predict = x * 48 * groupNum;
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("groupNum", groupNum);
		map.put("time", predict);
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ujuit.quant.firmoffer.service.TStrategyService#selectByTestRecordId(
	 * java.lang.Integer)
	 */
	@Override
	public TStrategy selectByTestRecordId(Integer id) {
		return dao.find(new DataItem(TStrategy.class, "selectByTestRecordId"), id);
	}

	/**
	 * 实盘时间
	 */
	@Override
	public TStrategyDto findNowTestTStrategyDto(String strategyId, String testId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("type", RecordType.nowTest.getValue());// 实时
		TStrategyDto dto = dao.find(new DataItem(TStrategy.class, "findTStrategyDto"), map);
		if (dto == null) {
			return null;
		}
		// map.put("status", "1");//设置状态为在运行中.
		List<RecordParamDto> paramList = dao.getDataList(new DataItem(TStrategy.class, "findRecordParamlist"), map);
		if (paramList != null && paramList.size() > 0) {
			dto.setParamId(paramList.get(0).getParamId());
		}
		dto.setParamList(paramList);
		Integer num = recordService.queryOfferNum(strategyId, RecordType.nowTest.getValue());
		dto.setHisOfferNum(num);
		TParamGroup overTime = dao.find(new DataItem(TParamGroup.class, "findOverTime"), map);
		if (overTime != null && overTime.getEndTime() != null) {
			dto.setStartTime(overTime.getStartTime());
		}
		return dto;
	}

	public int testStartQuant2(List<StrategyParamValue> pv) throws Exception {

		/*
		 * List<StrategyParamValue> pv =new ArrayList<StrategyParamValue>();
		 * StrategyParamValue p1=new StrategyParamValue();
		 * p1.setFieldName("name1"); p1.setFieldValue("1");
		 * p1.setParamName("名称1"); p1.setType(1); p1.setMaxValue("4");
		 * p1.setStepValue("1");; pv.add(p1);
		 * 
		 * p1=new StrategyParamValue(); p1.setFieldName("name2");
		 * p1.setFieldValue("1,3"); p1.setParamName("名称2");
		 * 
		 * pv.add(p1); TTestRecord resord=new TTestRecord();
		 * resord.setStrategyId(3); resord.setHisDataStart(new
		 * Date(System.currentTimeMillis()-360000)); resord.setHisDataEnd(new
		 * Date()); resord.setType("1"); resord.setTestTime(new Date());
		 */
		/*
		 * try { sdkService.startStrategy(pv, resord, 1, new
		 * BigDecimal(200000)); } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * System.out.println("--");
		 */

		// 产生需要运行的参数
		for (StrategyParamValue p : pv) {

			if (p.getType() == 0) {// 0，固定 1循环

				List<BigDecimal> l = CommonUtil.commaToNumber(p.getFieldValue());
				if (l.size() > 1) {
					// 多个固定值
					p.setList(l);
				} else if (l.size() == 0) {

				} else {
					p.setValue(l.get(0));
				}

			} else if (p.getType() != 0) {
				List<BigDecimal> list = new ArrayList<BigDecimal>();
				p.setList(list);
				for (BigDecimal i = CommonUtil.getNumber(p.getFieldValue()); i.compareTo(p.getMaxValue()) < 1; i = i
						.add(p.getStepValue())) {
					list.add(i);
				}

			}

		}

		try {
			return (getTrecordParamList(new ArrayList<TRecordParam>(), pv, 0));
		} catch (Exception e) {

			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public Integer findRunTestId(String strategyId, String type) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("type", type);
		Integer testId = dao.find(new DataItem(TStrategy.class, "findRunTestId"), map);
		return testId;
	}

	@Override
	public Integer findLatestId(Integer strategyId, String type) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("type", type);
		Integer testId = dao.find(new DataItem(TStrategy.class, "findLatestId"), map);
		return testId;
	}

	public int getTrecordParamList(List<TRecordParam> oldList, List<StrategyParamValue> pv, int nowSet)
			throws Exception {
		int count = 0;
		StrategyParamValue p = pv.get(nowSet);
		List<TRecordParam> newList = new ArrayList<>(pv.size());
		newList.addAll(oldList);
		boolean isOver = (nowSet == (pv.size() - 1));

		if (p.getValue() != null) {// 0，固定 1循环
			TRecordParam param = new TRecordParam();
			// param.setGroupId(paramGroupId);
			param.setFieldName(p.getFieldName());
			param.setParamName(p.getParamName());
			param.setParamValue(p.getValue().toString());
			newList.add(param);

			if (isOver) {
				count++;
			} else {

				count += getTrecordParamList(newList, pv, nowSet + 1);
			}
		} else {
			// 多个固定产生
			for (BigDecimal d : p.getList()) {
				TRecordParam param = new TRecordParam();
				List<TRecordParam> list_1 = new ArrayList<>(pv.size());
				list_1.addAll(newList);
				param.setFieldName(p.getFieldName());
				param.setParamName(p.getParamName());
				param.setParamValue(d.toString());
				list_1.add(param);
				if (isOver) {
					count++;
				} else {
					count += getTrecordParamList(list_1, pv, nowSet + 1);

				}
			}
		}
		return count;
	}

	@Override
	public boolean deleteTStrategy(int id) {
		return dao.delete(new DataItem(TStrategy.class, "deleteByPrimaryKey"), id);
	}

	@Override
	public Map<String, Object> queryTime(String testId, String strategyId, String groupId) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		param.put("strategyId", strategyId);
		param.put("testId", testId);
		param.put("groupId", groupId); // 根据groupId 来区分 已跑完还是在运行中
		TStrategyDto dto = dao.find(new DataItem(TStrategy.class, "findTStrategyDto"), param);
		if (dto != null) {
			if (dto.getStartTime() != null) {
				map.put("startTime", dto.getStartTime());
			} else {
				TParamGroup overTime = dao.find(new DataItem(TParamGroup.class, "findOverTime"), param);
				if (overTime != null) {
					map.put("startTime", overTime.getStartTime());
				}
			}
			map.put("endTime", dto.getEndTime());
		} else {
			map.put("startTime", new Date());
			map.put("endTime", new Date());
		}
		return map;
	}

	@Override
	public Integer queryUserIsAttenTion(Integer userId, String strategyType) {
		if ("2".endsWith(strategyType)) {
			Integer count = daoEEx.find(new DataItem(TStrategyQh.class, "queryUserIsAttenTion"), userId);
			if (count > 0) {
				return 1;
			}
		} else {
			Integer count = dao.find(new DataItem(TStrategy.class, "queryUserIsAttenTion"), userId);
			if (count > 0) {
				return 1;
			}
		}

		return 0;
	}

	@Override
	public List<TStrategy> queryTStrategyByUserId(Integer userId, String strategyType) {
		if ("2".endsWith(strategyType)) {
			return daoEEx.getDataList(new DataItem(TStrategyQh.class, "queryTStrategyByUserId"), userId);
		} else {
			return dao.getDataList(new DataItem(TStrategy.class, "queryTStrategyByUserId"), userId);
		}

	}

	@Override
	public List<TStrategy> queryTStrategyListByUserId(Integer userId) {
		return dao.getDataList(new DataItem(TStrategy.class, "queryTStrategyListByUserId"), userId);
	}

}
