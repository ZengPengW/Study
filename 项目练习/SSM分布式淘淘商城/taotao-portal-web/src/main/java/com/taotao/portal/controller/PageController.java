package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面展示
 * @author zp
 *
 */
@Controller
public class PageController {

	@RequestMapping("/index")
	public String showIndex() {
		return "index";
	}
}
