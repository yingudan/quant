/*
*@Title:  CommonUtils.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年5月15日 下午2:41:10   
 * @version 
*/
package com.ujuit.quant.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

import com.alibaba.druid.util.StringUtils;
import com.ujuit.sysmanager.core.mybatis.Dao;
import com.ujuit.sysmanager.core.mybatis.DataItem;

import ch.qos.logback.core.pattern.parser.Parser;

/*
 * TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年5月15日 下午2:41:10   
 * @version 
*/
public class CommonUtils {

	public static String convertCell(Cell cell) {
		NumberFormat formater = NumberFormat.getInstance();
		BigDecimal bigDecimal;
		formater.setGroupingUsed(false);
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			DecimalFormat df = new DecimalFormat("#.############");
			cellValue = df.format(cell.getNumericCellValue());
			// System.out.println(df.format(d));
			// bigDecimal= new
			// BigDecimal(Double.toString(cell.getNumericCellValue()));
			// cellValue = bigDecimal.toString();
			// cellValue = formater.format(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			cellValue = Boolean.valueOf(cell.getBooleanCellValue()).toString();
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			cellValue = String.valueOf(cell.getErrorCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA:// 经过公式算出来的值
			cellValue = cell.getStringCellValue();
			break;

		default:
			cellValue = "";
		}
		return cellValue.trim();
	}

	public static Map<String, String> getHeader(String sourceUrl) {
		Map<String, String> map = new HashMap<>();
		map.put("Referer", sourceUrl);
		return map;
	}

	/**
	 * 读取txt文件的内容
	 * 
	 * @param file
	 *            想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
			// BufferedReader br = new BufferedReader(new
			// FileReader(file));//构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result.append(System.lineSeparator() + s);
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	public static Date toDate(String dateString, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(dateString);
			return date;
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static Date toDateUnSafe(String dateString, String format) throws ParseException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(dateString);
			return date;
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public static String dateToString(Date date, String format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateStr = sdf.format(date);
		return dateStr;
	}

	public static String dateToString(long timestamp, String format) {
		Date date = new Date(timestamp);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateStr = sdf.format(date);
		return dateStr;
	}

	public static boolean batchInsert(Dao dao, DataItem dataItem, List list, int batchSize) {
		if (list == null || list.size() == 0) {
			return false;
		}
		List list2Insert = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			list2Insert.add(list.get(i));
			if ((i + 1) % batchSize == 0 || i == list.size() - 1) {
				dao.insert(dataItem, list2Insert);
				list2Insert.clear();
			}
		}

		return true;
	}

	public static String convertCode(String code, String exchange) {
		return code + "." + convertExchange(exchange);
	}

	public static String convertExchange(String exchangeName) {
		if (exchangeName.equals("sse"))
			return "SH";
		return "SZ";
	}

	/**
	 * 是否是同一天
	 * 
	 * @param date
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date, Date date2) {
		if (date == null || date2 == null) {
			return false;
		}
		return CommonUtils.dateToString(date, "yyyy-MM-dd").equals(CommonUtils.dateToString(date2, "yyyy-MM-dd"));
	}

	/**
	 * 返回excle上的财报日期
	 * 
	 * @param fileName
	 * @return
	 */
	public static String fileDate(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return null;
		}
		Pattern pattern = Pattern.compile("[0-9]{4}[-][0-9]{1,2}[-][0-9]{1,2}");
		Matcher matcher = pattern.matcher(fileName);
		String dateStr = null;
		if (matcher.find()) {
			dateStr = matcher.group(0);
			return dateStr.toString();
		}
		return null;
	}

	/**
	 * 还原code
	 * 
	 * @author shadow
	 * @param code
	 * @return
	 */
	public static String analysisCode(String code) {
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		String[] sourceStrArray = code.split("\\.");
		if (sourceStrArray.length > 0) {
			return sourceStrArray[0];
		}
		return null;
	}

	public static File getFileFromResoure(Class class_, String fileName) {

		ClassLoader classLoader = class_.getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;

	}
}
