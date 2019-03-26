package com.zpp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;

/**
 * Servlet implementation class CheckUserEmail
 */
@WebServlet("/CheckUserEmail")
public class CheckUserEmail extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String email=request.getParameter("email");
		SellerService service=new SellerServiceImpl();
		try {
			boolean flag=service.isExistEmail(email);
			if(flag) {
				response.getWriter().print(false);
			}else {
				response.getWriter().print(true);
			}
		} catch (SQLException e) {
			response.getWriter().print(false);
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
