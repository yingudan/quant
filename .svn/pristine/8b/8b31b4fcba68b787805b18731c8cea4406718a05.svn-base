package com.ujuit.quant.wechat.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ujuit.quant.utils.MemCacheCommonUtil;

public class SendMsgUtil {
	
	private static String send_message_path = "https://api.weixin.qq.com/cgi-bin/message/custom/send";

	public static boolean sendMessage(String openId, String content) {
		// 获取数据的地址（微信提供）
		String url = send_message_path + "?access_token=" + MemCacheCommonUtil.get("wechatToken");
		// 发送给微信服务器的数据
		String jsonStr = "{\"touser\": " + "\"" + openId + "\"" + ",\"msgtype\": \"text\", \"text\":"
				+ " {\"content\": " + "\"" + content + "\"" + " }}";
		// 将得到的字符串转化成json对象
		// System.out.println(jsonStr);
		String response = HttpUtil.sendPost(url, jsonStr);
		if (StringUtils.isNotEmpty(response)) {
			Map<String, Object> map = parseJSON2Map(response);
			if ("0".equals(map.get("errcode").toString())) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject json = JSON.parseObject(jsonStr);
		map = JSONObject.toJavaObject(json, Map.class);
		return map;
	}
}
