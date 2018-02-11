package com.ujuit.quant.utils;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.ujuit.stockquotation.model.TdfMarketDataHolder;

/**
 * @Description 业务工具类
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @date 2017年8月16日
 * @company 有据信息技术有限公司
 * @version 1.0
 */
public final class BusinessUtil {

	public final static String buy = "0";
	public final static String sale = "1";

	public final static BigDecimal zero = BigDecimal.ZERO;

	private final static BigDecimal tenThousand = new BigDecimal(10000);
	private final static SimpleDateFormat f_sqlTime = new SimpleDateFormat("HH:mm:ss");

	public static Time getNowTime() {

		return java.sql.Time.valueOf(f_sqlTime.format(new Date()));
	}

	/**
	 * 是否需要处理 true 代表需要风控，false代表不需要风控
	 * 
	 * @param codeMap
	 * @param isall
	 * @param code
	 * @return
	 * @date 2017年8月17日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	public static final boolean isCode(Map<String, String> codeMap, boolean isall, String code) {
		if (isall || codeMap.containsKey(code))
			return true;
		return false;

	}

	/**
	 * @param nowTime
	 *            -当前时间
	 * @param startTime
	 *            -开始时间
	 * @param endTime
	 *            -结束时间
	 * @return true 代表需要强平 false 不需要强平
	 * @date 2017年8月16日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	public static final boolean resultType(Time nowTime, Time startTime, Time endTime) {

		if ((startTime == null || nowTime.getTime() >= startTime.getTime())
				&& (endTime == null || nowTime.getTime() <= endTime.getTime())) {

			return true;

		}
		return false;
	}

	/**
	 * 10000毫厘=1元
	 * 
	 * @param n
	 * @return
	 * @date 2017年8月17日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	public final static BigDecimal getRmbUnit(int n) {

		return getRmbUnit(new BigDecimal(n));
	}

	/**
	 * 10000毫厘=1元
	 * 
	 * @param n
	 * @return
	 * @date 2017年8月17日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	public final static BigDecimal getRmbUnit(BigDecimal n) {
		return n.divide(tenThousand);
	}

	/**
	 * bigDecimal 除法防止空对象报错
	 */
	public static final BigDecimal divide(BigDecimal a, BigDecimal b) {
		if (a == null || zero.compareTo(a) == 0) {
			return zero;
		}
		if (b == null || zero.compareTo(b) == 0) {
			return zero;
		}
		return a.divide(b, 2, BigDecimal.ROUND_HALF_UP);
	}

	public static final BigDecimal divideInt(Integer a, Integer b) {
		if (a == null) {
			return zero;
		}
		if (b == null) {
			return zero;
		}
		return divide(new BigDecimal(a), new BigDecimal(b));
	}

	/**
	 * BigDecimal 加法防止空对象报错
	 * 
	 * @author shandowF
	 * @date 2018年1月23日
	 *
	 */
	public static final BigDecimal AddMoney(BigDecimal a, BigDecimal b) {
		if (a == null) {
			a = zero;
		}
		if (b == null) {
			b = zero;
		}
		return a.add(b);
	}

	/**
	 * BigDecimal减法 防止报错
	 */
	public static final BigDecimal SubMoney(BigDecimal a, BigDecimal b) {
		if (a == null) {
			a = zero;
		}
		if (b == null) {
			b = zero;
		}
		return a.subtract(b);
	}

	/**
	 * 数字加法防止报错
	 * 
	 * @author shandowF
	 * @date 2018年1月24日
	 *
	 */
	public static final Integer addNum(Integer a, Integer b) {
		if (a == null) {
			a = 0;
		}
		if (b == null) {
			b = 0;
		}
		return a + b;
	}

	/**
	 * 数字减法防止报错
	 * 
	 * @author shandowF
	 * @date 2018年1月24日
	 *
	 */
	public static final Integer subNum(Integer a, Integer b) {
		if (a == null) {
			a = 0;
		}
		if (b == null) {
			b = 0;
		}
		return a - b;
	}

	public static final BigDecimal mulMoney(BigDecimal price, Integer num) {
		if (price == null) {
			return zero;
		}
		if (num == null || num == 0) {
			return zero;
		}
		return price.multiply(new BigDecimal(num));
	}
}
