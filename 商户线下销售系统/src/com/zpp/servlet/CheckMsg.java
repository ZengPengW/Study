package com.zpp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckMsg
 */
@WebServlet("/CheckMsg")
public class CheckMsg extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mymsg=request.getParameter("msg");
		String msg=(String) request.getSession().getAttribute("emailmsg");
	
		if(msg==null||!msg.equals(mymsg)) {
			response.getWriter().print(false);
		
		}else {
			response.getWriter().print(true);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
