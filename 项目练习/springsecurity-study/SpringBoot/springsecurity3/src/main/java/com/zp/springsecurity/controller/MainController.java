package com.zp.springsecurity.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zp.springsecurity.utils.CheckImgServlet;

@Controller
public class MainController {

	@Autowired
	private CheckImgServlet checkImgServlet;
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	@RequestMapping("/loginAjax")
	public String loginAjax() {
		return "loginAjax";
	}

	

	@RequestMapping("/myerror3")
	public String error() {
		return "error";
	}

	@RequestMapping("/imgCode")
	public void imgCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkImgServlet.getImg(request, response);
	}
	
	@RequestMapping("/403")
	public String FORBIDDEN() {
		return "403";
	}
	
	@RequestMapping("/404")
	public String NOT_FOUND() {
		return "404";
	}
}
