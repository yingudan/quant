package com.ujuit.quant.firmoffer.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ujuit.dealserver.constant.ApplayType;
import com.ujuit.dealserver.service.StockConfigApiService;
import com.ujuit.quant.bus.dto.StrategyParamValue;
import com.ujuit.quant.firmoffer.constant.RecordType;
import com.ujuit.quant.firmoffer.constant.SysCode;
import com.ujuit.quant.firmoffer.constant.TStrategicType;
import com.ujuit.quant.firmoffer.dto.DailyPositionDto;
import com.ujuit.quant.firmoffer.dto.IncomeNowSituationDto;
import com.ujuit.quant.firmoffer.dto.MixPositionDto;
import com.ujuit.quant.firmoffer.dto.ParamGroupListDto;
import com.ujuit.quant.firmoffer.dto.TTestRecordListDto;
import com.ujuit.quant.firmoffer.dto.TTestRecordWithTestDurationDto;
import com.ujuit.quant.firmoffer.dto.TransactionDto;
import com.ujuit.quant.firmoffer.dto.startStrategyDto;
import com.ujuit.quant.firmoffer.model.CustomIndicators;
import com.ujuit.quant.firmoffer.model.DailyPosition;
import com.ujuit.quant.firmoffer.model.TParamGroup;
import com.ujuit.quant.firmoffer.model.TStrategy;
import com.ujuit.quant.firmoffer.model.TTestRecord;
import com.ujuit.quant.firmoffer.model.TTransactionDetail;
import com.ujuit.quant.firmoffer.model.TWechatAttention;
import com.ujuit.quant.firmoffer.service.CustomIndicatorsService;
import com.ujuit.quant.firmoffer.service.DailyPositionService;
import com.ujuit.quant.firmoffer.service.IncomeSituationService;
import com.ujuit.quant.firmoffer.service.StrategyMonthAnalysisService;
import com.ujuit.quant.firmoffer.service.TMinIncomeService;
import com.ujuit.quant.firmoffer.service.TStrategicReturnService;
import com.ujuit.quant.firmoffer.service.TStrategyParamsService;
import com.ujuit.quant.firmoffer.service.TStrategyService;
import com.ujuit.quant.firmoffer.service.TTestRecordService;
import com.ujuit.quant.firmoffer.service.TTransactionDetailService;
import com.ujuit.quant.firmoffer.service.TWechatAttentionService;
import com.ujuit.quant.sdk.service.SdkService;
import com.ujuit.quant.user.model.TUserModel;
import com.ujuit.quant.utils.ExportExcleUtils;
import com.ujuit.quant.utils.MemCacheCommonUtil;
import com.ujuit.quant.utils.MemberUtil;
import com.ujuit.quant.utils.RealReference;
import com.ujuit.quant.wechat.util.HttpUtil;
import com.ujuit.quant.wechat.util.SendMsgUtil;
import com.ujuit.quant.wechat.util.SignUtil;
import com.ujuit.quant.wechat.util.TicketUtil;
import com.ujuit.quantcalation.api.service.CalculationApiService;
import com.ujuit.strategysdkserver.service.StrategyTestService;
import com.ujuit.sysmanager.core.constant.SystemStatus;
import com.ujuit.sysmanager.core.model.ResultData;
import com.ujuit.sysmanager.core.mybatis.DataResult;

@RestController
@RequestMapping("/firmoffer/")
public class FirmofferController {

	private static String WORD = "wordinfo";

	private static String MARKPOLL = "markPoll";

	private static Logger log = LoggerFactory.getLogger(FirmofferController.class);
	@Resource
	TStrategicReturnService tstrategicreturnService;

	@Resource
	TTransactionDetailService ttransactiondetailService;

	@Resource
	TStrategyService tstrategyService;

	@Resource
	TTestRecordService ttestRecordService;

	@Resource
	DailyPositionService dailypositionService;

	@Resource
	IncomeSituationService incomesituationService;

	@Resource
	TMinIncomeService tminincomeService;

	@Resource
	SdkService sdkService;

	@Resource
	TStrategyParamsService tstrategyparamsService;

	@Resource
	CalculationApiService calculationapiService;

	@Resource
	TWechatAttentionService twechatattentionService;

	@Resource
	StrategyMonthAnalysisService strategymonthanalysisService;

	@Resource
	CustomIndicatorsService customIndicatorsService;

//	@Resource
//	StockConfigApiService stockconfigapiService;
	
	
	public static final BigDecimal HUNDRED = new BigDecimal(100);

	/**
	 * 策略列表接口
	 * 
	 * @author shadow
	 * @param pageSize
	 * @param pageNum
	 * @param searchText
	 * @param userId
	 * @return ResultData
	 */
	@RequestMapping(value = "findStrategyList.m")
	public ResultData findStrategyList(HttpServletRequest request, @RequestParam(required = true) Integer pageSize,
			@RequestParam(required = true) Integer pageNum, String searchText, Integer userId) {
		TUserModel user = MemberUtil.getLoginUserFromSession(request.getSession());
		if (user == null) {
			return new ResultData(SystemStatus.SUCCESS, false, "用户未登录");
		}
		return new ResultData(SystemStatus.SUCCESS,
				tstrategicreturnService.findStrategyList(user.getId(), searchText, pageNum, pageSize));
	}

	/**
	 * 实盘模拟-交易详情(实时)
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value = "findTransactionList.m")
	public ResultData findTransactionList(String groupId, String strategyId, String testId, Long beginDate,
			Long endDate, @RequestParam(required = true) Integer pageSize,
			@RequestParam(required = true) Integer pageNum) {
		return new ResultData(SystemStatus.SUCCESS, ttransactiondetailService.findTransactionList(groupId, strategyId,
				testId, beginDate, endDate, RecordType.nowTest.getValue(), pageSize, pageNum));
	}

	/**
	 * 历史回测-交易详情
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value = "findTransactionDto.m")
	public ResultData findTransactionDto(String groupId, String strategyId, String testId, Long startTime, Long endTime,
			@RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
		return new ResultData(SystemStatus.SUCCESS, ttransactiondetailService.findTransactionDto(groupId, strategyId,
				testId, startTime, endTime, RecordType.hisTest.getValue(), pageSize, pageNum));
	}

	/**
	 * 策略收益
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @return
	 */
	@RequestMapping(value = "findTStrategicReturnList.m")
	public ResultData findTStrategicReturnList(String groupId, String strategyId, String testId, Long startTime,
			Long endTime) {
		return new ResultData(SystemStatus.SUCCESS, tstrategicreturnService.findTStrategicReturnList(groupId,
				strategyId, testId, startTime, endTime, TStrategicType.yield.getValue()));
	}

