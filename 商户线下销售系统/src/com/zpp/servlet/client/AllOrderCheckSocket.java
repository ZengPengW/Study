package com.zpp.servlet.client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.dao.impl.PayDaoImpl;
import com.zpp.domain.Order;
import com.zpp.domain.User;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.utils.Base64Utils;
import com.zpp.utils.CookiesUtils;
import com.zpp.utils.URLcodeUtils;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class AllOrderCheckSocket
 */
@WebServlet("/AllOrderCheckSocket")
public class AllOrderCheckSocket extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String sid=CookiesUtils.getCookie(request.getCookies(), "sid");
			String orderClass=request.getParameter("orderClass");
			int currentPage=Integer.parseInt(request.getParameter("currentPage"));
			request.setAttribute("orderClass", URLcodeUtils.encoder(orderClass));
			orderClass=Base64Utils.decoder(orderClass);
			
			User user=CookiesUtils.getUser(sid);
			PayService payService=new PayServiceImpl();
			List<Order> allOrd=payService.getAllOrderByUid(user.getId(),currentPage,orderClass);
			String json=JSONArray.fromObject(allOrd).toString();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(json);
			
			
			
			
			
		
		} catch (SQLException e) {
			response.getWriter().print(false);
			e.printStackTrace();
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
