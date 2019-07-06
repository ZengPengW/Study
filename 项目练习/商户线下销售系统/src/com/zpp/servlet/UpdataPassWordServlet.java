package com.zpp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.service.SellerService;

/**
 * Servlet implementation class UpdataPassWordServlet
 */
@WebServlet("/UpdataPassWordServlet")
public class UpdataPassWordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=(String) request.getSession().getAttribute("currEmail");
		
		request.getSession().setAttribute("changePWemail", email);
		request.getSession().removeAttribute("currEmail");
		//response.sendRedirect("page/cgpd.jsp");
		request.getRequestDispatcher("/page/cgpd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
