package com.ujuit.quant.user.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ujuit.sysmanager.util.Res;

@Controller
@RequestMapping("/frontend/")
public class FrontendController {
	public static String firefox="firefox";
	
	@RequestMapping("getFile.do")
	public void getFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String type = request.getParameter("type");
		String filename = Res.getProperties("pdfFileName");
		String agent=request.getHeader("User-Agent").toLowerCase();
		agent=getBrowserName(agent);
		if ("1".equals(type)) {
			filename =  Res.getProperties("sdkFileName");
			if(firefox.equals(agent)){
				response.addHeader("Content-Disposition", "attachment;filename="+
			new String(filename.getBytes("GB2312"),"ISO-8859-1"));
			}else{
				response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
			}
		} else {
			if(firefox.equals(agent)){
				response.addHeader("Content-Disposition", "attachment;filename="+
						new String(filename.getBytes("GB2312"),"ISO-8859-1"));
			}else{
				response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
			}
			
		}
		response.setContentType(request.getServletContext().getMimeType(filename));
		// response.setHeader("Content-Disposition", "attachment;filename=" +
		// filename);
		// response.addHeader("Content-Disposition","attachment;filename="+filename);
		String fullFileName = request.getServletContext().getRealPath("/doc/" + filename);
		InputStream in = new FileInputStream(fullFileName);
		OutputStream out = response.getOutputStream();
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		in.close();
		out.close();
	}

	
	
	/**
	 * @author shadow
	 * @return
	 */
	@RequestMapping("index.do")
	public String index() {
		return "web/frontend/index/index";
	}
	/**
	 * @author shadow andrew new
	 * @return
	 */
	@RequestMapping("nindex.do")
	public String nindex() {
		return "web/frontend/index";
	}
	/**
	 * @author shadow andrew new
	 * @return
	 */
	@RequestMapping("aboutus.do")
	public String aboutus() {
		return "web/frontend/index/aboutus";
	}
	/**
	 * @author shadow andrew new
	 * @return
	 */
	@RequestMapping("company.do")
	public String company() {
		return "web/frontend/company";
	}
	/**
	 * @author shadow andrew new
	 * @return
	 */
	@RequestMapping("protocol.do")
	public String protocol() {
		return "web/frontend/user/protocol";
	}
	/**
	 * @author shadow andrew new
	 * @return
	 */
	@RequestMapping("joinus.do")
	public String joinus() {
		return "web/frontend/index/joinus";
	}
	/**
	 * @author shadow andrew new
	 * 静态布局 暂时不需要验证
	 * @return
	 */
	@RequestMapping("pcenter.do")
	public String pcenter() {
		return "web/frontend/person/pcenter";
	}
	/**
	 * @author shadow andrew new
	 * 静态布局 暂时不需要验证
	 * @return
	 */
	@RequestMapping("strategy.do")
	public String strategy() {
		return "web/frontend/strategy/strategy";
	}
	
	/**
	 * @author shadow
	 * @return
	 */
	@RequestMapping("doc.do")
	public String doc() {
		return "web/frontend/doc";
	}

	/**
	 * @author shadow
	 * @return
	 */
	@RequestMapping("myStrategy.do")
	public String myStrategy() {
		return "web/frontend/myStrategy";
	}

	/**
	 * @author shadow
	 * @return
	 */
	@RequestMapping("simulation.do")
	public String simulation() {
		return "web/frontend/simulation";
	}

	/**
	 * @author shadow
	 * @return
	 */
	@RequestMapping("simulationPara.do")
	public String simulationPara() {
		return "web/frontend/simulationPara";
	}

	/**
	 * @author shadow
	 * @return
	 */
	@RequestMapping("simParaHistory.do")
	public String simParaHistory() {
		return "web/frontend/simParaHistory";
	}

	/**
	 * @author shadow
	 * @return
	 */
	@RequestMapping("forget.do")
	public String forget() {
		return "web/frontend/user/forget";
	}

	/**
	 * @author shadow
	 * @return
	 */
	@RequestMapping("login.do")
	public String login() {
		return "web/frontend/user/login";
	}

	/**
	 * @author shadow
	 * @return
	 */
	@RequestMapping("reg.do")
	public String reg() {
		return "web/frontend/user/reg";
	}

	
	public String getBrowserName(String agent) {
		  if(agent.indexOf("msie 7")>0){
		   return "ie7";
		  }else if(agent.indexOf("msie 8")>0){
		   return "ie8";
		  }else if(agent.indexOf("msie 9")>0){
		   return "ie9";
		  }else if(agent.indexOf("msie 10")>0){
		   return "ie10";
		  }else if(agent.indexOf("msie")>0){
		   return "ie";
		  }else if(agent.indexOf("opera")>0){
		   return "opera";
		  }else if(agent.indexOf("opera")>0){
		   return "opera";
		  }else if(agent.indexOf("firefox")>0){
		   return "firefox";
		  }else if(agent.indexOf("webkit")>0){
		   return "webkit";
		  }else if(agent.indexOf("gecko")>0 && agent.indexOf("rv:11")>0){
		   return "ie11";
		  }else{
		   return "Others";
		  }
		 }
}