	/**
	 * 停止实盘模拟 或 历史回测
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "stopTheOffer.m")
	public ResultData stopTheOffer(Integer testId, String type, HttpServletRequest request) {
		TUserModel user = MemberUtil.getLoginUserFromSession(request.getSession());
		if (user == null) {
			return new ResultData(SystemStatus.SUCCESS, false, "用户未登录");
		}
		if (testId == null) {
			return new ResultData(SystemStatus.ERROR, null, "测试记录id不能为空");
		}
		com.ujuit.quant.firmoffer.model.TTestRecord testRecord = ttestRecordService
				.queryTestRecordById(String.valueOf(testId));
		TStrategy strategy = tstrategyService.findById(testRecord.getStrategyId());
		StrategyTestService strategytestService = RealReference.realReference(strategy.getRemoteIp(),
				strategy.getRemotePort());
		if (strategytestService == null) {
			return new ResultData(SystemStatus.ERROR, null, "Dubbo服务连接失败");
		}
		int mark = strategytestService.stopTest(testId);
		if (mark == 200) {
			return new ResultData(SystemStatus.SUCCESS, null, "停止模拟成功");
		}
		return new ResultData(SystemStatus.ERROR, mark, "停止模拟失败");
		// return new ResultData(SystemStatus.SUCCESS, null, "停止模拟成功");
	}

	/**
	 * 暂停实盘模拟
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "pauseTheOffer.m")
	public ResultData pauseTheOffer(Integer testId, HttpServletRequest request) {
		TUserModel user = MemberUtil.getLoginUserFromSession(request.getSession());
		if (user == null) {
			return new ResultData(SystemStatus.SUCCESS, false, "用户未登录");
		}
		if (testId == null) {
			return new ResultData(SystemStatus.ERROR, null, "测试记录id不能为空");
		}
		com.ujuit.quant.firmoffer.model.TTestRecord testRecord = ttestRecordService
				.queryTestRecordById(String.valueOf(testId));

		if (testRecord == null) {
			return new ResultData(SystemStatus.ERROR, null, "测试记录不存在");
		}

		if (testRecord.getType() == null || testRecord.getType().equals("0")) {
			return new ResultData(SystemStatus.ERROR, null, "历史回测不能暂停");
		}
		TStrategy strategy = tstrategyService.findById(testRecord.getStrategyId());
		StrategyTestService strategytestService = RealReference.realReference(strategy.getRemoteIp(),
				strategy.getRemotePort());
		if (strategytestService == null) {
			return new ResultData(SystemStatus.ERROR, null, "Dubbo服务连接失败");
		}
		int mark = strategytestService.pauseTest(testId);
		if (mark == 200) {
			return new ResultData(SystemStatus.SUCCESS, null, "停止模拟成功");
		}
		return new ResultData(SystemStatus.ERROR, mark, "停止模拟失败");
		// return new ResultData(SystemStatus.SUCCESS, null, "停止模拟成功");
	}

	/**
	 * 恢复策略
	 * 
	 * @author shadow s= strategyTestService.stopTest(73);
	 * @return s=strategyTestService.startTest(73);
	 * @throws Exception
	 */
	@RequestMapping(value = "resumeTheOffer.m")
	public ResultData resumeTheOffer(HttpServletRequest request, Integer testId) {
		TUserModel user = MemberUtil.getLoginUserFromSession(request.getSession());
		if (user == null) {
			return new ResultData(SystemStatus.SUCCESS, false, "用户未登录");
		}
		com.ujuit.quant.firmoffer.model.TTestRecord testRecord = ttestRecordService
				.queryTestRecordById(String.valueOf(testId));
		if (testRecord == null) {
			return new ResultData(SystemStatus.ERROR, null, "测试记录不存在");
		}

		if (testRecord.getType() == null || testRecord.getType().equals("0")) {
			return new ResultData(SystemStatus.ERROR, null, "历史回测不能重启");
		}
		TStrategy strategy = tstrategyService.findById(testRecord.getStrategyId());
		StrategyTestService strategytestService = RealReference.realReference(strategy.getRemoteIp(),
				strategy.getRemotePort());
		if (strategytestService == null) {
			return new ResultData(SystemStatus.ERROR, null, "Dubbo服务连接失败");
		}
		int mark = strategytestService.startTest(testId);
		if (mark == 200) {
			return new ResultData(SystemStatus.SUCCESS, null, "恢复模拟成功");
		}
		return new ResultData(SystemStatus.ERROR, mark, "恢复模拟失败");
	}

	/**
	 * 实盘模拟基本信息
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @return
	 */
	@RequestMapping(value = "findTStrategyDto.m")
	public ResultData findTStrategyDto(String groupId, String strategyId, String testId) {
		return new ResultData(SystemStatus.SUCCESS, tstrategyService.findNowTestTStrategyDto(strategyId, testId));
	}

	/**
	 * 交易详情 实盘
	 */
	@RequestMapping(value = "findNowDetails.m")
	public ResultData findNowDetails(String groupId, String strategyId, String testId, Long startTime, Long endTime,
			@RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
		return new ResultData(SystemStatus.SUCCESS, ttransactiondetailService.findNowTtrDetail(groupId, strategyId,
				testId, startTime, endTime, pageSize, pageNum));
	}

