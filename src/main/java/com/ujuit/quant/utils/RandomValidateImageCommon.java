package com.ujuit.quant.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.ujuit.quant.user.constants.ValidateType;

/**
 * 图形验证码
 * 
 * @author cly</a>
 * @date 2017年3月13日
 * @company 有据信息技术有限公司
 * @version 1.0
 */
public final class RandomValidateImageCommon {

	/**
	 * 前缀
	 */
	private static String key_prefix="image_validate_";
	
	/**设置随机数字
	 * @param request
	 * @param randomString 随机数字
	 * @return
	 * @date 2017年3月13日
	 * @author  cly
	 */
	public static void setRandomNumber (HttpServletRequest request,HttpServletResponse response ,ValidateType validateType){
		GenerateRandomImage gri=new GenerateRandomImage();
		 HttpSession session = request.getSession();
        session.removeAttribute(key_prefix+validateType.getKey());
        session.setAttribute(key_prefix+validateType.getKey(), gri.getRandcode(request  ,response).toUpperCase() );
        gri=null;
	}
 
	/**验证验证码 （注意只有一次验证后就会消失）
	 * @param request
	 * @param code
	 * @return
	 * @date 2017年3月13日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	public  static boolean  validatePersistentResponse(HttpServletRequest request,
			String code,ValidateType validateType){
		return validate(request,code ,false,validateType);
	}
	
	/**验证
	 * @param request
	 * @param code
	 * @param flag -false持久  true 只验证一次
	 * @return
	 * @date 2017年3月13日
	 * @author cly
	 */
	private  static boolean  validate (HttpServletRequest request,
			String code,boolean flag,ValidateType validateType){
		 HttpSession session = request.getSession();
		String random=(String) session.getAttribute(key_prefix+validateType.getKey());
		
		if(flag){
			session.removeAttribute(key_prefix+validateType.getKey());
		}
		
		if(StringUtils.isNotEmpty(code)){
			return code.equalsIgnoreCase(random);
		}
		return false;
	}
	
	/**验证验证码 （注意只有一次验证后就会消失）
	 * @param request
	 * @param code
	 * @return
	 * @date 2017年3月13日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	public static boolean  validateResponse(HttpServletRequest request,
			String  code,ValidateType validateType){
		  
        return validate(request,code ,true,validateType);
		
	}
	
	
	
}
