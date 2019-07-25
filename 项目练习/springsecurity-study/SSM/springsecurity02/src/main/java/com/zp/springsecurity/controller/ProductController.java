package com.zp.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {


	
	/**
	 * 添加
	 */
	@RequestMapping("/product/add")
	public String add(){
		return "productAdd";
	}
	
	
	/**
	 * 修改
	 */
	@RequestMapping("/product/update")
	public String update(){
		return "productUpdate";
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping("/product/delete")
	public String delete(){
		return "productDelete";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping("/product/list")
	public String list(){
		return "productList";
	}
}
