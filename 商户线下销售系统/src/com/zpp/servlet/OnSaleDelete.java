package com.zpp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.User;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.CookiesUtils;
import com.zpp.utils.JsonUtils;

import Jedis.JedisPoolUtils;
import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class OnSaleDelete
 */
@WebServlet("/OnSaleDelete")
public class OnSaleDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SellerService service=new SellerServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag=false;
		try {
			int pid=Integer.parseInt(request.getParameter("pid"));
			
			String sid=CookiesUtils.getCookie(request.getCookies(), "sid");
			Jedis jedis=JedisPoolUtils.getJedis();
			User user =JsonUtils.getUser(jedis.hget("users", sid));
			jedis.close();
			//SellerService service=new SellerServiceImpl();
			flag=service.onSaleDelete(user.getId(), pid);
			
		} catch (Exception e) {
			response.getWriter().print(false);
			e.printStackTrace();
		}finally {
			response.getWriter().print(flag);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
