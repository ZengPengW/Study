package com.zpp.servlet.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.ShopCart;

import com.zpp.utils.CookiesUtils;

import net.sf.json.JSONArray;

import net.sf.json.JsonConfig;


@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@SuppressWarnings({ "unchecked", "static-access" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int pid=Integer.parseInt(request.getParameter("pid"));
		//	int count =Integer.parseInt(request.getParameter("count"));
			String cart=CookiesUtils.getCookie(request.getCookies(), "cart");
			String shopname=request.getParameter("shopname");
			double shopprice=Double.valueOf(request.getParameter("shopprice"));
			
			if (cart==null||cart.isEmpty()) {
				List<ShopCart> shopCarts=new ArrayList<ShopCart>();
				shopCarts.add(new ShopCart(pid,1));
				JSONArray jsonArray=JSONArray.fromObject(shopCarts);
				String json=jsonArray.toString();
				Cookie cookie=new Cookie("cart", json);
				cookie.setMaxAge(24*60*60*1);
				response.addCookie(cookie);
				
			}else {
				JSONArray jsonArray=JSONArray.fromObject(cart);
				List<ShopCart> shopCarts=jsonArray.toList(jsonArray, ShopCart.class,new JsonConfig());

				for (ShopCart shopCart : shopCarts) {
					if(shopCart.getPid()==pid) {
						shopCart.setCount(shopCart.getCount()+1);
						break;
					}
				}
				
				JSONArray jsonarr=jsonArray.fromObject(shopCarts);
				Cookie cookie=new Cookie("cart", jsonarr.toString());
				cookie.setMaxAge(24*60*60*1);
				response.addCookie(cookie);
			}
			response.getWriter().print(true);
			
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/page/errorpage.html");
			e.printStackTrace();
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
