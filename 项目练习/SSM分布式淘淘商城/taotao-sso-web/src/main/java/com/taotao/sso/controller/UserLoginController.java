package com.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.sso.service.UserLoginService;

@Controller
public class UserLoginController {
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Value("${TT_TOKEN_KEY}")
	private String TT_TOKEN_KEY;
	/**
	 * url:/user/login
	 * 请求限定的方法：post
	 * @param username 
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult login(String username,String password,HttpServletResponse response,HttpServletRequest request){
		TaotaoResult taotaoResult = userLoginService.login(username, password);
		//判断成功 设置token 
		if (taotaoResult.getStatus()==200) {
			CookieUtils.setCookie(request, response, TT_TOKEN_KEY, taotaoResult.getData().toString());
		}
		return taotaoResult;
	}
	
	/**
	 * url:/user/token/{token}
	 * @param token
	 * @return json
	 * 请求限定的方法：get
	 */
	@RequestMapping(value="/user/token/{token}",method=RequestMethod.GET)
	@ResponseBody
	public Object getUserByToken(@PathVariable String token,String callback){
		//System.out.println(callback);
		TaotaoResult result=null;
		try {
			result=userLoginService.getUserByToken(token);	
		} catch (Exception e) {
			e.printStackTrace();
			result=TaotaoResult.build(500, e.getMessage());
		}
		//判断是否是jsonp调用
		if (StringUtils.isBlank(callback)) {
			return result;		
		}else {
			MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		
	}
	/**
	 * url:/user/logout/{token}
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/user/logout/{token}",method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult logOut(@PathVariable String token,HttpServletResponse response,HttpServletRequest request){
		CookieUtils.deleteCookie(request, response, TT_TOKEN_KEY);
		return userLoginService.deleteUserByToken(token);
	}
}
