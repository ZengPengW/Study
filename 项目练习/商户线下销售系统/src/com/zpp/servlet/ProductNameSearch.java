package com.zpp.servlet;

import java.io.IOException;
import java.util.List;

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
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class ProductNameSearch
 */
@WebServlet("/ProductNameSearch")
public class ProductNameSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SellerService service=new SellerServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String likeName=request.getParameter("likeName");
		
			try {
			//	SellerService service=new SellerServiceImpl();
				Jedis jedis=JedisPoolUtils.getJedis();
				User user=JsonUtils.getUser(jedis.hget("users", CookiesUtils.getCookie(request.getCookies(), "sid")));
				
				List<Product> products=null;
				if(!likeName.isEmpty())products=service.GetProductByName(likeName, user.getId());
				else   products=service.FindAllProduct(user.getId(), 1, "È«²¿");
				
				if (products==null) {
					response.setStatus(404);	
				}
				JSONArray jsonArray=JSONArray.fromObject(products);
				String json=jsonArray.toString();
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(json);
				jedis.close();
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
