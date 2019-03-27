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
 * Servlet implementation class CheckUserIsExis
 */
@WebServlet("/CheckUserIsExis")
public class CheckUserIsExis extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name= request.getParameter("name");
		String password=request.getParameter("password");
		if(name.isEmpty()||password.isEmpty()) {
			response.getWriter().print(false);
			return;
		}
		boolean bl=false;
		try {
			//System.out.println(name+" "+password);
			SellerService service=new SellerServiceImpl();
			 bl=service.isExistUser(name, password);
			response.getWriter().print(bl);
		} catch (SQLException e) {
			response.getWriter().print(false);
			
		}finally {
			if(bl) {
				request.getSession().setAttribute("loginname", name);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
