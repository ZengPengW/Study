package com.zpp.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.Order;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.utils.CookiesUtils;

/**
 * Servlet implementation class MyOrderCheck
 */
@WebServlet("/MyOrderCheck")
public class MyOrderCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shopcart=CookiesUtils.getCookie(request.getCookies(),"cart");
		if(shopcart!=null&&!shopcart.isEmpty()){
			Cookie cookie=new Cookie("cart", "q");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		String equipment=CookiesUtils.getCookie(request.getCookies(), "equipment");
		String uid=CookiesUtils.getCookie(request.getCookies(), "uid");
		if(equipment==null||uid==null) {
			response.sendRedirect(request.getContextPath()+"/page/errorpage.html");
			return;
		}
		try {
			PayService payService=new PayServiceImpl();
			List<Order> order=payService.GetOrderByEquipment(Integer.parseInt(uid), equipment);
			request.setAttribute("myOrder", order);
			request.getRequestDispatcher("/page/customer/myOrder.jsp").forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/page/errorpage.html");
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
