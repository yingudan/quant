package com.ujuit.quant.bus.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @Description 
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @date 2017年4月19日
 * @company 有据信息技术有限公司
 * @version 1.0
 */
public final class CommonUtil {

	/**
	 * 逗号
	 */
	public final static String comma=",";
	
	
	/**字符以（,号分割）转获取数字集合
	 * @param str
	 * @return
	 * @date 2017年4月19日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 * @throws Exception 
	 */
	public   static List<BigDecimal> commaToNumber(String str) throws Exception{
		
		if (StringUtils.isEmpty(str))return null;
		
		String s[]=str.split(comma);
		 List<BigDecimal> list=new ArrayList<BigDecimal>();
		for (String string : s) {
		  if( isNumeric(string)){//包含float
				list.add(new BigDecimal(string));
			 }
		   
			
		}
		
		return list;
		
	}
	/**字符以（,号分割）转获取数字集合
	 * @param str
	 * @return
	 * @date 2017年4月19日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	public   static  BigDecimal  getNumber(String str){
		
		if (StringUtils.isEmpty(str))return null;
		
	 
		 
			 if( isNumeric(str)){//包含float
				return (new BigDecimal(str));
			 }
			
	 return null;
		
	 
		
	}
    /**
     * <p>Checks if the String contains only unicode digits.
     * A decimal point is not a unicode digit and returns false.</p>
     *
     * <p><code>null</code> will return <code>false</code>.
     * An empty String (length()=0) will return <code>true</code>.</p>
     *
     * <pre>
     * StringUtils.isNumeric(null)   = false
     * StringUtils.isNumeric("")     = true
     * StringUtils.isNumeric("  ")   = false
     * StringUtils.isNumeric("123")  = true
     * StringUtils.isNumeric("12 3") = false
     * StringUtils.isNumeric("ab2c") = false
     * StringUtils.isNumeric("12-3") = false
     * StringUtils.isNumeric("12.3") = true
     * StringUtils.isNumeric("-12.3") = true
     * StringUtils.isNumeric("11.2.3") = false
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if only contains digits, and is non-null
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        
        return str.matches("^(-?\\d+)(\\.\\d+)?$");
    }
 
    
}
