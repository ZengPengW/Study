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
 * Servlet implementation class PublishServlet
 */
@WebServlet("/PublishServlet")
public class PublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SellerService service=new SellerServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag=false;
		try {
			int pid=Integer.parseInt(request.getParameter("pid"));
			Jedis jedis=JedisPoolUtils.getJedis();
			String jsonstr=jedis.hget("users",CookiesUtils.getCookie(request.getCookies(), "sid"));
			jedis.close();
			if(jsonstr==null||jsonstr.isEmpty()) {
				request.setAttribute("isSuccess", false);
				throw new RuntimeException("Î´µÇÂ¼´íÎó");
			}
			User user=JsonUtils.getUser(jsonstr);
			//SellerService service=new SellerServiceImpl();
			
			if(!service.isExisOnSale(user.getId(), pid)) {
				flag=service.publishProduct(user.getId(), pid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			response.getWriter().print(flag);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
