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
import com.zpp.utils.CookiesUtils;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class OrderSearch
 */
@WebServlet("/OrderSearch")
public class OrderSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String searchinfo=request.getParameter("searchinfo");
			if (searchinfo==null||searchinfo.isEmpty()) {
				throw new RuntimeException("²ÎÊýÎª¿Õ");
			}
			User user=CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
			PayService payService=new PayServiceImpl();
			List<Order> list=payService.OrderSearchLike(user.getId(), searchinfo);
			String json=JSONArray.fromObject(list).toString();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("false");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
