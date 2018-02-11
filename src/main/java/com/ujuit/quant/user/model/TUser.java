/*
*@Title:  TUserModel.java    
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: cly     
 * @date:   2017年4月18日 下午10:23:33   
 * @version 
*/
package com.ujuit.quant.user.model;

import java.io.Serializable;

/**
 * @author shadow
 */
public class TUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 秘钥
	 */
	private String secretKey;

	/**
	 * 类型1为策略平台 2为数据平台且包含策略平台
	 */
	private String type;

	/**
	 * 手机号码
	 */
	private String callPhone;

	/**
	 * 用户姓名(数据平台)
	 */
	private String theUserName;

	/**
	 * 数据平台秘钥
	 */
	private String accessKey;

	/**
	 * 简介
	 */
	private String introduce;

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCallPhone() {
		return callPhone;
	}

	public void setCallPhone(String callPhone) {
		this.callPhone = callPhone;
	}

	public String getTheUserName() {
		return theUserName;
	}

	public void setTheUserName(String theUserName) {
		this.theUserName = theUserName;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

}
