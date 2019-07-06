package com.zpp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.User;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.CookiesUtils;

/**
 * Servlet implementation class CheckAlterUserNameLnfo
 */
@WebServlet("/CheckAlterUserNameInfo")
public class CheckAlterUserNameInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SellerService service=new SellerServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username=request.getParameter("username");
			User user=CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
			String oldusername=user.getUsername();
			if(oldusername.equals(username))response.getWriter().print(true);
			else {
				//SellerService service=new SellerServiceImpl();
				try {
					boolean isexist=service.isExistName(username);
					if(isexist) {
						response.getWriter().print(false);
					}else {
						response.getWriter().print(true);
					}
				} catch (SQLException e) {
					response.getWriter().print(false);
					e.printStackTrace();
				}	
			}
		} catch (Exception e) {
			response.getWriter().print(false);
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
