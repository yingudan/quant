package com.ujuit.quant.wechat.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ujuit.quant.wechat.thread.TokenThread;
import com.ujuit.quant.wechat.util.WeixinUtil;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	public void init() throws ServletException {
		TokenThread.appid = getInitParameter("appid");

		TokenThread.appsecret = getInitParameter("appsecret");

		log.info("weixin api appid:{}", TokenThread.appid);
		log.info("weixin api appsecret:{}", TokenThread.appsecret);

		if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {
			log.error("appid and appsecret configuration error, please check carefully.");
		} else {
			new Thread(new TokenThread()).start();
		}
	}
}
