/*
*@Title:  LoginFilter.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年5月4日 上午10:01:08   
 * @version 
*/
package com.ujuit.quant.filter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.ujuit.quant.user.model.TUserModel;
import com.ujuit.quant.user.service.TUserService;
import com.ujuit.quant.utils.MemberUtil;
import com.ujuit.sysmanager.core.constant.SystemStatus;
import com.ujuit.sysmanager.core.model.ResultData;
import com.ujuit.sysmanager.util.Res;
import com.ujuit.sysmanager.util.ResponseUtil;

/*
 * 登陆拦截器
 * @author: cly     
 * @date:   2017年5月4日 上午10:01:08   
 * @version 
*/
public class LoginFilter implements HandlerInterceptor{
	public static String WEB_QUANT = "web_quant";
	public static String WEB_DATAOFFER = "web_dataoffer";
	public static String WEB_QUANTFUTURE = "web_quantfuture";
	@Resource
	private TUserService userService;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();  
		
		request.setAttribute(WEB_QUANT,Res.getProperties("webquantkey") );
		request.setAttribute(WEB_DATAOFFER,Res.getProperties("webdataofferkey") );
		request.setAttribute(WEB_QUANTFUTURE,Res.getProperties("webquantfuturekey") );
 
		
    	HttpSession session = request.getSession();  
        Integer userId = MemberUtil.getLoginUserIDFromSession(session);  
        SSOToken token = SSOHelper.getToken(request);
        if(userId!=null){  
        	
        	if(token!=null) {
                //登陆成功的用户  
                return true;  
        	}
        	MemberUtil.removeLoginUser(request.getSession());
        }

        
			if(token!=null){
				TUserModel obj= userService.findByPhoneNum(token.getUid());
				MemberUtil.saveMember2Session(session,obj);
				return true;
			}
		
 
		request.setAttribute(WEB_QUANTFUTURE,Res.getProperties("webquantfuturekey") );
 
        if(requestURI.indexOf("index.do")>0||requestURI.indexOf("doc.do")>0||requestURI.indexOf("forget.do")>0
        		||requestURI.indexOf("login.do")>0||requestURI.indexOf("reg.do")>0||requestURI.indexOf("login.m")>0
        		||requestURI.indexOf("getImageValidate.m")>0||requestURI.indexOf("sendSmsCode.m")>0||requestURI.indexOf("checkRegister.m")>0
        		||requestURI.indexOf("checkSmsCode.m")>0||requestURI.indexOf("register.m")>0||requestURI.indexOf("logout.m")>0
        		||requestURI.indexOf("getFile.do")>0||requestURI.indexOf("joinus.do")>0||requestURI.indexOf("aboutus.do")>0
        		||requestURI.indexOf("weixin.do")>0||requestURI.indexOf("nindex.do")>0||requestURI.indexOf("ablutus.do")>0||requestURI.indexOf("joinus.do")>0
        		||requestURI.indexOf("strategy.do")>0||requestURI.indexOf("protocol.do")>0
        		||requestURI.indexOf("company.do")>0
        		||requestURI.indexOf("document.do")>0
        		||requestURI.indexOf("queryAttrInfo.m")>0
        		||requestURI.indexOf("queryAssetInfo.m")>0
				||requestURI.indexOf("queryActualDailyList.m")>0
        		){  
            return true;
        }else{  

            	
               //没有登陆，转向登陆界面  
            	if(requestURI.endsWith(".m")){
            		ResultData res=new ResultData(SystemStatus.HAVElOGIN,null,"请登录");
            		ResponseUtil.toJson(response, res);
            	}else{
            		request.getRequestDispatcher("/frontend/login.do").forward(request,response);  
            	}
                
              return false;  
             
        }  
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
