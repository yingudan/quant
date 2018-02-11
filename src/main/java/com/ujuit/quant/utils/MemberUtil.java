/*
*@Title:  MemberUtil.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月19日 下午4:45:48   
 * @version 
*/
package com.ujuit.quant.utils;

import javax.servlet.http.HttpSession;

import com.ujuit.quant.user.model.TUserModel;

/*
 * TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月19日 下午4:45:48   
 * @version 
*/
public class MemberUtil {

	public static String SESSION_KEY_LOGIN_USER = "login_user";
	public static String SESSION_KEY_LOGIN_ID = "login_id";


	/**
	 * 保存用户实体到session
	 * 
	 * @author cly
	 * @param session
	 * @param model
	 * @date 2017年4月19日 下午5:54:50
	 * @return: void
	 */
	public static void saveMember2Session(HttpSession session, TUserModel model) {
		session.setAttribute(SESSION_KEY_LOGIN_USER, model);
		session.setAttribute(SESSION_KEY_LOGIN_ID, model.getId());
	
	}

	/**
	 * 从session 中拿到 登录用户实体
	 * 
	 * @author cly
	 * @param session
	 * @return
	 * @date 2017年4月19日 下午5:55:05
	 * @return: TUserModel
	 */
	public static TUserModel getLoginUserFromSession(HttpSession session) {
		return (TUserModel) session.getAttribute(SESSION_KEY_LOGIN_USER);
	}

	/**
	 * 从 session 中拿到 登陆用户id
	 * 
	 * @author cly
	 * @param session
	 * @return
	 * @date 2017年4月19日 下午5:55:21
	 * @return: Integer
	 */
	public static Integer getLoginUserIDFromSession(HttpSession session) {
		return (Integer) session.getAttribute(SESSION_KEY_LOGIN_ID);
	}

	public boolean isLogin(HttpSession session) {
		return getLoginUserIDFromSession(session) != null;
	}

	/**
	 * 从session 中移除登陆信息
	 * 
	 * @author cly
	 * @param session
	 * @date 2017年4月19日 下午5:55:52
	 * @return: void
	 */
	public static void removeLoginUser(HttpSession session) {
		session.removeAttribute(SESSION_KEY_LOGIN_USER);
		session.removeAttribute(SESSION_KEY_LOGIN_ID);
	}

}
