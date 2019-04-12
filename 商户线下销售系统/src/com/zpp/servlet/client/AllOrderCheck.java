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

/**
 * Servlet implementation class AllOrderCheck
 */
@WebServlet("/AllOrderCheck")
public class AllOrderCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
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
			request.setAttribute("allOrd",allOrd);
			//ªÒ»°∑÷“≥
			long totalSize=payService.getAllOrderCountByUid(user.getId(), orderClass);
			long totalPage=totalSize/PayDaoImpl.pageSize;
			if(totalSize%PayDaoImpl.pageSize!=0)totalPage++;
			request.setAttribute("currentPage",currentPage);
			request.setAttribute("totalPage",totalPage);
			
			
			request.getRequestDispatcher("/page/admin/allOrder.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("isSuccess", false);
			request.getRequestDispatcher("/page/success.jsp").forward(request, response);
			e.printStackTrace();
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
