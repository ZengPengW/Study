package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 跳转页面使用
 * @author zp
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class PageController {

	@RequestMapping("/page/{page}")
	public String showPage(@PathVariable String page){
		
		return page;
	}
}
