package com.ujuit.quant.wechat.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ujuit.quant.wechat.pojo.AccessToken;
import com.ujuit.quant.wechat.util.WeixinUtil;

public class TokenThread implements Runnable {
	private static Logger log = LoggerFactory.getLogger(TokenThread.class);
	public static String appid ="wx01f3fd8ed1f5f19d";
	public static String appsecret ="155a2181469f3d93c1137dea11278d37";
	public static AccessToken accessToken = null;

	public void run() {
		while (true) {
			try {
				accessToken = WeixinUtil.getAccessToken(appid, appsecret);
				if (null != accessToken) {
					log.info("toekn", accessToken.getExpiresIn(), accessToken.getToken());

					Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
				} else {
					Thread.sleep(60 * 1000);
				}
			} catch (InterruptedException e) {
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					log.error("{}", e1);
				}
				log.error("{}", e);
			}
		}
	}
}
