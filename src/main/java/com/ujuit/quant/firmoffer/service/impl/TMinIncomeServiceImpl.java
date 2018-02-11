package com.ujuit.quant.firmoffer.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ujuit.quant.firmoffer.constant.ChartType;
import com.ujuit.quant.firmoffer.constant.RecordType;
import com.ujuit.quant.firmoffer.constant.TradingDirectType;
import com.ujuit.quant.firmoffer.dto.ChartHisDateDto;
import com.ujuit.quant.firmoffer.dto.ChartNowDateDto;
import com.ujuit.quant.firmoffer.dto.ChartPointDto;
import com.ujuit.quant.firmoffer.dto.CostDto;
import com.ujuit.quant.firmoffer.dto.HisDataDto;
import com.ujuit.quant.firmoffer.dto.SaleMapDto;
import com.ujuit.quant.firmoffer.model.DailyAssets;
import com.ujuit.quant.firmoffer.model.TMinIncome;
import com.ujuit.quant.firmoffer.model.TTestRecord;
import com.ujuit.quant.firmoffer.model.TTransactionDetail;
import com.ujuit.quant.firmoffer.service.TMinIncomeService;
import com.ujuit.quant.firmoffer.service.TStrategyParamsService;
import com.ujuit.quant.firmoffer.service.TTestRecordService;
import com.ujuit.quant.utils.BusinessUtil;
import com.ujuit.quant.utils.DataUtils;
import com.ujuit.quant.utils.DateUtils;
import com.ujuit.quantcalation.api.service.CalculationApiService;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TMinIncomeServiceImpl implements TMinIncomeService {
	@Resource
	Dao dao;
	@Resource
	TTestRecordService ttestRecordService;
	@Resource
	CalculationApiService calculationapiService;
	@Resource
	TStrategyParamsService tstrategyparamsService;

	@Override
	public ChartNowDateDto findChartNowDateDto(String strategyId, String testId, Long startTime, Long endTime,
			String type) {
		ChartNowDateDto dto = new ChartNowDateDto();
		if (ChartType.DAY.getValue().equals(type)) {// 为2就返回所有的跟之前一样 每一天的收益
			HashMap<String, Object> map = new HashMap<>();
			map.put("strategyId", strategyId);
			map.put("testId", testId);
			if (startTime != null) {
				map.put("startTime", new Date(startTime));
			}
			if (endTime != null) {
				map.put("endTime", new Date(endTime));
			}

			map.put("type", RecordType.nowTest.getValue());
			List<TMinIncome> list = dao.getDataList(new DataItem(TMinIncome.class, "findAllTMinIncomeList"), map);
			dto = saveInfo(list);

			// 计算手续费
			List<SaleMapDto> ttrDetailList = dao.getDataList(new DataItem(TTransactionDetail.class, "findSaleMap"),
					map);
			ChartPointDto costDto = new ChartPointDto();
			ChartPointDto benchCostDto = new ChartPointDto();

			List<Date> times = new ArrayList<>();
			List<BigDecimal> costValues = new ArrayList<>();
			List<BigDecimal> benchCostValues = new ArrayList<>();

			if (ttrDetailList != null && ttrDetailList.size() > 0) {

				BigDecimal benchCost = BigDecimal.ZERO;
				for (SaleMapDto transation : ttrDetailList) {
					List<TTransactionDetail> theList = transation.getList();
					BigDecimal cost = BigDecimal.ZERO;
					for (TTransactionDetail detail : theList) {
						cost = cost.add(detail.getCost());
					}
					benchCost = benchCost.add(cost);
					costValues.add(cost);
					benchCostValues.add(benchCost);
					times.add(transation.getTime());
				}
				costDto.setTimes(times);
				costDto.setValues(costValues);
				benchCostDto.setTimes(times);
				benchCostDto.setValues(benchCostValues);

			}
			dto.setCost(costDto);
			dto.setBenchCost(benchCostDto);
			return dto;
		}
		// 返回分钟
		if (ChartType.MIN.getValue().equals(type)) {
			// Date date=calculationapiService.lastOpen(new Date());
			HashMap<String, Object> map = new HashMap<>();
			map.put("strategyId", strategyId);
			map.put("testId", testId);
			map.put("type", RecordType.nowTest.getValue());
			TMinIncome tin = dao.find(new DataItem(TMinIncome.class, "findLimitIncome"), map);
			if (tin != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				map.put("startTime", sdf.format(tin.getCreateTime()) + " 00:00:00");
				map.put("endTime", sdf.format(tin.getCreateTime()) + " 23:59:59");
			}
			// 最新profit 替换 accumulated_return
			List<TMinIncome> list = dao.getDataList(new DataItem(TMinIncome.class, "findTMinIncomeListByTime"), map);
			dto = saveInfo(list);
			ChartPointDto benchmark = dto.getBenchmark();
			List<BigDecimal> benchList = benchmark.getValues();

			ChartPointDto totalBenchmarkSz50 = dto.getTotalBenchmarkSz50();
			List<BigDecimal> totalBenchmarkSz50List = totalBenchmarkSz50.getValues();

			ChartPointDto totalBenchmarkZz500 = dto.getTotalBenchmarkZz500();
			List<BigDecimal> totalBenchmarkZz500List = totalBenchmarkZz500.getValues();

			ChartPointDto accumulated = dto.getAccumulated();
			List<BigDecimal> accumulatedReturnList = accumulated.getValues();

			TMinIncome minIncome = dao.find(new DataItem(TMinIncome.class, "findDayMaxMinIncome"), map);
			if (minIncome != null) {
				BigDecimal benchmarkreturn = minIncome.getBenchmarkReturn();
				BigDecimal benchmarkSz50return = minIncome.getTotalBenchmarkSz50();
				BigDecimal benchmarkZz500return = minIncome.getTotalBenchmarkZz500();
				BigDecimal accumulatedReturn = minIncome.getAccumulatedReturn();

				accumulated.setValues(chageList(accumulatedReturnList, accumulatedReturn));
				benchmark.setValues(chageList(benchList, benchmarkreturn));
				totalBenchmarkSz50.setValues(chageList(totalBenchmarkSz50List, benchmarkSz50return));
				totalBenchmarkZz500.setValues(chageList(totalBenchmarkZz500List, benchmarkZz500return));
			}
			return dto;
		}
		return dto;
	}

	/**
	 * 减去上一日的 基准收益率
	 * 
	 * @author shandowF
	 * @date 2018年1月30日
	 */
	private List<BigDecimal> chageList(List<BigDecimal> list, BigDecimal minuend) {
		if (minuend == null) {
			return list;
		}
		if (minuend.compareTo(BigDecimal.ZERO) == 0) {
			return list;
		}
		List<BigDecimal> theList = new ArrayList<BigDecimal>();
		for (BigDecimal a : list) {
			BigDecimal bfa = a;
			a = BusinessUtil.SubMoney(bfa, minuend);
			theList.add(a);
		}
		return theList;
	}

	private ChartNowDateDto saveInfo(List<TMinIncome> list) {
		ChartNowDateDto dto = new ChartNowDateDto();
		if (list != null && list.size() > 0) {
			ChartPointDto accumulated = new ChartPointDto();// 累计收益
			ChartPointDto benchmark = new ChartPointDto();// 基准收益率
			ChartPointDto logarithmicreturn = new ChartPointDto();// 对数收益
			ChartPointDto relativereturn = new ChartPointDto();// 相对收益

			ChartPointDto benchmarkSz50 = new ChartPointDto();
			ChartPointDto totalBenchmarkSz50 = new ChartPointDto();
			ChartPointDto relativeReturnSz50 = new ChartPointDto();
			ChartPointDto benchmarkZz500 = new ChartPointDto();
			ChartPointDto totalBenchmarkZz500 = new ChartPointDto();
			ChartPointDto relativeReturnZz500 = new ChartPointDto();

			List<Date> times = new ArrayList<>();
			List<BigDecimal> accvalues = new ArrayList<>();
			List<BigDecimal> benvalues = new ArrayList<>();
			List<BigDecimal> logvalues = new ArrayList<>();
			List<BigDecimal> relvalues = new ArrayList<>();

			List<BigDecimal> benchmarkSz50Values = new ArrayList<>();
			List<BigDecimal> totalBenchmarkSz50Values = new ArrayList<>();
			List<BigDecimal> relativeReturnSz50Values = new ArrayList<>();
			List<BigDecimal> benchmarkZz500Values = new ArrayList<>();
			List<BigDecimal> totalBenchmarkZz500Values = new ArrayList<>();
			List<BigDecimal> relativeReturnZz500Values = new ArrayList<>();

			for (TMinIncome income : list) {
				times.add(income.getCreateTime());
				accvalues.add(income.getAccumulatedReturn());
				benvalues.add(income.getBenchmarkReturn());
				logvalues.add(income.getLogarithmicReturn());
				relvalues.add(income.getRelativeReturn());

				benchmarkSz50Values.add(income.getBenchmarkSz50());
				totalBenchmarkSz50Values.add(income.getTotalBenchmarkSz50());
				relativeReturnSz50Values.add(income.getRelativeReturnSz50());
				benchmarkZz500Values.add(income.getBenchmarkZz500());
				totalBenchmarkZz500Values.add(income.getTotalBenchmarkZz500());
				relativeReturnZz500Values.add(income.getRelativeReturnZz500());

				accumulated.setTimes(times);
				accumulated.setValues(accvalues);

				benchmark.setTimes(times);
				benchmark.setValues(benvalues);

				logarithmicreturn.setTimes(times);
				logarithmicreturn.setValues(logvalues);

				relativereturn.setTimes(times);
				relativereturn.setValues(relvalues);

				benchmarkSz50.setTimes(times);
				benchmarkSz50.setValues(benchmarkSz50Values);

				totalBenchmarkSz50.setTimes(times);
				totalBenchmarkSz50.setValues(totalBenchmarkSz50Values);

				relativeReturnSz50.setTimes(times);
				relativeReturnSz50.setValues(relativeReturnSz50Values);

				benchmarkZz500.setTimes(times);
				benchmarkZz500.setValues(benchmarkZz500Values);

				totalBenchmarkZz500.setTimes(times);
				totalBenchmarkZz500.setValues(totalBenchmarkZz500Values);

				relativeReturnZz500.setTimes(times);
				relativeReturnZz500.setValues(relativeReturnZz500Values);

			}

			dto.setAccumulated(accumulated);
			dto.setBenchmark(benchmark);
			dto.setLogarithmicreturn(logarithmicreturn);
			dto.setRelativereturn(relativereturn);

			dto.setBenchmarkSz50(benchmarkSz50);
			dto.setTotalBenchmarkSz50(totalBenchmarkSz50);
			dto.setRelativeReturnSz50(relativeReturnSz50);

			dto.setBenchmarkZz500(totalBenchmarkZz500);
			dto.setTotalBenchmarkZz500(totalBenchmarkZz500);
			dto.setRelativeReturnZz500(relativeReturnZz500);

		}
		return dto;
	}

	/**
	 * 历史在 income_situation 收入详情表
	 */
	@Override
	public ChartHisDateDto findHisCharDateDto(String groupId, String strategyId, String testId, Long startTime,
			Long endTime) {
		ChartHisDateDto dto = new ChartHisDateDto();
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("groupId", groupId);
		if (startTime != null) {
			map.put("startTime", new Date(startTime));
			map.put("beginDate", new Date(startTime));
		}
		if (endTime != null) {
			map.put("endTime", new Date(endTime));
			map.put("endDate", new Date(endTime));
		}
		map.put("type", RecordType.hisTest.getValue());
		if (groupId == null || "".equals(groupId)) {
			map.put("status", "1");
		}
		List<TMinIncome> list = dao.getDataList(new DataItem(TMinIncome.class, "findHisTMinIncomeList"), map);
		// 当日交易
		List<TTransactionDetail> ttrDetailList = dao
				.getDataList(new DataItem(TTransactionDetail.class, "findTransactionMap"), map);
		ChartPointDto accumulated = new ChartPointDto(); // 累计收益
		ChartPointDto benchmark = new ChartPointDto();// 基准收益率
		ChartPointDto logarithmic = new ChartPointDto();// 对数收益
		ChartPointDto relative = new ChartPointDto();// 相对收益
		ChartPointDto todayBuying = new ChartPointDto();// 当日买
		ChartPointDto todaySale = new ChartPointDto();// 当日售出
		ChartPointDto todayPftAndLoss = new ChartPointDto();// 当日亏损

		ChartPointDto benchcost = new ChartPointDto();// 累计手续费
		ChartPointDto cost = new ChartPointDto();// 手续费

		if (startTime == null) {
			startTime = System.currentTimeMillis();
		}
		if (endTime == null) {
			endTime = System.currentTimeMillis() + 1;
		}
		List<Date> dateList = DateUtils.findDates(new Date(startTime), new Date(endTime));
		List<Date> datetimes = new ArrayList<>();
		List<BigDecimal> todayPftAndLossvalues = new ArrayList<>();
		if (dateList != null && dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				map.put("time", DateUtils.sdf.format(dateList.get(i)));
				DailyAssets dAssets = dao.find(new DataItem(DailyAssets.class, "findDailyAssetsByTime"), map);
				BigDecimal e = BigDecimal.ZERO;
				if (dAssets != null) {// 优化方法
					DailyAssets bfdAssets = dao.find(new DataItem(DailyAssets.class, "findDailyAssetsBfTheTime"), map);
					if (bfdAssets != null) {
						e = dAssets.getMoney().subtract(bfdAssets.getMoney());
					} else {
						e = dAssets.getMoney();
					}
					if (i == 0) {
						todayPftAndLossvalues.add(new BigDecimal(0));
					} else {
						todayPftAndLossvalues.add(DataUtils.formateTwo(e));
					}
					datetimes.add(dateList.get(i));
				}
			}
			todayPftAndLoss.setTimes(datetimes);
			todayPftAndLoss.setValues(todayPftAndLossvalues);
		}
		if (list != null && list.size() > 0) {
			List<Date> times = new ArrayList<>();
			List<BigDecimal> accvalues = new ArrayList<>();
			List<BigDecimal> benvalues = new ArrayList<>();
			List<BigDecimal> logvalues = new ArrayList<>();
			List<BigDecimal> relvalues = new ArrayList<>();
			for (TMinIncome income : list) {
				times.add(income.getCreateTime());
				accvalues.add(income.getAccumulatedReturn());
				benvalues.add(income.getBenchmarkReturn());
				logvalues.add(income.getLogarithmicReturn());
				relvalues.add(income.getRelativeReturn());
			}
			accumulated.setTimes(times);
			benchmark.setTimes(times);
			logarithmic.setTimes(times);
			relative.setTimes(times);
			accumulated.setValues(accvalues);
			benchmark.setValues(benvalues);
			logarithmic.setValues(logvalues);
			relative.setValues(relvalues);
		}

		if (ttrDetailList != null && ttrDetailList.size() > 0) {
			List<Date> times = new ArrayList<>();
			List<BigDecimal> buyValues = new ArrayList<>();
			List<BigDecimal> saleValues = new ArrayList<>();

			for (TTransactionDetail detail : ttrDetailList) {
				times.add(detail.getTime());
				if (TradingDirectType.BUY.getValue().equals(detail.getTradingDirection())) {// 买入
					buyValues.add(detail.getAmount());
					saleValues.add(BigDecimal.ZERO);
				} else if (TradingDirectType.SALE.getValue().equals(detail.getTradingDirection())) {
					saleValues.add(detail.getAmount());
					buyValues.add(BigDecimal.ZERO);
				}
				todayBuying.setValues(buyValues);
				todaySale.setValues(saleValues);
				todayBuying.setTimes(times);
				todaySale.setTimes(times);

				// 这个地方新增手續費
				cost.setTimes(times);
			}
		}

		dto.setAccumulated(accumulated);
		dto.setBenchmark(benchmark);
		dto.setLogarithmic(logarithmic);
		dto.setRelative(relative);

		dto.setTodayBuying(todayBuying);
		dto.setTodaySale(todaySale);

		dto.setTodayPftAndLoss(todayPftAndLoss);
		return dto;
	}

	@Override
	public List<TMinIncome> findTMinIncomeMap(String groupId, String strategyId, String testId, Long startTime,
			Long endTime) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("groupId", groupId);
		if (startTime != null) {
			map.put("startTime", new Date(startTime));
		}
		if (endTime != null) {
			map.put("endTime", new Date(endTime));
		}
		if (groupId == null || "".equals(groupId)) {
			map.put("status", "1");
		}
		List<TMinIncome> list = dao.getDataList(new DataItem(TMinIncome.class, "findHisTMinIncomeList"), map);
		return list;
	}

	@Override
	public List<DailyAssets> findDailyAssetsMap(String groupId, String strategyId, String testId, Long startTime,
			Long endTime) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("groupId", groupId);
		if (startTime != null) {
			map.put("startTime", new Date(startTime));
		}
		if (endTime != null) {
			map.put("endTime", new Date(endTime));
		}
		if (groupId == null || "".equals(groupId)) {
			map.put("status", "1");
		}
		List<DailyAssets> list = dao.getDataList(new DataItem(DailyAssets.class, "findDailyAssetsMap"), map);
		com.ujuit.quant.firmoffer.model.TTestRecord testRecord = ttestRecordService.queryTestRecordById(testId);
		if (list != null && list.size() > 0) {
			DailyAssets assets = list.get(0);
			assets.setGapMoney(assets.getMoney().subtract(testRecord.getIniFunding()));
		}
		return list;
	}

	@Override
	public HisDataDto findHisDataDto(String groupId, String strategyId, String testId, Long startTime, Long endTime) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("groupId", groupId);
		if (startTime != null) {
			map.put("startTime", new Date(startTime));
		}
		if (endTime != null) {
			map.put("endTime", new Date(endTime));
		}
		if (groupId == null || "".equals(groupId)) {
			map.put("status", "1");
		}
		HisDataDto hisDataDto = new HisDataDto();
		List<DailyAssets> dailyAssetsMap = dao.getDataList(new DataItem(DailyAssets.class, "findDailyAssetsMap"), map);
		com.ujuit.quant.firmoffer.model.TTestRecord testRecord = ttestRecordService.queryTestRecordById(testId);
		if (dailyAssetsMap != null && dailyAssetsMap.size() > 0) {
			DailyAssets assets = dailyAssetsMap.get(0);
			assets.setGapMoney(assets.getMoney().subtract(testRecord.getIniFunding()));
		}
		List<TMinIncome> TMinIncomeMap = dao.getDataList(new DataItem(TMinIncome.class, "findHisTMinIncomeList"), map);
		hisDataDto.setTMinIncomeMap(TMinIncomeMap);// 历史每分钟收益概览
		hisDataDto.setDailyAssetsMap(dailyAssetsMap);//

		getByAndSaleMap(map, startTime, hisDataDto);
		return hisDataDto;
	}

	private void getByAndSaleMap(Map<String, Object> map, Long startTime, HisDataDto hisDataDto) {
		List<SaleMapDto> byAndSaleMap = new ArrayList<>();
		Date dBegin = new Date(startTime);
		Date dEnd = null;
		List<SaleMapDto> ttrDetailList = dao.getDataList(new DataItem(TTransactionDetail.class, "findSaleMap"), map);

		List<CostDto> costMap = new ArrayList<>();
		// 设置开始时间到第一个交易时间的空白区
		if (ttrDetailList != null && ttrDetailList.size() > 0) {
			dEnd = ttrDetailList.get(0).getTime();
			List<Date> list = DateUtils.findDates(dBegin, dEnd);
			List<SaleMapDto> dateList = new ArrayList<>();
			if (list != null && list.size() > 0) {
				for (Date date : list) {
					SaleMapDto dto = new SaleMapDto();
					List<TTransactionDetail> ttrList = new ArrayList<>();
					TTransactionDetail detail = new TTransactionDetail();
					detail.setAmount(BigDecimal.ZERO);
					detail.setTradingDirection(TradingDirectType.BUY.getValue());
					detail.setCost(BigDecimal.ZERO);
					ttrList.add(detail);
					dto.setList(ttrList);
					dto.setTime(date);
					dateList.add(dto);
				}
			}
			byAndSaleMap.addAll(dateList);

			BigDecimal benchCost = BigDecimal.ZERO;
			for (SaleMapDto dto : ttrDetailList) {
				CostDto costDto = new CostDto();
				costDto.setTime(dto.getTime());
				List<TTransactionDetail> theList = dto.getList();
				BigDecimal cost = BigDecimal.ZERO;
				for (TTransactionDetail detail : theList) {
					cost = cost.add(detail.getCost());
				}
				benchCost = benchCost.add(cost);
				costDto.setCost(cost);
				costDto.setBenchCost(benchCost);
				costMap.add(costDto);
			}

		}
		byAndSaleMap.addAll(ttrDetailList);
		hisDataDto.setByAndSaleMap(byAndSaleMap);
		hisDataDto.setCostMap(costMap);
	}

	@Override
	public HisDataDto findNowDataMap(TTestRecord test) {
		Date startDay = test.getTestTime();
		// Date endDay=DateUtils.TODAY;
		Integer groupId = tstrategyparamsService.queryGroupIdByTestId(test.getId());
		HisDataDto dto = new HisDataDto();
		Map<String, Object> map = new HashMap<>();
		map.put("groupId", groupId);
		map.put("testId", test.getId());
		map.put("strategyId", test.getStrategyId());
		List<DailyAssets> dailyAssetsMap = dao.getDataList(new DataItem(DailyAssets.class, "findDailyAssetsMap"), map);
		if (dailyAssetsMap != null && dailyAssetsMap.size() > 0) {
			DailyAssets assets = dailyAssetsMap.get(0);
			assets.setGapMoney(assets.getMoney().subtract(test.getIniFunding()));
		}
		dto.setDailyAssetsMap(dailyAssetsMap);
		getByAndSaleMap(map, startDay.getTime(), dto);
		return dto;
	}

}
