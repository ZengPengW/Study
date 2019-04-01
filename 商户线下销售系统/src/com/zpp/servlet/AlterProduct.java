package com.zpp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.Product;
import com.zpp.domain.User;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.CookiesUtils;
import com.zpp.utils.JsonUtils;

import Jedis.JedisPoolUtils;
import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class AlterProduct
 */
@WebServlet("/AlterProduct")
public class AlterProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int pid=Integer.parseInt(request.getParameter("pid"));
			Jedis jedis=JedisPoolUtils.getJedis();
			String json=jedis.hget("users", CookiesUtils.getCookie(request.getCookies(), "sid"));
			User user=JsonUtils.getUser(json);
			jedis.close();
			SellerService service=new SellerServiceImpl();
			Product product=service.GetProductByPid(user.getId(), pid);
			request.setAttribute("product", product);
			request.getRequestDispatcher("/page/admin/alterProduct.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("isSuccess", false);
			request.getRequestDispatcher("/page/success.jsp").forward(request, response);
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
