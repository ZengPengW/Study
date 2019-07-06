package com.zpp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckLoginMsg")
public class CheckLoginMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg=request.getParameter("msg");
		String checkcode_session=(String) request.getSession().getAttribute("checkcode_session");
		//System.out.println(checkcode_session+" "+msg);
		if(msg.equals(checkcode_session)) {
			response.getWriter().print(true);
		}else {
			response.getWriter().print(false);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
