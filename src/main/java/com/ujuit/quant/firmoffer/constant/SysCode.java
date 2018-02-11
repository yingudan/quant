package com.ujuit.quant.firmoffer.constant;

public interface SysCode {
	/**
	 * 连接策略服务器超时
	 */
	public static final int TIME_OUT = 101;
	/**
	 * OK 不解释
	 */
	public static final int OK = 200;
	/**
	 * 策略未就绪
	 */
	public static final int STRATEGY_NOT_READY = 301;

	/**
	 *  策略测试记录未找到
	 */
	public static final int TEST_RECORD_NOT_FOUND = 302;
	
	/**
	 *  策略未找到
	 */
	public static final int STRATEGY_NOT_FOUND = 303;

	/**
	 *  测试记录参数组未找到
	 */
	public static final int PARAM_GROUP_NOT_FOUND = 304;

	/**
	 *  策略服务器已掉线
	 */
	public static final int STRATEGY_SERVER_DISCONNECT = 305;

	// ----------------------------------------------------------
	/**
	 *  参数组未在测试中
	 */
	public static final int PARAM_GROUP_NOT_TESTING = 401;

	/**
	 *  参数组数量不匹配 实时测试只能有一组参数组
	 */
	public static final int PARAM_GROUP_NOT_FIT = 402;

	/**
	 *  参数组设置不正确
	 */
	public static final int PARAM_GROUP_NOT_CORRECT = 403;
}
