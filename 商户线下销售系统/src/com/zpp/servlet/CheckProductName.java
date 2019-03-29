package com.zpp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.dao.impl.SellerdaoImpl;
import com.zpp.domain.User;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.CookiesUtils;

/**
 * Servlet implementation class CheckProductName
 */
@WebServlet("/CheckProductName")
public class CheckProductName extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName=request.getParameter("productName");
		String sid=CookiesUtils.getCookie(request.getCookies(), "sid");
		if(sid==null)response.sendRedirect("/page/login.jsp");
		else {
			SellerService service=new SellerServiceImpl();
			User user;
			try {
				user = service.getUserBySid(sid);
				if(service.isExisProductName(productName, user.getId())) {
					//System.out.println(false);
					response.getWriter().println(false);
				}else {
					//System.out.println(true);
					response.getWriter().println(true);
				}
			} catch (SQLException e) {
				
				response.getWriter().println(false);
				e.printStackTrace();
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
