package com.ujuit.quant.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 短信发送工具类
 * 
 * @author luyang
 * 
 */
public class SendSMSUtils {
	private static Logger logger = LoggerFactory.getLogger(SendSMSUtils.class);
	/**
	 * 短信企业用户号
	 */
	private static final String CORPID = "LKSDK0004150";
	/**
	 * 短信发送密码
	 */
	private static final String PWD = "Sylh@cdjyygl";
public static void main(String[] args) {
	try {
		int sendResult =  sendSMS("13915286196", "尊敬的用户，欢迎您注册有据量化策略开发系统；您的验证码1234，该验证码5分钟内有效，请尽快完成注册；若非本人操作，请忽略此条信息。【有据信息】", "");
		System.out.println(sendResult);
	} catch (MalformedURLException | UnsupportedEncodingException e) {
		e.printStackTrace();
	}
}
	/**
	 * 发送短信
	 * 
	 * @param Mobile
	 *            目标手机号码
	 * @param Content
	 *            短信内容
	 * @param send_time
	 *            发送时间 固定14位长度字符串，比如：20060912152435代表2006年9月12日15时24分35
	 * @return
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 * 
	 *             大于等于0的数字 提交成功 –1 账号未注册 –2 其他错误 –3 帐号或密码错误 –5 余额不足，请充值 –6
	 *             定时发送时间不是有效的时间格式 -7 提交信息末尾未签名，请添加中文的企业签名【 】 –8 发送内容需在1到300字之间
	 *             -9 发送号码为空 -10 定时时间不能小于系统当前时间 -100 限制IP访问
	 */
	public static int sendSMS(String Mobile, String Content, String send_time)
			throws MalformedURLException, UnsupportedEncodingException {
		URL url = null;
		String send_content = URLEncoder.encode(
				Content.replaceAll("<br/>", " "), "GBK");// 发送内容
		url = new URL("https://mb345.com/ws/BatchSend.aspx?CorpID=" + CORPID
				+ "&Pwd=" + PWD + "&Mobile=" + Mobile + "&Content="
				+ send_content + "&Cell=&SendTime=" + send_time);
		BufferedReader in = null;
		int inputLine = 0;
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			inputLine = new Integer(in.readLine()).intValue();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		showMessge(inputLine);
		return inputLine;
	}
	
	/**
	 * @Author dzy
	 * @Desc   打印发送短信消息结果
	 * @param key
	 */
	public static String showMessge(int key){
	   String Retext=null;
	   if(key >=0){
		   Retext = "发送成功";
	   }else if(key==-1){
		   Retext = "帐号未注册";
	   }else if(key==-3){
		   Retext = "密码错误";
	   }else if(key==-4){
		   Retext = "手机号格式不对";
	   }else if(key==-5){
		   Retext = "余额不足";
	   }else if(key==-6){
		   Retext = "定时发送时间不是有效的时间格式";
	   }else {
		   Retext = "其他错误";
	   }
	   logger.info(Retext);
	   return Retext;
	}
	
	
	
}
