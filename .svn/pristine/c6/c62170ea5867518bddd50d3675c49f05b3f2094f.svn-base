package com.ujuit.quant.sdk.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ujuit.dealserver.service.StockConfigApiService;
import com.ujuit.quant.bus.dto.StrategyParamValue;
import com.ujuit.quant.bus.model.TRecordParam;
import com.ujuit.quant.bus.model.TTestRecord;
import com.ujuit.quant.bus.service.BusTParamGroupService;
import com.ujuit.quant.bus.service.BusTRecordParamService;
import com.ujuit.quant.bus.service.BusTSubAccountService;
import com.ujuit.quant.bus.service.BusTTestRecordService;
import com.ujuit.quant.bus.utils.CommonUtil;
import com.ujuit.quant.firmoffer.model.TStrategy;
import com.ujuit.quant.firmoffer.service.TStrategyParamsService;
import com.ujuit.quant.firmoffer.service.TStrategyService;
import com.ujuit.quant.sdk.service.SdkService;

/**
 * @Description
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @date 2017年4月19日
 * @company 有据信息技术有限公司
 * @version 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SdkServiceImpl implements SdkService {

	@Resource
	BusTTestRecordService ttestRecordService;
	@Resource
	BusTSubAccountService tsubAccountService;
	@Resource
	BusTRecordParamService trecordParamService;
	@Resource
	BusTParamGroupService tparamGroupService;
	@Resource
	TStrategyService tstrategyService;
	@Resource
	StockConfigApiService stockconfigapiService;
	@Resource
	TStrategyParamsService tstrategyparamsService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public int startStrategy(List<StrategyParamValue> pv, TTestRecord resord, int userId, BigDecimal moneyTotal)
			throws Exception {
		resord.setIniFunding(moneyTotal);

		TStrategy strategy = tstrategyService.findById(resord.getStrategyId());

		if (strategy == null) {
			throw new Exception("没有找到你创建的策略。");
		}

		if (strategy.getStatus().equals("3") || strategy.getStatus().equals("6")) {
			if (strategy.getStatus().equals("6")) {
				Integer subAccountId = tstrategyparamsService.queryGroupIdByTestId(resord.getId());
				stockconfigapiService.editUserAmount(subAccountId, moneyTotal);
			}
		} else {
			throw new Exception("该策略正在运行中，不能再次启动。");
		}

		// else if(!strategy.getStatus().equals("3")){
		// throw new Exception("该策略正在运行中，不能再次启动。");
		// }

		// 添加t_test_record
		if (resord.getId() == null) {
			ttestRecordService.insert(resord);
		} else {
			ttestRecordService.update(resord);
		}
		// stockconfigapiService.editUserAmount(userId, moneyTotal);
		// strategyId
		// 添加参数

		// 产生需要运行的参数
		for (StrategyParamValue p : pv) {

			if (StringUtils.isEmpty(p.getFieldValue())) {// 过滤空
				throw new Exception("固定值不能为空");
			}

			if (p.getType() == 0) {// 0，固定 1循环

				List<BigDecimal> l = CommonUtil.commaToNumber(p.getFieldValue());
				if (l.size() > 1) {
					// 多个固定值
					p.setList(l);
				} else if (l.size() == 0) {
					throw new Exception("固定值不能为空");
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

		getTrecordParamList(new ArrayList<TRecordParam>(), pv, 0, resord.getId(), userId, moneyTotal);
		// 调用SDK服务进行通知

		// int result=strategyTestService.startTest( resord.getId() );

		return resord.getId();
	}

	/*
	 * public void getTrecordParamList1(List<TRecordParam>
	 * list,List<StrategyParamValue> pv, int id,int paramGroupId ){
	 * 
	 * 
	 * for (int i = 0; i < pv.size(); i++) {
	 * 
	 * }
	 * 
	 * 
	 * 
	 * for (StrategyParamValue p : pv) { if(p.getId()!=id){//过滤掉当前的ID
	 * 
	 * TRecordParam param=new TRecordParam(); param.setGroupId(paramGroupId);
	 * param.setParamId(p.getId());
	 * 
	 * if(p.getValue() !=null){//0，固定 1循环
	 * 
	 * 
	 * param.setParamValue(p.getValue().toString()); list.add(param);
	 * 
	 * 
	 * 
	 * } else { //多个固定产生 for (BigDecimal d : p.getList()) { param=new
	 * TRecordParam(); param.setGroupId(paramGroupId);
	 * param.setParamId(p.getId()); param.setParamValue(d.toString());
	 * list.add(param); }
	 * 
	 * } } }
	 * 
	 * 
	 * }
	 */

	/**
	 * @param list
	 *            -已经组合好的数据
	 * @param pv
	 *            -需要便利的数据
	 * @param nowSet
	 *            -当前开始便利的步长
	 * @date 2017年4月19日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void getTrecordParamList(List<TRecordParam> oldList, List<StrategyParamValue> pv, int nowSet, int testId,
			int userId, BigDecimal moneyTotal) throws Exception {

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
				// 测试参数组
				int groupId = tparamGroupService.insert(testId);
				// 添加账户
				tsubAccountService.insert(groupId, userId, moneyTotal);
				for (TRecordParam l : newList) {
					l.setGroupId(groupId);
				}
				trecordParamService.insert(newList, groupId);
			} else {

				getTrecordParamList(newList, pv, nowSet + 1, testId, userId, moneyTotal);

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
					// 测试参数组
					int groupId = tparamGroupService.insert(testId);
					// 添加账户
					tsubAccountService.insert(groupId, userId, moneyTotal);
					for (TRecordParam l : list_1) {
						l.setGroupId(groupId);
					}
					trecordParamService.insert(list_1, groupId);
				} else {

					getTrecordParamList(list_1, pv, nowSet + 1, testId, userId, moneyTotal);

				}
			}

		}

	}

}
