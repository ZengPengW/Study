package com.zp.springsecurity.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zp.springsecurity.pojo.Permission;
import com.zp.springsecurity.pojo.User;
import com.zp.springsecurity.service.UserService;
import com.zp.springsecurity.utils.CheckImgServlet;


@Controller
public class MainController {

	@Autowired
	private CheckImgServlet checkImgServlet;

	@Autowired
	private UserService service;

	@RequestMapping("/index")
	public String index(Model model) {
		// 获取登录后的用户名:userdetail 对象
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			String username = userDetails.getUsername();
			model.addAttribute("username", username);
		}
		return "/index/index";
	}

	//模拟自动登录
	@RequestMapping("/autolg")
	public String lg(Model model) {
		// 获取登录后的用户名:userdetail 对象
		User user = service.findByUserName("eric");
		// 查询权限
		List<Permission> permissions = service.findPermissionByUsername("eric");

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Permission permission : permissions) {
			authorities.add(new SimpleGrantedAuthority(permission.getPermTag()));
		}
		user.setAuthorities(authorities);
		
		Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		return "/index/index";
	}

	@RequestMapping("/loginAjax")
	public String loginAjax() {
		return "loginAjax";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/error")
	public String error() {
		return "error";
	}

	@RequestMapping("/imgCode")
	public void imgCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkImgServlet.getImg(request, response);
	}
}
