package com.ujuit.quant.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static SimpleDateFormat thesdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 00:00:00
	 */
	public static String SATRT_TIME = " 00:00:00";
	/**
	 * 23:59:59
	 */
	public static String END_TIME = " 23:59:59";

	public static Date TODAY = new Date();

	// map.put("startTime", thesdf.parse(sdf.format(thedate) + " 00:00:00"));
	// map.put("endTime", thesdf.parse(sdf.format(thedate) + " 23:59:59"));

	/**
	 * yyyy-MM-dd 00:00:00
	 * 
	 * @author shandowF
	 * @date 2018年2月1日
	 */
	public static Date getSatrtToDay() {
		Date satrtToDay = null;
		try {
			satrtToDay = thesdf.parse(sdf.format(TODAY) + SATRT_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return satrtToDay;
	}

	/**
	 * yyyy-MM-dd 23:59:59
	 * 
	 * @author shandowF
	 * @date 2018年2月1日
	 */
	public static Date getEndToDay() {
		Date endToDay = null;
		try {
			endToDay = thesdf.parse(sdf.format(TODAY) + END_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return endToDay;
	}


	/**
	 * @author shandowF
	 * @date 2017年3月20日
	 *
	 */
	public static List<Date> findDates(Date dBegin, Date dEnd) {
		List<Date> lDate = new ArrayList<Date>();
		// lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			lDate.add(calBegin.getTime());
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
		}
		return lDate;
	}

	public static List<Date> findMonth(Date startTime, Date endTime) {

		List<Date> lDate = new ArrayList<Date>();

		Calendar calBegin = Calendar.getInstance();// 定义日期实例

		calBegin.setTime(startTime);// 设置日期起始时间

		Calendar calEnd = Calendar.getInstance();

		calEnd.setTime(endTime);

		while (endTime.after(calBegin.getTime())) {// 判断是否到结束日期

			lDate.add(calBegin.getTime());

			calBegin.add(Calendar.MONTH, 1);// 进行当前日期月份加1
		}
		return lDate;
	}

	/**
	 * 获取当前日期的前一天
	 * 
	 * @author shandowF
	 * @date 2017年11月16日
	 *
	 */
	public static Date getSpecifiedDayBefore(Date specifiedDay) {
		Calendar c = Calendar.getInstance();
		c.setTime(specifiedDay);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		return c.getTime();
	}

	/**
	 * 返回当日时间
	 * 
	 * @author shandowF
	 * @date 2018年2月1日
	 * @return yyyy-MM-dd
	 */
	public static String getToDay() {
		return sdf.format(TODAY);
	}
}