	/**
	 * 测试记录列表
	 * 
	 * @author cly
	 * @param strategyId
	 *            策略id
	 * @param type
	 *            类型 1：实盘 2：历史
	 * @param orderType
	 *            排序类型 1 按时间排序 2 按收益率排序
	 * @param order
	 *            排序 1 asc 顺序 2 desc 倒序
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * @date 2017年4月23日 下午8:27:55
	 * @return: ResultData
	 */
	@RequestMapping(value = "findTestRecordList.m")
	public ResultData findTestRecordList(Integer strategyId, int type, Integer orderType, Integer order, int pageSize,
			int pageNum) {

		if (strategyId == null) {
			return new ResultData(SystemStatus.ERROR, null, "strategyId不能为空");
		}
		if (orderType == null) {
			orderType = 1;
		}

		if (order == null) {
			order = 2;
		}
		TStrategy tStrategy = tstrategyService.findById(strategyId);
		if (tStrategy == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略不存在");
		}

		DataResult<TTestRecordListDto> result = ttestRecordService.queryTestRecordListByStgId(strategyId,
				type == 1 ? RecordType.nowTest : RecordType.hisTest, orderType, order == 1 ? false : true, pageNum,
				pageSize);
		if (pageNum == 1) {
			String strategyStatus = tStrategy.getStatus();
			if (strategyStatus != null) {
				// 策略 正在实盘模拟或者历史回测的时候 取出 最新一条 加到列表上
				if ((type == 1 && (strategyStatus.equals("0")||strategyStatus.equals("6"))) || (type != 1 && (strategyStatus.equals("1")||strategyStatus.equals("6")))) {
					TTestRecordListDto lastRecord = ttestRecordService.findLastTestRecordByType(strategyId,
							type == 1 ? RecordType.nowTest : RecordType.hisTest);
					if (lastRecord != null) {
						IncomeNowSituationDto incomeDto = incomesituationService.findNowIncomeSituation(null,
								strategyId.toString(), lastRecord.getId().toString());
						lastRecord.setStatus(Integer.parseInt(tStrategy.getStatus()));
						if (incomeDto != null) {
							lastRecord.setAccumulatedReturn(incomeDto.getAccumulatedReturn());
						}
						result.getRecords().add(0, lastRecord);
						TParamGroup paramGroup = ttestRecordService.selectRunningGroup(lastRecord.getId());
						if (paramGroup != null) {
							lastRecord.setParamGroupId(paramGroup.getId());
						}
					}
				}
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("strategyId", tStrategy.getId());
		map.put("strategyName", tStrategy.getStrategyName());
		map.put("strategyStatus", tStrategy.getStatus());
		map.put("pageNo", result.getPageNo());
		map.put("pageSize", result.getPageSize());
		map.put("totalPage", result.getTotalPage());
		map.put("totalRecords", result.getTotalRecords());
		map.put("page", result.isPage());
		map.put("records", result.getRecords());

		return new ResultData(SystemStatus.SUCCESS, map, null);
	}

	/**
	 * 获取 测试记录详情 附带 测试时间 、 状态
	 * 
	 * @author cly
	 * @param id
	 * @return
	 * @date 2017年4月24日 下午2:31:28
	 * @return: ResultData
	 */
	@RequestMapping(value = "findTTestRecordWithDurationDto.m")
	public ResultData findTTestRecordWithDurationDto(Integer id) {
		return new ResultData(SystemStatus.SUCCESS, ttestRecordService.queryTestRecordWithDurationByPrimaryKey(id));
	}

	/**
	 * 
	 * 参数组列表
	 * 
	 * @author cly
	 * @param id
	 *            测试记录 id
	 * @param orderType
	 *            排序类型 1.回测收益 2.回测年华收益 3.基准收益 4.阿尔法 5.贝塔 6.夏普比率 7.收益波动率 8.最大回测
	 * @param order
	 *            排序 1 asc 顺序 2 desc 倒序
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * @date 2017年4月23日 下午8:42:08
	 * @return: ResultData
	 */
	@RequestMapping(value = "findParamGroupList.m")
	public ResultData findParamGroupList(Integer id, Integer orderType, Integer order, int pageSize, int pageNum) {

		if (id == null) {
			return new ResultData(SystemStatus.ERROR, null, "id不能为空");
		}
		if (order == null) {
			order = 2;
		}
		if (orderType == null) {
			orderType = 0;
		}

		TStrategy tStrategy = tstrategyService.selectByTestRecordId(id);
		if (tStrategy == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略不存在");
		}
		TTestRecordWithTestDurationDto testRecordWithTestDurationDto = ttestRecordService
				.queryTestRecordWithDurationByPrimaryKey(id);
		if (testRecordWithTestDurationDto == null) {
			return new ResultData(SystemStatus.ERROR, null, "测试记录不存在");
		}
		DataResult<ParamGroupListDto> result = ttestRecordService.queryParamGroupList(id, orderType,
				order == 1 ? false : true, pageNum, pageSize);
		Map<String, Object> map = new HashMap<>();
		map.put("strategyId", tStrategy.getId());
		map.put("strategyName", tStrategy.getStrategyName());
		map.put("strategyStatus", tStrategy.getStatus());
		map.put("pageNo", result.getPageNo());
		map.put("pageSize", result.getPageSize());
		map.put("totalPage", result.getTotalPage());
		map.put("totalRecords", result.getTotalRecords());
		map.put("page", result.isPage());
		map.put("records", result.getRecords());
		map.put("testRecord", testRecordWithTestDurationDto);
		return new ResultData(SystemStatus.SUCCESS, map, null);
	}

	/**
	 * 删除 测试记录
	 * 
	 * @author cly
	 * @param ids
	 * @return
	 * @date 2017年4月23日 下午9:15:46
	 * @return: ResultData
	 */
	@RequestMapping(value = "deleteTestRecord.m")
	public ResultData deleteTestRecord(@RequestParam String ids) {

		String[] testRecordIdArr = ids.split(",");
		if (testRecordIdArr == null || testRecordIdArr.length == 0) {
			return new ResultData(SystemStatus.ERROR, null, "请选择要删除的测试记录");
		}
		List<String> testRecordIdList = Arrays.asList(testRecordIdArr);
		boolean result = ttestRecordService.batchDeleteByPrimaryKeyList(testRecordIdList);
		if (result) {
			return new ResultData(SystemStatus.SUCCESS, null, "删除成功");
		} else {
			return new ResultData(SystemStatus.ERROR, null, "删除失败");
		}

	}

	/**
	 * 历史回测基本信息
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @return
	 */
	@RequestMapping(value = "findHisTStrategyDto.m")
	public ResultData findHisTStrategyDto(String groupId, String strategyId, String testId) {
		return new ResultData(SystemStatus.SUCCESS,
				tstrategyService.findTStrategyDto(groupId, strategyId, testId, RecordType.hisTest.getValue()));
	}

	/**
	 * 历史回测 跳过回测下一组
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @return
	 */
	@RequestMapping(value = "retNextGroup.m")
	public ResultData retNextGroup(Integer groupId) {
		int mark = 1;
		try {
			TStrategy strategy = tstrategyparamsService.queryTsByGroupId(groupId);
			StrategyTestService strategytestService = RealReference.realReference(strategy.getRemoteIp(),
					strategy.getRemotePort());
			mark = strategytestService.skipParamGroup(groupId);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultData(SystemStatus.ERROR, mark, "跳过回测失败");
		}
		if (mark == 200) {
			return new ResultData(SystemStatus.SUCCESS, null, "跳过回测成功");
		}
		return new ResultData(SystemStatus.ERROR, mark, "跳过回测失败");
	}

	/**
	 * 每日持仓
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "findDailyList.m")
	public ResultData findDailyList(String groupId, String strategyId, String testId, Long startTime, Long endTime,
			@RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
		return new ResultData(SystemStatus.SUCCESS,
				dailypositionService.findDailyList(groupId, strategyId, testId, startTime, endTime, pageSize, pageNum));
	}

	/**
	 * 实盘每日持仓
	 */
	@RequestMapping(value = "findNowDailyList.m")
	public ResultData findNowDailyList(String groupId, String strategyId, String testId, Long startTime, Long endTime,
			@RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer pageNum) {
		return new ResultData(SystemStatus.SUCCESS, dailypositionService.findNowDailyList(groupId, strategyId, testId,
				startTime, endTime, pageSize, pageNum));
	}

	/**
	 * 风险指标
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "findRiskIndicator.m")
	public ResultData findRiskIndicator(String groupId, String strategyId, String testId, Long startTime, Long endTime,
			String dataType) {
		return new ResultData(SystemStatus.SUCCESS,
				tstrategicreturnService.findRiskIndicator(groupId, strategyId, testId, startTime, endTime, dataType));
	}

	/**
	 * 历史回测-收益概览-非图表数据
	 * 
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "findHisIncomeSituation.m")
	public ResultData findHisIncomeSituation(String groupId, String strategyId, String testId, Long startTime,
			Long endTime) {
		return new ResultData(SystemStatus.SUCCESS,
				incomesituationService.findHisIncomeSituation(groupId, strategyId, testId, startTime, endTime));
	}

	/**
	 * 实盘模拟-收益概览-非图表数据
	 * 
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "findNowIncomeSituation.m")
	public ResultData findNowIncomeSituation(String groupId, String strategyId, String testId) {
		return new ResultData(SystemStatus.SUCCESS,
				incomesituationService.findNowIncomeSituation(groupId, strategyId, testId));
	}

	/**
	 * 每日持仓
	 * 
	 * @param groupId
	 * @param strategyId
	 * @param testId
	 * @return
	 */
	@RequestMapping(value = "findPosition.m")
	public ResultData findPosition(String groupId, String strategyId, String testId) {
		return new ResultData(SystemStatus.SUCCESS, incomesituationService.findPosition(groupId, strategyId, testId));
	}

	/**
	 * 实盘模拟-收益概览-图表数据
	 * 
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "findNowCharDate.m")
	public ResultData findNowCharDate(String strategyId, String testId, Long startTime, Long endTime, String type) {
		if (StringUtils.isEmpty(type)) {
			return new ResultData(SystemStatus.ERROR, null, "实盘模拟图表type不能为空");
		}
		return new ResultData(SystemStatus.SUCCESS,
				tminincomeService.findChartNowDateDto(strategyId, testId, startTime, endTime, type));
	}

	/**
	 * 实盘模拟-每日盈亏等
	 */
	@RequestMapping(value = "findNowDataMap.m")
	public ResultData findNowDataMap(String testId) {
		if (testId == null) {
			return new ResultData(SystemStatus.ERROR, null, "testId不能为空");
		}
		TTestRecord test = ttestRecordService.queryTestRecordById(testId);
		if(test==null){
			return new ResultData(SystemStatus.ERROR, null, "该条测试记录不存在");
		}
		return new ResultData(SystemStatus.SUCCESS, tminincomeService.findNowDataMap(test));
	}

	/**
	 * 历史回测-收益概览-图表数据
	 * 
	 * @author shadow
	 * @param strategyId
	 * @param testId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "findHisCharDateDto.m")
	public ResultData findHisCharDateDto(String groupId, String strategyId, String testId, Long startTime,
			Long endTime) {
		return new ResultData(SystemStatus.SUCCESS,
				tminincomeService.findHisCharDateDto(groupId, strategyId, testId, startTime, endTime));
	}

	/**
	 * @author shadow 详情-实盘模拟参数
	 * @param strategyId
	 * @return
	 */
	@RequestMapping(value = "findParams.m")
	public ResultData findNowParams(String strategyId,String testId) {
		
		return new ResultData(SystemStatus.SUCCESS, tstrategyparamsService.findParams(strategyId,testId));
	}

	/**
	 * 根据testId查询组Id
	 */
	@RequestMapping(value = "queryGroupId.m")
	public ResultData queryGroupId(Integer testId) {
		if (testId == null) {
			return new ResultData(SystemStatus.ERROR, null, "testId不能为空");
		}
		Integer groupId = tstrategyparamsService.queryGroupIdByTestId(testId);
		return new ResultData(SystemStatus.SUCCESS, groupId);
	}

	/**
	 * 开始策略
	 * 
	 * @author shadow s= strategyTestService.stopTest(73);
	 * @return s=strategyTestService.startTest(73);
	 * @throws Exception
	 */
	@RequestMapping(value = "startStrategy.m")
	public ResultData startStrategy(HttpServletRequest request, @RequestBody startStrategyDto dto) {
		TUserModel user = MemberUtil.getLoginUserFromSession(request.getSession());
		if (user == null) {
			return new ResultData(SystemStatus.SUCCESS, false, "用户未登录");
		}
		com.ujuit.quant.bus.model.TTestRecord record = packingPojo(dto.getIniFunding(), dto.getStartTime(),
				dto.getEndTime(), dto.getType(), dto.getStrategyId(), dto.getFrequency(),dto.getTestId());
		int a = 1;
		int testrecode = 1;
		try {
			testrecode = sdkService.startStrategy(dto.getList(), record, user.getId(), dto.getIniFunding());
			TStrategy strategy = tstrategyService.findById(record.getStrategyId());
			StrategyTestService strategytestService = RealReference.realReference(strategy.getRemoteIp(),
					strategy.getRemotePort());
			a = strategytestService.startTest(testrecode);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultData(SystemStatus.ERROR, null, "系统错误");
		}
		if (a == SysCode.OK) {// 在这个地方添加关注
			boolean flag = twechatattentionService.addRushWechatAttention(Integer.valueOf(dto.getStrategyId()));
			return new ResultData(SystemStatus.SUCCESS, testrecode, "启动成功");
		} else if (a == SysCode.TIME_OUT) {
			return new ResultData(SystemStatus.ERROR, null, "启动失败,连接策略服务器超时");
		} else if (a == SysCode.STRATEGY_NOT_READY) {
			return new ResultData(SystemStatus.ERROR, null, "启动失败,策略未就绪");
		} else if (a == SysCode.TEST_RECORD_NOT_FOUND) {
			return new ResultData(SystemStatus.ERROR, null, "启动失败,策略测试记录未找到");
		} else if (a == SysCode.STRATEGY_NOT_FOUND) {
			return new ResultData(SystemStatus.ERROR, null, "启动失败,策略未找到");
		} else if (a == SysCode.PARAM_GROUP_NOT_FOUND) {
			return new ResultData(SystemStatus.ERROR, null, "启动失败,测试记录参数组未找到");
		} else if (a == SysCode.STRATEGY_SERVER_DISCONNECT) {
			return new ResultData(SystemStatus.ERROR, null, "启动失败,策略服务器已掉线");
		}
		return new ResultData(SystemStatus.ERROR, a, "启动失败,系统原因");
	}

	public com.ujuit.quant.bus.model.TTestRecord packingPojo(BigDecimal iniFunding, Long startTime, Long endTime,
			String type, String strategyId, String frequency,Integer testId) {
		com.ujuit.quant.bus.model.TTestRecord record = new com.ujuit.quant.bus.model.TTestRecord();
		if (endTime != null) {
			record.setHisDataEnd(new Date(endTime));
		}
		if (startTime != null) {
			record.setHisDataStart(new Date(startTime));
		}
		if ("1".equals(record.getType())) {
			record.setHisDataStart(new Date());
		}
		record.setTestTime(new Date());
		record.setIniFunding(iniFunding);
		record.setStrategyId(Integer.valueOf(strategyId));
		record.setFrequency(frequency);
		record.setType(type);
		record.setId(testId);
		return record;
	}

	@RequestMapping(value = "getHisBacktoTake.m")
	public ResultData getHisBacktoTake(@RequestBody startStrategyDto dto) {
		if (dto.getList() == null || dto.getList().size() < 1) {
			return new ResultData(SystemStatus.ERROR, null, "测试参数组不能为空");
		}
		for (StrategyParamValue spv : dto.getList()) {
			if (spv.getType() == 1) {
				if ("0".equals(spv.getStepValue().toString())) {
					return new ResultData(SystemStatus.ERROR, null, "步长不能为0");
				}
			}
		}
		try {
			int x = calculationapiService.transactionCount(new Date(dto.getStartTime()), new Date(dto.getEndTime()));
			if (x == 0) {
				return new ResultData(SystemStatus.ERROR, null, "所选时间区间内没有交易日");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultData(SystemStatus.SUCCESS, tstrategyService.getHisBacktoTake(dto));
	}

	/**
	 * 当日买卖 图表数据
	 */
	@RequestMapping(value = "findByAndSaleMap.m")
	public ResultData findByAndSaleMap(String groupId, String strategyId, String testId, Long startTime, Long endTime) {
		return new ResultData(SystemStatus.SUCCESS,
				ttransactiondetailService.findByAndSaleMap(groupId, strategyId, testId, startTime, endTime));
	}

	/**
	 * 历史回测所有图表
	 * 
	 * @return
	 */
	@RequestMapping(value = "findHisDataMap.m")
	public ResultData findHisDataMap(String groupId, String strategyId, String testId, Long startTime, Long endTime) {
		return new ResultData(SystemStatus.SUCCESS,
				tminincomeService.findHisDataDto(groupId, strategyId, testId, startTime, endTime));
	}

	/**
	 * 收益 图表数据
	 */
	@RequestMapping(value = "findTMinIncomeMap.m")
	public ResultData findTMinIncomeMap(String groupId, String strategyId, String testId, Long startTime,
			Long endTime) {
		return new ResultData(SystemStatus.SUCCESS,
				tminincomeService.findTMinIncomeMap(groupId, strategyId, testId, startTime, endTime));
	}

	/**
	 * 每日盈亏 图表
	 */
	@RequestMapping(value = "findDailyAssetsMap.m")
	public ResultData findDailyAssetsMap(String groupId, String strategyId, String testId, Long startTime,
			Long endTime) {
		return new ResultData(SystemStatus.SUCCESS,
				tminincomeService.findDailyAssetsMap(groupId, strategyId, testId, startTime, endTime));
	}

	/**
	 * 查询正在跑的那组参数组id
	 */
	@RequestMapping(value = "findRunTestId.m")
	public ResultData findRunTestId(String strategyId, String type) {
		// String accessToken = TokenThread.accessToken.getToken();
		// System.out.println(accessToken);
		if (strategyId == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略id不能为空");
		}
		return new ResultData(SystemStatus.SUCCESS, tstrategyService.findRunTestId(strategyId, type));
	}

	/**
	 * 微信回调
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "weixin.do")
	public ResultData weixin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean isGet = request.getMethod().toLowerCase().equals("get");
		if (isGet) {
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				response.getOutputStream().println(echostr);
			}
			// access(request, response); //验证url真实性
		} else {
			// 进入POST聊天处理
			try {
				// 接收消息并返回消息
				// acceptMessage(request, response);
				Map<String, String> map = HttpUtil.parseXml(request);
				if (map.get("Event").equals("unsubscribe")) {// 取消关注
					twechatattentionService.deleteByOpenId(map.get("FromUserName"));
				} else if (map.get("Event").equals("subscribe")) {// 首次绑定
					String userIdStId = map.get("EventKey");
					if (userIdStId.indexOf("qrscene_") == 0) {// 判断是否包含qrscene
						userIdStId = userIdStId.replace("qrscene_", "");
					}
					String param[] = userIdStId.trim().split(",");
					if (param.length > 1) {
						List<TWechatAttention> attentions = new ArrayList<>();
						List<TStrategy> listst = tstrategyService.queryTStrategyByUserId(Integer.valueOf(param[0]),
								param[2]);// 查询用户下面的所有策略
						for (TStrategy strategy : listst) {
							TWechatAttention attention = new TWechatAttention();
							if ((strategy.getId() + "").equals(param[1])) {
								attention.setIsPush(1);
							} else {
								attention.setIsPush(0);// 默认都为关闭
							}
							attention.setOpenId(map.get("FromUserName"));
							attention.setStrategyId(strategy.getId());
							attention.setUserId(strategy.getUserId());
							attentions.add(attention);
						}
						twechatattentionService.batchInsert(attentions, 200, param[2]);
						MemCacheCommonUtil.put(MARKPOLL, param[0]);
					}
					SendMsgUtil.sendMessage(map.get("FromUserName"), "欢迎关注UJUQT有据量化平台，将可以收到策略实盘模拟调仓信号！");
				}
				// else if(map.get("Event").equals("SCAN")){//多次扫描二维码
				//
				// }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 删除策略
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteTStrategy.m")
	public ResultData deleteTStrategy(Integer id) {
		if (id == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略id不能为空");
		}
		return new ResultData(SystemStatus.SUCCESS, tstrategyService.deleteTStrategy(id));
	}

	/**
	 * 开启or关闭消息通知
	 * 
	 * @param type
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateIspush.m")
	public ResultData updateIspush(String type, String id) {
		if (id == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略id不能为空");
		}
		return new ResultData(SystemStatus.SUCCESS, twechatattentionService.updateIspush(type, id));
	}

	/**
	 * 生成二维码
	 * 
	 * @param userId
	 * @param strategyId
	 * @return
	 */
	@RequestMapping(value = "queryWechatTiket.m")
	public ResultData queryWechatTiket(String userId, String strategyId, String type, String strategyType) {
		if (strategyId == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略id不能为空");
		}
		if (userId == null) {
			return new ResultData(SystemStatus.ERROR, null, "用户id不能为空");
		}
		if (strategyType == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略类型不能为空");
		}
		if ("1".equals(type)) {
			Integer isAtten = tstrategyService.queryUserIsAttenTion(Integer.valueOf(userId), strategyType);
			if (isAtten > 0) {// 已经有关注 直接更改状态
				twechatattentionService.updateIspush("1", strategyId);
				return new ResultData(SystemStatus.SUCCESS, "1");
			} else {
				String tiket = TicketUtil.getTicket(userId, strategyId, strategyType);
				if (tiket != null) {
					return new ResultData(SystemStatus.SUCCESS, tiket);
				}
			}
		} else {
			String tiket = TicketUtil.getTicket(userId, strategyId, strategyType);
			if (tiket != null) {
				return new ResultData(SystemStatus.SUCCESS, tiket);
			}
		}
		return new ResultData(SystemStatus.ERROR, "Token失效");
	}

	@RequestMapping(value = "queryGradeDtoList.m")
	public ResultData queryGradeDtoList(String testId, String strategyId, Long startTime, Long endTime) {
		if (strategyId == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略id不能为空");
		}
		if (testId == null) {
			return new ResultData(SystemStatus.ERROR, null, "测试id不能为空");
		}
		return new ResultData(SystemStatus.SUCCESS,
				dailypositionService.queryGradeDtoList(testId, strategyId, startTime, endTime));
	}

	/**
	 * 雷达图
	 * 
	 * @param theMonth
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "querystrategymonthanalysisList.m")
	public ResultData querystrategymonthanalysisList(Long theMonth, String testId, String strategyId, String groupId)
			throws Exception {
		if (strategyId == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略id不能为空");
		}
		if (testId == null) {
			return new ResultData(SystemStatus.ERROR, null, "测试id不能为空");
		}
		return new ResultData(SystemStatus.SUCCESS,
				strategymonthanalysisService.queryRadarMap(theMonth, testId, strategyId));
	}

	@RequestMapping(value = "queryWordInfo.m")
	public ResultData queryWordInfo(HttpServletRequest req, HttpResponse res) {
		String content = null;
		try {
			content = MemCacheCommonUtil.get(WORD).toString();
			req.setAttribute("content", content);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultData(SystemStatus.ERROR, false);
		}
		return new ResultData(SystemStatus.SUCCESS, true);
	}

	@RequestMapping(value = "putWordInfo.m")
	public ResultData queryWordInfo(String wordInfo) {
		boolean flag = true;
		try {
			MemCacheCommonUtil.put(WORD, wordInfo);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return new ResultData(SystemStatus.SUCCESS, flag);
	}

	/**
	 * 最新调仓
	 * 
	 * @param testId
	 * @param strategyId
	 * @return
	 */
	@RequestMapping(value = "queryMixSpace.m")
	public ResultData queryMixSpace(String testId, String strategyId) {
		if (strategyId == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略id不能为空");
		}
		if (testId == null) {
			return new ResultData(SystemStatus.ERROR, null, "测试id不能为空");
		}
		return new ResultData(SystemStatus.SUCCESS, dailypositionService.queryMixSpace(testId, strategyId));
	}

	/**
	 * 查询时间
	 * 
	 * @param testId
	 * @param strategyId
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "queryTime.m")
	public ResultData queryTime(String testId, String strategyId, String groupId) {
		if (strategyId == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略id不能为空");
		}
		if (testId == null) {
			return new ResultData(SystemStatus.ERROR, null, "测试id不能为空");
		}
		return new ResultData(SystemStatus.SUCCESS, tstrategyService.queryTime(testId, strategyId, groupId));
	}

	public static <T> void saveExcle(String[] names, String[] key, JSONObject params, List<T> list,
			HttpServletResponse res, String fileName) throws Exception {
		// HSSFWorkbook wb = new HSSFWorkbook();
		// // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		// HSSFSheet sheet = wb.createSheet(fileName);
		// // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		// HSSFRow row = sheet.createRow((int) 0);
		// // 第四步，创建单元格，并设置值表头 设置表头居中
		// HSSFCellStyle style = wb.createCellStyle();
		// HSSFCell cell = row.createCell((short) 0);

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(fileName);
		XSSFRow row = sheet.createRow((int) 0);
		XSSFCellStyle style = wb.createCellStyle();

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		XSSFCell cell = null;

		for (int i = 0; i < names.length; i++) {
			cell = row.createCell((short) i);
			cell.setCellStyle(style);
			cell.setCellValue(names[i]);
		}

		for (int i = 0; i < list.size(); i++) {
			T t = list.get(i);
			row = sheet.createRow((int) i + 1);
			for (int j = 0; j < key.length; j++) {
				String[] ks = key[j].split(",");
				String value = "";
				for (String s : ks) {
					Method m = t.getClass().getDeclaredMethod("get" + upMethod(s));
					if (params != null) {
						JSONArray thelist = params.getJSONArray(key[j]);
						String v = m.invoke(t) + "";
						if (thelist != null) {
							for (int n = 0; n < thelist.size(); n++) {
								JSONObject map = thelist.getJSONObject(n);
								if ((map.get("name") + "").equals(m.invoke(t) + "")) {
									v = map.get("value") + "";
									break;
								}
							}
						}
						value += v;
					} else {
						value = m.invoke(t) + "";
					}
				}
				// System.out.println(names[j]+":"+value);
				row.createCell((short) j).setCellValue(value);
			}

		}
		ExportExcleUtils.export(res, fileName, wb);
	}

	// public static void main(String[] args) {
	// String[] names={"kkk","2","3"};
	// String[] key={"id,code","code"};
	//// kkk:222
	//// 2:222
	//// kkk:333
	//// 2:333
	// JSONObject params =
	// JSONObject.parseObject("{'id':[{'name':'1','value':'出售'},"
	// + "{'name':2,'value':'买入'}],'code':[{'name':'222','value':'xx'}]}");
	//// Ma p<String,List<Map<String,Object>>> params =
	//// new HashMap<>();
	//// Map<String,Object> map = new HashMap<>();
	//// map.put("name", 1);
	//// map.put("value", "出售");
	//// Map<String,Object> map1 = new HashMap<>();
	//// map1.put("name", 2);
	//// map1.put("value", "买入");
	//// List<Map<String,Object>> listParams = new ArrayList<>();
	//// listParams.add(map);
	//// listParams.add(map1);
	//// params.put(key[0], listParams);
	// List<DailyPosition> list = new ArrayList<>();
	// DailyPosition a = new DailyPosition();
	// a.setId(1);
	// a.setCode("222");
	// list.add(a);
	// DailyPosition b = new DailyPosition();
	// b.setId(2);
	// b.setCode("333");
	// list.add(b);
	// try {
	// saveExcle(names,key,params,list, null,"222");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public static String upMethod(String method) {
		if (StringUtils.isNotEmpty(method)) {
			return (method.charAt(0) + "").toUpperCase() + method.substring(1);
		}
		return null;
	}

	public static String changeDate(String chageDate) {
		if (StringUtils.isBlank(chageDate)) {
			return null;
		}
		if (chageDate.length() != 8) {
			return null;
		}
		StringBuilder builder = new StringBuilder(chageDate);
		builder.insert(4, "-");
		builder.insert(7, "-");
		return builder.toString();
	}

	/**
	 * 导出Excle
	 * 
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	@RequestMapping(value = "exportExcle.m")
	public void exportExcle(HttpServletRequest req, HttpServletResponse res, String type, String strategyId,
			String testId, Long startTime, Long endTime, Integer pageNum, Integer pageSize, String groupId, String name)
			throws Exception {
		// String[] names={"kkk","2","3"};
		// String[] key={"id,code","code"};
		// JSONObject params =
		// JSONObject.parseObject("{'id':[{'name':'1','value':'出售'},"
		// + "{'name':2,'value':'买入'}],'code':[{'name':'222','value':'xx'}]}");
		// JSONObject params=null;
		if ("1".equals(type)) {// 实盘我的持仓
			String fileName = name;
			IncomeNowSituationDto dto = incomesituationService.findNowIncomeSituation(null, strategyId, testId);
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DailyPositionDto thedplist = dto.getTheDplist();
			// if (thedplist.getTime() != null) {
			// fileName += sdf.format(thedplist.getTime());
			// }
			List<DailyPosition> dpList = thedplist.getDpList();
			String[] names = { "股票名称", "现价(元)", "成交均价(元)", "持仓数量(股)", "收益(元)" };
			String[] key = { "stockName,code", "nowPrice", "tranPrice", "positionNum", "profit" };
			saveExcle(names, key, null, dpList, res, fileName);
		} else if ("2".equals(type)) {// 实盘模拟 我的当日交易
			String fileName = name;
			IncomeNowSituationDto dto = incomesituationService.findNowIncomeSituation(null, strategyId, testId);
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			TransactionDto theTclist = dto.getTheTclist();
			// if (theTclist.getTheTime() != null) {
			// fileName += sdf.format(theTclist.getTheTime());
			// }
			List<TTransactionDetail> ttrList = theTclist.getTtrList();
			for (TTransactionDetail detail : ttrList) {
				detail.setNameAndCode(detail.getStockName() + detail.getStockCode());
			}
			// stockName,stockCode
			JSONObject params = JSONObject
					.parseObject("{'tradingDirection':[{'name':'1','value':'买入'}," + "{'name':2,'value':'卖出'}]}");
			String[] names = { "股票名称", "买卖方向 ", "成交均价(元)", "成交时间", "成交金额(元)", "交易费用(元)" };
			String[] key = { "nameAndCode", "tradingDirection", "aveTranPrice", "applayTime", "amount", "cost" };
			saveExcle(names, key, params, ttrList, res, fileName);
		} else if ("3".equals(type)) {// 实盘模拟导出【交易详情】
			// DataResult<TTransactionDetail> dr =
			// ttransactiondetailService.findNowTtrDetail(null, strategyId,
			// testId,
			// startTime, endTime, pageSize, pageNum);
			// List<TTransactionDetail> list = dr.getRecords();
			List<TTransactionDetail> list = ttransactiondetailService.findNowTtrDetailAll(null, strategyId, testId,
					startTime, endTime);
			String fileName = name;
			JSONObject params = JSONObject
					.parseObject("{'tradingDirection':[{'name':'1','value':'买入'}," + "{'name':2,'value':'卖出'}]}");
			String[] names = { "股票名称", "成交时间 ", "买卖方向", "成交均价（元）", "成交数量（股）", "成交金额（元）", "交易费用（元）" };
			String[] key = { "stockName,stockCode", "applayDate,applayTime", "tradingDirection", "aveTranPrice",
					"tranNum", "amount", "cost" };
			saveExcle(names, key, params, list, res, fileName);
		} else if ("4".equals(type)) {// 实盘模拟导出【每日持仓】
			// DailyPositionByPageDto dto =
			// dailypositionService.findNowDailyList(null, strategyId, testId,
			// startTime,
			// endTime, pageSize, pageNum);
			// List<DailyPositionDto> list = dto.getList();

			List<DailyPositionDto> list = dailypositionService.findNowDailyListAll(null, strategyId, testId, startTime,
					endTime);
			List<DailyPosition> dpList = new ArrayList<>();
			for (DailyPositionDto dpd : list) {
				if (dpd.getDpList() != null) {
					dpList.addAll(dpd.getDpList());
				}
			}
			String fileName = name;
			String[] names = { "日期", "股票名称 ", "收盘价", "持仓数量(股)", "成交价(元)", "持仓金额(元)", "收益(元)" };
			String[] key = { "time", "stockName,code", "closingPrice", "positionNum", "tranPrice", "positionPrice",
					"profit" };
			saveExcle(names, key, null, dpList, res, fileName);
		} else if ("5".equals(type)) {// 历史回测导出【交易详情】
			// DataResult<TransactionDto> dr =
			// ttransactiondetailService.findTransactionDto(groupId, strategyId,
			// testId,
			// startTime, endTime, RecordType.hisTest.getValue(), pageSize,
			// pageNum);
			// List<TransactionDto> list = dr.getRecords();

			List<TransactionDto> list = ttransactiondetailService.findTransactionDtoAll(groupId, strategyId, testId,
					startTime, endTime, RecordType.hisTest.getValue());

			List<TTransactionDetail> ttrList = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (TransactionDto dto : list) {
				if (dto.getTtrList() != null) {
					for (TTransactionDetail ttd : dto.getTtrList()) {
						if (dto.getTheTime() != null) {
							ttd.setApplayDate(sdf.format(dto.getTheTime()));
						}
					}
					ttrList.addAll(dto.getTtrList());
				}
			}
			String fileName = name;
			String[] names = { "日期", "成交时间 ", "股票名称", "买卖方向", "成交均价(元)", "成交数量(股)", "成交金额(元)", "交易费用(元)" };
			JSONObject params = JSONObject
					.parseObject("{'tradingDirection':[{'name':'1','value':'买入'}," + "{'name':2,'value':'卖出'}]}");
			String[] key = { "applayDate", "applayTime", "stockName,stockCode", "tradingDirection", "aveTranPrice",
					"tranNum", "amount", "cost" };
			saveExcle(names, key, params, ttrList, res, fileName);
		} else if ("6".equals(type)) {// 历史回测导出【每日持仓】

			// DailyPositionByPageDto dto =
			// dailypositionService.findDailyList(groupId, strategyId, testId,
			// startTime,
			// endTime, pageSize, pageNum);
			// List<DailyPositionDto> list = dto.getList();

			List<DailyPositionDto> list = dailypositionService.findDailyListAll(groupId, strategyId, testId, startTime,
					endTime);
			List<DailyPosition> dpList = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (DailyPositionDto d : list) {
				if (d.getDpList() != null) {
					for (DailyPosition ttd : d.getDpList()) {
						if (d.getTime() != null) {
							ttd.setThedate(sdf.format(d.getTime()));
						}
						dpList.addAll(d.getDpList());
					}
				}
			}
			String fileName = name;
			String[] names = { "日期", "股票名称", "收盘价(元)", "持仓数量(股)", "成交价(元)", "持仓金额(元)", "收益(元)" };
			// JSONObject params =
			// JSONObject.parseObject("{'tradingDirection':[{'name':'1','value':'买入'},"
			// + "{'name':2,'value':'卖出'}]}");
			String[] key = { "Thedate", "stockName,code", "closingPrice", "positionNum", "tranPrice", "positionPrice",
					"profit" };
			saveExcle(names, key, null, dpList, res, fileName);
		} else if ("7".equals(type)) {// 评分详情页我的持仓
			// findPosition.m
			DailyPositionDto dto = incomesituationService.findPosition(groupId, strategyId, testId);
			List<DailyPosition> dplist = dto.getDpList();
			for (DailyPosition vo : dplist) {
				if (vo.getStockName() != null) {
					vo.setStockName(vo.getStockName() + vo.getCode());
				}
			}
			String fileName = name;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// if (dto.getTime() != null) {
			// fileName += sdf.format(dto.getTime());
			// }
			String[] names = { "股票名称", "现价(元)", "成交均价(元)", "持仓数量(股)", "收益(元)" };
			// JSONObject params =
			// JSONObject.parseObject("{'tradingDirection':[{'name':'1','value':'买入'},"
			// + "{'name':2,'value':'卖出'}]}");
			String[] key = { "stockName", "nowPrice", "tranPrice", "positionNum", "profit" };
			saveExcle(names, key, null, dplist, res, fileName);
		} else if ("8".equals(type)) {// 最新调仓
			List<MixPositionDto> listMap = dailypositionService.queryMixSpace(testId, strategyId);
			String fileName = name;
			// if (listMap.get(0).getDealDate() != null) {
			// fileName += changeDate(listMap.get(0).getDealDate());
			// }
			for (MixPositionDto dto : listMap) {
				String applayType = dto.getApplayType();
				if (ApplayType.BUY.getValue().equals(applayType)) {
					String bf = dto.getBfMixPosition().multiply(HUNDRED).toString() + "%";
					dto.setBh(bf);
					BigDecimal bh = dto.getAfMixPosition().subtract(dto.getBfMixPosition()).multiply(HUNDRED);
					dto.setChanges(bh.toString() + "%->" + dto.getAfMixPosition().multiply(HUNDRED).toString() + "%");
				}
				if (ApplayType.SALE.getValue().equals(applayType)) {
					String bf = "-" + dto.getAfMixPosition().multiply(HUNDRED).toString() + "%";
					dto.setBh(bf);
					BigDecimal bh = dto.getAfMixPosition().add(dto.getBfMixPosition()).multiply(HUNDRED);
					dto.setChanges(bh.toString() + "%->" + dto.getAfMixPosition().multiply(HUNDRED).toString() + "%");
				}
			}

			String[] names = { "股票名称", "时间", "买卖方向", "成交均价(元)", "成交仓位(元)", "仓位变化(元)" };

			JSONObject params = JSONObject
					.parseObject("{'applayType':[{'name':'1','value':'买入'}," + "{'name':2,'value':'卖出'}]}");

			String[] key = { "stockName,code", "dealDate", "applayType", "dealPrice", "bh", "changes" };

			saveExcle(names, key, params, listMap, res, fileName);
		}
	}

	/**
	 *
	 * 查询用户是否被关注
	 * 
	 * @param strategyId
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "queryUserIsAttenTion.m")
	public ResultData queryUserIsAttenTion(Integer userId, String strategyType) {
		if (userId == null) {
			return new ResultData(SystemStatus.ERROR, null, "用户id不能为空");
		}
		if (strategyType == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略id不能为空");
		}
		return new ResultData(SystemStatus.SUCCESS, tstrategyService.queryUserIsAttenTion(userId, strategyType));
	}

	/**
	 * 清除轮询
	 */
	@RequestMapping(value = "removePoll.m")
	public ResultData removePoll() {
		try {
			MemCacheCommonUtil.remove(MARKPOLL);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultData(SystemStatus.SUCCESS, false);
		}
		return new ResultData(SystemStatus.SUCCESS, true);
	}

	/**
	 * 轮询接口
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "queryPoll.m")
	public ResultData queryPoll(String userId) {
		int mark = 0;
		if (MemCacheCommonUtil.get(MARKPOLL) != null && MemCacheCommonUtil.get(MARKPOLL).equals(userId)) {
			mark = 1;
		}
		return new ResultData(SystemStatus.SUCCESS, mark);
	}

	/**
	 * 查询 实盘 时间
	 * 
	 * @param testId
	 * @param strategyId
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "queryFactTime.m")
	public ResultData queryTime(String testId, String strategyId) {
		if (strategyId == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略id不能为空");
		}
		if (testId == null) {
			return new ResultData(SystemStatus.ERROR, null, "测试id不能为空");
		}
		TTestRecord test = ttestRecordService.queryTestRecordById(testId);
		if (test == null) {
			return new ResultData(SystemStatus.ERROR, null, "测试id有误，该条测试记录不存在");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("startTime", test.getTestTime());
		if (test.getEndTime() == null) {
			map.put("endTime", new Date());
		} else {
			map.put("endTime", test.getEndTime());
		}
		return new ResultData(SystemStatus.SUCCESS, map);
	}

	/**
	 * 获取资金情况
	 * 
	 * @param testId
	 * @param strategyId
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "queryAssetInfo.m")
	public ResultData queryAssetInfo(Integer strategyId) {
		if (strategyId == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略id不能为空");
		}
		Integer testId = tstrategyService.findLatestId(strategyId, "1");
		if (testId == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略未开启实盘模拟");
		}
		// 资产总量（剩余资金+总持仓价值），剩余资金，当日购买股票所用资金，当日卖出股票所用资金，当日产生的手续费
		Map<String, Object> map = dailypositionService.queryAssetInfo(testId, strategyId);
		return new ResultData(SystemStatus.SUCCESS, map);

	}

	/**
	 * 实盘持仓
	 * 
	 * @author shandowF
	 * @date 2017年12月29日
	 *
	 */
	@RequestMapping(value = "queryActualDailyList.m")
	public ResultData queryActualDailyList(Integer strategyId) {
		if (strategyId == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略id不能为空");
		}
		Integer testId = tstrategyService.findLatestId(strategyId, "1");
		if (testId == null) {
			return new ResultData(SystemStatus.ERROR, null, "策略未开启实盘模拟");
		}
		return new ResultData(SystemStatus.SUCCESS, dailypositionService.queryActualDailyList(strategyId, testId));
	}

	/**
	 * 自定义参数组
	 * 
	 * @author shadow
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "queryCustomIndicators.m")
	public ResultData findTransactionList(Integer strategyId, Integer testId, Integer groupId, Integer type) {
		if (type == null) {
			type = 0;
		}
		if (type == 1 && groupId == null) {
			return new ResultData(SystemStatus.ERROR, null, "groupId 不能为空");
		}

		if (groupId == null) {
			groupId = tstrategyparamsService.queryGroupIdByTestId(testId);
		}
		List<HashMap<String, Object>> list = new ArrayList<>();
		List<CustomIndicators> result = customIndicatorsService.findByGroupId(groupId);
		if (result != null) {
			for (CustomIndicators item : result) {
				JSONObject jsonObj = JSON.parseObject(item.getIndicators());
				for (java.util.Map.Entry<String, Object> entry : jsonObj.entrySet()) {
					HashMap<String, Object> map = new HashMap<>();
					map.put("name", entry.getKey());
					map.put("val", entry.getValue());
					list.add(map);
				}
			}
		}
		return new ResultData(SystemStatus.SUCCESS, list);
	}

	/**
	 * 用于实盘更改用户金额
	 * 
	 * @author shandowF
	 * @date 2018年2月2日
	 */
	@RequestMapping(value = "editUserAmount.m")
	public ResultData editUserAmount(Integer userId, BigDecimal amount) {
		if (userId == null) {
			return new ResultData(SystemStatus.ERROR, null, "用户id不能为空");
		}
		if (amount == null) {
			return new ResultData(SystemStatus.ERROR, null, "改变金额不能为空");
		}
		boolean key = false;
//		key = stockconfigapiService.editUserAmount(userId, amount);
		if (key) {
			return new ResultData(SystemStatus.SUCCESS, true);
		}
		return new ResultData(SystemStatus.ERROR, false, "更改金额失败");
	}
}
