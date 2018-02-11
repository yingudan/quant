package com.ujuit.quant.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	/**
	 * 默认页面
	 * 
	 * @author cly
	 * @return
	 * @date 2017年5月11日 下午5:55:20
	 * @return: String
	 */
	@RequestMapping("index.do")
	public String index() {
		return "web/frontend/index";
	}

}
