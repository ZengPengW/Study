package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.service.TestService;

/*
 * 测试
 */
@Controller
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping("/test/queryNow.action")
	@ResponseBody
	public String queryNow() {
		//1.引入服务
		//2.注入服务
		//3.调用
		System.out.println("adaddas传递");
		return	testService.queryNow();	
	}
}
