package com.ujuit.quant.firmoffer.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
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

import com.ujuit.quant.firmoffer.dto.SaleMapDto;
import com.ujuit.quant.firmoffer.dto.TransactionDto;
import com.ujuit.quant.firmoffer.model.TTransactionDetail;
import com.ujuit.quant.firmoffer.service.TTransactionDetailService;
import com.ujuit.quant.utils.DateUtils;
import com.ujuit.quant.utils.PageModel;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;
import com.ujuit.sysmanager.core.mybatis.DataResult;

/**
 * @author shadow
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TTransactionDetailServiceImpl implements TTransactionDetailService {
	@Resource
	Dao dao;

	@Override
	public Integer insert(TTransactionDetail tTransactionDetail) {
		return dao.add(new DataItem(TTransactionDetail.class, "insert"), tTransactionDetail).getId();
	}

	@Override
	public DataResult<TTransactionDetail> findTransactionList(String groupId, String strategyId, String testId,
			Long beginDate, Long endDate, String type, Integer pageSize, Integer pageNum) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat theSd = new SimpleDateFormat("yyyy-MM-dd");
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("groupId", groupId);
		if (beginDate != null) {
			map.put("beginDate", theSd.format(new Date(beginDate)));
		}
		if (endDate != null) {
			map.put("endDate", theSd.format(new Date(endDate)));
		}
		map.put("type", type);
		// List<TTransactionDetail> list = dao.getDataList(new
		// DataItem(TTransactionDetail.class, "findNowTtrDetail"),
		// map);
		DataResult<TTransactionDetail> dr = dao.getData(new DataItem(TTransactionDetail.class, "findNowTtrDetail"), map,
				pageNum, pageSize);
		List<TTransactionDetail> list = new ArrayList<>();
		if (dr != null) {
			list = dr.getRecords();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (list != null && list.size() > 0) {
			try {
				for (TTransactionDetail detail : list) {
					detail.setTime(sdf.parse(detail.getApplayDate() + " " + detail.getApplayTime()));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dr;
	}

	@Override
	public DataResult<TransactionDto> findTransactionDto(String groupId, String strategyId, String testId,
			Long startTime, Long endTime, String type, Integer pageSize, Integer pageNum) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (startTime != null) {
			map.put("startTime", sdf.format(new Date(startTime)));
		}
		if (endTime != null) {
			map.put("endTime", sdf.format(new Date(endTime)));
		}
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("type", type);// 历史回测
		map.put("groupId", groupId);
		if (groupId == null || "".equals(groupId)) {
			map.put("status", "1");
		}
		DataResult<TransactionDto> dr = dao.getData(new DataItem(TTransactionDetail.class, "findHisTransactionList"),
				map, pageNum, 50);// 根据金松需求修改
		// = new DataResult<>();
		// List<TransactionDto> list = dao.getDataList(new
		// DataItem(TTransactionDetail.class, "findHisTransactionList"),
		// map);
		// dr.setPageSize(pageSize);
		// dr.setTotalRecords(list.size());
		// PageModel pm = new PageModel(list, pageSize);
		// list = pm.getObjects(pageNum);
		// dao.getData(new DataItem(TTransactionDetail.class,
		// "findHisTransactionList"), map,
		// pageNum, pageSize);
		// if(dr!=null){
		// list=dr.getRecords();
		// }
		if (dr.getRecords() != null && dr.getRecords().size() > 0) {
			for (TransactionDto dto : dr.getRecords()) {
				if (dto.getTtrList() != null && dto.getTtrList().size() > 0) {
					dto.setSum(dto.getTtrList().size());
					BigDecimal buying = new BigDecimal("0");
					BigDecimal sale = new BigDecimal("0");
					for (TTransactionDetail detail : dto.getTtrList()) {
						if ("1".equals(detail.getTradingDirection())) {// 1买入
																		// 2卖出
							buying = buying.add(detail.getAmount());
						} else if ("2".equals(detail.getTradingDirection())) {
							sale = sale.add(detail.getAmount());
						}
					}
					dto.setBuying(buying);
					dto.setSale(sale);
				}
			}
		}
		// dr.setRecords(list);
		return dr;
	}

	@Override
	public List<SaleMapDto> findByAndSaleMap(String groupId, String strategyId, String testId, Long startTime,
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
			map.put("status", 1);
		}
		Date dBegin = new Date(startTime);
		Date dEnd = null;
		List<SaleMapDto> TheList = new ArrayList<>();
		List<SaleMapDto> ttrDetailList = dao.getDataList(new DataItem(TTransactionDetail.class, "findSaleMap"), map);
		if (ttrDetailList != null && ttrDetailList.size() > 0) {
			dEnd = ttrDetailList.get(0).getTime();
			List<Date> list = DateUtils.findDates(dBegin, dEnd);
			List<SaleMapDto> dateList = new ArrayList<>();
			if (list != null && list.size() > 0) {
				for (Date date : list) {
					SaleMapDto dto = new SaleMapDto();
					List<TTransactionDetail> ttrList = new ArrayList<>();
					TTransactionDetail detail = new TTransactionDetail();
					detail.setAmount(new BigDecimal(0));
					detail.setTradingDirection("1");
					ttrList.add(detail);
					dto.setList(ttrList);
					dto.setTime(date);
					dateList.add(dto);
				}
			}
			TheList.addAll(dateList);
		}

		TheList.addAll(ttrDetailList);
		// List<Date> lDate = DateUtils.findDates(dBegin, dEnd);
		// .getDataList(new DataItem(TTransactionDetail.class,
		// "findTransactionMap"), map);

		return TheList;
	}

	@Override
	public DataResult<TTransactionDetail> findNowTtrDetail(String groupId, String strategyId, String testId,
			Long startTime, Long endTime, Integer pageSize, Integer pageNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", groupId);
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		SimpleDateFormat thesdf = new SimpleDateFormat("yyyy-MM-dd");
		if (startTime != null) {
			map.put("startTime", thesdf.format(new Date(startTime)));
		}
		if (endTime != null) {
			map.put("endTime", thesdf.format(new Date(endTime)));
		}
		// List<TTransactionDetail> list = dao.getDataList(new
		// DataItem(TTransactionDetail.class, "findNowTtrDetail"),
		// map);
		DataResult<TTransactionDetail> dr = dao.getData(new DataItem(TTransactionDetail.class, "findNowTtrDetail"), map,
				pageNum, pageSize);
		return dr;
	}

	@Override
	public List<TTransactionDetail> findNowTtrDetailAll(String groupId, String strategyId, String testId,
			Long startTime, Long endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", groupId);
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		SimpleDateFormat thesdf = new SimpleDateFormat("yyyy-MM-dd");
		if (startTime != null) {
			map.put("startTime", thesdf.format(new Date(startTime)));
		}
		if (endTime != null) {
			map.put("endTime", thesdf.format(new Date(endTime)));
		}
		List<TTransactionDetail> dr = dao.getDataList(new DataItem(TTransactionDetail.class, "findNowTtrDetail"), map);
		return dr;
	}

	@Override
	public List<TransactionDto> findTransactionDtoAll(String groupId, String strategyId, String testId, Long startTime,
			Long endTime, String type) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (startTime != null) {
			map.put("startTime", sdf.format(new Date(startTime)));
		}
		if (endTime != null) {
			map.put("endTime", sdf.format(new Date(endTime)));
		}
		map.put("strategyId", strategyId);
		map.put("testId", testId);
		map.put("type", type);// 历史回测
		map.put("groupId", groupId);
		if (groupId == null || "".equals(groupId)) {
			map.put("status", "1");
		}
		List<TransactionDto> list = dao.getDataList(new DataItem(TTransactionDetail.class, "findHisTransactionList"),
				map);// 根据金松需求修改
		if (list != null && list.size() > 0) {
			for (TransactionDto dto : list) {
				if (dto.getTtrList() != null && dto.getTtrList().size() > 0) {
					dto.setSum(dto.getTtrList().size());
					BigDecimal buying = new BigDecimal("0");
					BigDecimal sale = new BigDecimal("0");
					for (TTransactionDetail detail : dto.getTtrList()) {
						if ("1".equals(detail.getTradingDirection())) {// 1买入
																		// 2卖出
							buying = buying.add(detail.getAmount());
						} else if ("2".equals(detail.getTradingDirection())) {
							sale = sale.add(detail.getAmount());
						}
					}
					dto.setBuying(buying);
					dto.setSale(sale);
				}
			}
		}
		return list;
	}

}
