package com.taotao.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 跳转页面使用
 * @author zp
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class PageController {

	@RequestMapping("/page/{page}")
	public String showPage(@PathVariable String page,String url,Model model){
		if(StringUtils.isNotBlank(url)) model.addAttribute("redirect", url);
		return page;
	}
}
