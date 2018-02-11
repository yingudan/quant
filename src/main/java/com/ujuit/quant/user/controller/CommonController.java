package com.ujuit.quant.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ujuit.quant.utils.MemCacheCommonUtil;

/**
 * @Description 
 * 
 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
 * @date 2017年3月27日
 * @company 有据信息技术有限公司
 * @version 1.0
 */
@Controller
@RequestMapping("web/")
public class CommonController   {
 
	/**系统页面公共处理部分区域
	 * @param module
	 * @param page
	 * @return
	 * @date 2017年3月15日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	@RequestMapping(value="{module}/{page}.do", method = RequestMethod.GET)
	public String  showView(HttpServletRequest request,
			HttpServletResponse response
			,@PathVariable String  module,@PathVariable String page){
		if(MemCacheCommonUtil.get("wordinfo")!=null){
			request.setAttribute("content", MemCacheCommonUtil.get("wordinfo").toString());
		}
		return  "web/"+ module+"/"+page;
	}
	
	/**系统页面公共处理部分区域
	 * @param module
	 * @param page
	 * @return
	 * @date 2017年3月15日
	 * @author <a href="mailto:odenpan@gmail.com">Oden Pan</a>
	 */
	@RequestMapping(value="{module}/{module2}/{page}.do", method = RequestMethod.GET)
	public String  showViewChild(HttpServletRequest request,
			HttpServletResponse response
			,@PathVariable String  module,@PathVariable String  module2,@PathVariable String page){
		return   "web/"+module+"/"+  module2+"/"+page;
	}
}
