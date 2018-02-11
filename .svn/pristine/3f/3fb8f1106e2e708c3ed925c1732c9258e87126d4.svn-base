package com.ujuit.quant.wechat.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ujuit.quant.utils.MemCacheCommonUtil;

public class TicketUtil {

	// 临时二维码
	private final static String QR_SCENE = "QR_SCENE";

	private final static String QR_STR_SCENE = "QR_STR_SCENE";// 字符串形式的参数

	// 创建二维码
	private static String create_ticket_path = "https://api.weixin.qq.com/cgi-bin/qrcode/create";

	// 通过ticket换取二维码
	private static String showqrcode_path = "https://mp.weixin.qq.com/cgi-bin/showqrcode";

	public static String getTicket(String userId, String strategyId, String strategyType) {
		// 获取数据的地址（微信提供）
		String url = create_ticket_path + "?access_token=" + MemCacheCommonUtil.get("wechatToken");
		// 发送给微信服务器的数据
		String jsonStr = "{\"expire_seconds\": 2592000,\"action_name\": \"QR_STR_SCENE\", \"action_info\":"
				+ " {\"scene\": {\"scene_str\": \"" + userId + "," + strategyId + "," + strategyType + "\" }}}";
		// 将得到的字符串转化成json对象
		try {
			String response = HttpUtil.sendPost(url, jsonStr);
			if (org.springframework.util.StringUtils.isEmpty(response)) {
				return null;
			}
			JSONObject jsonObject = JSON.parseObject(response);
			if (jsonObject != null) {
				if (jsonObject.getString("ticket") != null) {
					return jsonObject.getString("ticket");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public static String showQrcode(String ticket) {
		try {
			showqrcode_path = HttpUtil.sendGet(showqrcode_path, "ticket=" + ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showqrcode_path;
	}

	public static String setParmas(Map<String, String> map, String path, String charset) throws Exception {
		String result = "";
		boolean hasParams = false;
		if (path != null && !"".equals(path)) {
			if (map != null && map.size() > 0) {
				StringBuilder builder = new StringBuilder();
				Set<Entry<String, String>> params = map.entrySet();
				for (Entry<String, String> entry : params) {
					String key = entry.getKey().trim();
					String value = entry.getValue().trim();
					if (hasParams) {
						builder.append("&");
					} else {
						hasParams = true;
					}
					if (charset != null && !"".equals(charset)) {
						// builder.append(key).append("=").append(URLDecoder.(value,
						// charset));
						builder.append(key).append("=").append(urlEncode(value, charset));
					} else {
						builder.append(key).append("=").append(value);
					}
				}
				result = builder.toString();
			}
		}
		return doUrlPath(path, result).toString();
	}

	public static String urlEncode(String source, String encode) {
		String result = source;
		try {
			result = URLEncoder.encode(source, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static URL doUrlPath(String path, String query) throws Exception {
		URL url = new URL(path);
		if (org.apache.commons.lang.StringUtils.isEmpty(path)) {
			return url;
		}
		if (org.apache.commons.lang.StringUtils.isEmpty(url.getQuery())) {
			if (path.endsWith("?")) {
				path += query;
			} else {
				path = path + "?" + query;
			}
		} else {
			if (path.endsWith("&")) {
				path += query;
			} else {
				path = path + "&" + query;
			}
		}
		return new URL(path);
	}
}
