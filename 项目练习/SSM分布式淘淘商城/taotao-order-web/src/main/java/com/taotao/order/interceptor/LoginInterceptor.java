package com.taotao.order.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.sso.service.UserLoginService;
/**
 * 判断用户是否登录
 * @author zp
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Value("${TT_TOKEN_KEY}")
	private String TT_TOKEN_KEY;
	
	@Value("${SSO_URL}")
	private String SSO_URL;
	
	
	@Autowired
	private UserLoginService UserLoginService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//执行handler之前执行
		//1.从cookie中取token
		//没取到就到sso登录 
		String token = CookieUtils.getCookieValue(request, TT_TOKEN_KEY);
		if(StringUtils.isBlank(token)){
			response.sendRedirect(SSO_URL+"/page/login?url="+request.getRequestURL().toString());
			//拦截
			return false;
		}
		//取到就判断sso系统是否登录
		TaotaoResult result = UserLoginService.getUserByToken(token);
		if(result.getStatus()!=200){
			response.sendRedirect(SSO_URL+"/page/login?url="+request.getRequestURL().toString());
			//拦截
			return false;
		}
		
		
		//2.sso有信息就放行
		//把用户信息放到request中方便后面handler使用
		request.setAttribute("user", result.getData());
		//true 放行
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//handler之后，ModeAndView 之前

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//ModeAndView 之后 异常处理

	}

}
