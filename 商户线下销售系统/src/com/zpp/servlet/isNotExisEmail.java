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
 * Servlet implementation class isNotExisEmail
 */
@WebServlet("/isNotExisEmail")
public class isNotExisEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SellerService service=new SellerServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
				String email=request.getParameter("email");
				//System.out.println(email+"55");
				//SellerService service=new SellerServiceImpl();
				try {
					boolean flag=service.isExistEmail(email);
					response.getWriter().print(flag);
					//System.out.println(flag);
				} catch (SQLException e) {
					response.getWriter().print(false);
					
					e.printStackTrace();
				}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
