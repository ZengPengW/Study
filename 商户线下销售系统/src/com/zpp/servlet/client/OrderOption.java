package com.zpp.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.User;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.utils.CookiesUtils;

/**
 * Servlet implementation class OrderOption
 */
@WebServlet("/OrderOption")
public class OrderOption extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int oid=Integer.parseInt(request.getParameter("oid"));
			int isOption=Integer.parseInt(request.getParameter("isOption"));
			PayService payService=new PayServiceImpl();
			User user=CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
			boolean flag=payService.orderOptionByUid(user.getId(), oid, isOption);
			response.getWriter().print(flag);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(false);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
