package com.zpp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.dao.impl.SellerdaoImpl;
import com.zpp.domain.PageBean;
import com.zpp.domain.Product;
import com.zpp.domain.User;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.CookiesUtils;
import com.zpp.utils.JsonUtils;

import Jedis.JedisPoolUtils;
import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class OnSaleProductList
 */
@WebServlet("/OnSaleProductList")
public class OnSaleProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage=Integer.parseInt(request.getParameter("currentPage"));
		String productClass=request.getParameter("productClass");
		
		try {
			String sid=CookiesUtils.getCookie(request.getCookies(), "sid");
			
			Jedis jedis=JedisPoolUtils.getJedis();
			
			User user=JsonUtils.getUser(jedis.hget("users", sid));
			
			jedis.close();
			SellerService service=new SellerServiceImpl();
			
			List<Product> list=service.OnSaleProductByID(user.getId(), currentPage,productClass);
			
			long totalSize =service.CheckOnsaleProductCount(user.getId(), productClass);//总记录数
			long totalPage=totalSize/SellerdaoImpl.pageSize;//总页数
			if(totalSize%SellerdaoImpl.pageSize!=0)totalPage++;
			
			PageBean<Product> pageBean=new PageBean<Product>(currentPage,totalSize,totalPage,list,productClass);
			
			request.setAttribute("pageBean", pageBean);
			
			request.setAttribute("productClass",productClass );
			request.getRequestDispatcher("/page/admin/OnSaleProduct.jsp").forward(request, response);
			
			
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
