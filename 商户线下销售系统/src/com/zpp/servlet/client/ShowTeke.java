package com.zpp.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.Order;
import com.zpp.domain.User;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.CookiesUtils;

/**
 * Servlet implementation class ShowTeke
 */
@WebServlet("/ShowTeke")
public class ShowTeke extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PayService payService=new PayServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user=CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
			//PayService payService=new PayServiceImpl();
			List<Order> list=payService.getTeke(user.getId(), 0, 2);//未取货但已经备货
			
			request.setAttribute("gidlist", list);
			request.getRequestDispatcher("/page/admin/showteke.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath()+"/page/errorpage.html");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
