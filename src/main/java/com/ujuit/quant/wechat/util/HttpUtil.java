package com.ujuit.quant.wechat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class HttpUtil {

	/** 
     * 向指定URL发送GET方法的请求 
     *  
     * @param url   发送请求的URL 
     * @param param  请求参数，请求参数应该是name1=value1&name2=value2的形式。 
     * @return URL所代表远程资源的响应 
     */  
    public static String sendGet(String url, String param) {  
        String result = "";  
        BufferedReader in = null;  
        try {  
            String urlName = url + "?" + param;  
            URL realUrl = new URL(urlName);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent",  
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
            // 建立实际的连接  
            conn.connect();  
            // 获取所有响应头字段  
            /*Map<String, List<String>> map = conn.getHeaderFields();  
            // 遍历所有的响应头字段  
            for (String key : map.keySet()) {  
                System.out.println(key + "--->" + map.get(key));  
            }  */
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(  
                    new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送GET请求出现异常！"+url);  
           // e.printStackTrace();  
        }  
        // 使用finally块来关闭输入流  
        finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
               // ex.printStackTrace(); 
            	 System.out.println("发送GET请求出现异常！"+url);  
            }  
        }  
        return result;  
    }  
    
    public static void main(String[] args) {
		System.out.println(sendPost("http://index.cfmmc.com/servlet/json","funcNo=4000001&indexCode=CCFI&type=1"));
		
    
    }
    
    /**  
     * 向指定URL发送POST方法的请求  
     *   
     * @param url  
     *            发送请求的URL  
     * @param param  
     *            请求参数，请求参数应该是name1=value1&name2=value2的形式。  
     * @return URL所代表远程资源的响应  
     */  
    public static String sendPost(String url, String param) {  
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent",  
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            // 获取URLConnection对象对应的输出流  
            out = new PrintWriter(conn.getOutputStream());  
            // 发送请求参数  
            out.print(param);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result +=  line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送POST请求出现异常！"+url);  
           // e.printStackTrace();  
        }  
        // 使用finally块来关闭输出流、输入流  
        finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                //ex.printStackTrace();  
            	 System.out.println("发送POST请求出现异常！"+url);
            }  
        }  
        return result;  
    }  

    
    
    /**
	 * 解析返回值
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {

		// 将解析结果存储在HashMap中

		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流

		InputStream inputStream = request.getInputStream();

		// 读取输入流

		SAXReader reader = new SAXReader();

		Document document = reader.read(inputStream);

		String requestXml = document.asXML();

		String subXml = requestXml.split(">")[0] + ">";

		requestXml = requestXml.substring(subXml.length());

		// 得到xml根元素

		Element root = document.getRootElement();

		// 得到根元素的全部子节点

		List<Element> elementList = root.elements();

		// 遍历全部子节点

		for (Element e : elementList) {

			map.put(e.getName(), e.getText());

		}

		map.put("requestXml", requestXml);

		// 释放资源

		inputStream.close();

		inputStream = null;

		return map;

	}
}
