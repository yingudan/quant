package com.ujuit.quant.firmoffer.dto;

public class UserInfoDto {

	/**
	 * 用户策略秘钥
	 */
	private String secretKey;

	/**
	 * 用户姓名
	 */
	private String userName;

	private Integer StrategyNum;

	private String callPhone;

	private String HisNum;

	private String NowNum;

	/**
	 * 自我介绍
	 */
	private String introduce;

	public String getHisNum() {
		return HisNum;
	}

	public void setHisNum(String hisNum) {
		HisNum = hisNum;
	}

	public String getNowNum() {
		return NowNum;
	}

	public void setNowNum(String nowNum) {
		NowNum = nowNum;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStrategyNum() {
		return StrategyNum;
	}

	public void setStrategyNum(Integer strategyNum) {
		StrategyNum = strategyNum;
	}

	public String getCallPhone() {
		return callPhone;
	}

	public void setCallPhone(String callPhone) {
		this.callPhone = callPhone;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

}
