package com.zpp.servlet.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Jedis.JedisPoolUtils;
import redis.clients.jedis.Jedis;

/**
 * Servlet Filter implementation class IsLoginFilter
 */
@WebFilter(asyncSupported = true, 
urlPatterns = { "/IsLoginFilter","/page/admin/*","/page/index.jsp"})
public class IsLoginFilter implements Filter {

    
    public IsLoginFilter() {
       
    }

	public void destroy() {
	
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//System.out.println("lai ");
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse rep=(HttpServletResponse) response;
		Cookie [] cookies=req.getCookies();
		String sid=null;
		if(cookies !=null){
		    for(int i = 0;i < cookies.length;i++){ 
		        if(cookies[i].getName().equals("sid")){
		          sid=cookies[i].getValue();
		          break;
		        }
		        
		    }
		}
		
		if(sid==null)rep.sendRedirect("/Zpp/page/login.jsp");
		else  {
			Jedis jedis=JedisPoolUtils.getJedis();
			String userSid=null;
			userSid=jedis.hget("users", sid);
			
			if(userSid==null) {
				Cookie cookie=new Cookie("sid", "111");
				cookie.setMaxAge(0);
				rep.addCookie(cookie);
				rep.sendRedirect("/Zpp/page/login.jsp");
			}else{
				chain.doFilter(request, response);
			}
			
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
