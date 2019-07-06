package com.zpp.servlet.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class ChangePassWordFD
 */
@WebFilter(
		asyncSupported = true, 
		urlPatterns = { "/ChangePassWordFD","/UpdataPassWordServlet","/page/cgpd.jsp"})
public class ChangePassWordFD implements Filter {

  
    public ChangePassWordFD() {
      
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//System.out.println("123");
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse rep=(HttpServletResponse) response;
		req.setCharacterEncoding("utf-8");
		String email=req.getParameter("email");
		String yanzhengma=req.getParameter("yanzhengma");
		
		if(email==null||yanzhengma==null||!(req.getSession().getAttribute("emailmsg").equals(yanzhengma))) {
			//System.out.println("1233");
			
			rep.sendRedirect("/Zpp/page/errorpage.html");
		}else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		//System.out.println("a");
	}

}
