package com.taotao.search.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 全局异常处理器类
 * @author zp
 *
 */
public class GlobelExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//写入日志
		System.out.println(ex.getMessage());
		ex.printStackTrace();
		//及时通知开发人员如邮件短信等
		System.out.println("发短信通知");
		//给用户一个友好的界面
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("message", "您的网络有异常,请重试");
		modelAndView.setViewName("error/exception");
		return modelAndView;
	}

}
