package com.zpp.servlet.client;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.User;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.Base64Utils;
import com.zpp.utils.CookiesUtils;

/**
 * Servlet implementation class ClientInit
 */
@WebServlet("/ClientInit")
public class ClientInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String curuid=request.getParameter("curUid");
			
			int uid=Integer.parseInt(curuid);
			
			SellerService service=new SellerServiceImpl();
			User user=service.getUserById(uid);			
			//设置用户cookie
			String username=request.getParameter("username");
			String phone=request.getParameter("phone");
			String shopname=user.getShopname();
			username=Base64Utils.encoder(username);
			shopname=Base64Utils.encoder(shopname);
			
			
			
			String equipment=CookiesUtils.getCookie(request.getCookies(), "equipment");
			if(equipment==null||equipment.isEmpty()) {
				equipment=request.getSession().getId();
				Cookie cookie5 =new Cookie("equipment", equipment);
				cookie5.setMaxAge(24*30*30*365);
				response.addCookie(cookie5);
			}
			
			
			Cookie cookie=new Cookie("username", username);
			Cookie cookie2=new Cookie("phone", phone);
			Cookie cookie3=new Cookie("shopname", shopname);
			Cookie cookie4=new Cookie("uid", ""+uid);
			cookie.setMaxAge(24*60*60*365);
			cookie2.setMaxAge(24*60*60*365);
			cookie3.setMaxAge(24*60*60*365);
			cookie4.setMaxAge(24*60*60*365);
			response.addCookie(cookie);
			response.addCookie(cookie2);
			response.addCookie(cookie3);
			response.addCookie(cookie4);
			
			//清空购物车
			Cookie cart=new Cookie("cart", "1");
			cart.setMaxAge(0);
			response.addCookie(cart);
			//request.getRequestDispatcher("/OnSaleProductListClient").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/page/customer/Springboard.html");
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/page/errorpage.html");
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
