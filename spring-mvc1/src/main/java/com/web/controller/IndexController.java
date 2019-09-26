package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 使用注解方式定义一个controller
 */
@Controller
public class IndexController {

	@ResponseBody
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
