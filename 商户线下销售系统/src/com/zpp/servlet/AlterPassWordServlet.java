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
 * Servlet implementation class AlterPassWordServlet
 */
@WebServlet("/AlterPassWordServlet")
public class AlterPassWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newPassWord=request.getParameter("password");
		String email=(String) request.getSession().getAttribute("changePWemail");
		//System.out.println(email+" "+newPassWord);
		SellerService service=new SellerServiceImpl();
		try {
			boolean bl=service.upPassWord(email, newPassWord);
			request.setAttribute("isSuccess", bl);
			
		} catch (SQLException e) {
			request.setAttribute("isSuccess", false);
			e.printStackTrace();
		}finally {
			request.getSession().removeAttribute("changePWemail");
			request.getSession().removeAttribute("msg");
			request.getRequestDispatcher("/page/success.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
