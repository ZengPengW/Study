package com.zpp.servlet.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.Order;
import com.zpp.domain.Product;
import com.zpp.domain.ShopCart;
import com.zpp.domain.ShopCartBean;
import com.zpp.domain.User;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.CookiesUtils;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class UserOrderDetails
 */
@WebServlet("/UserOrderDetails")
public class UserOrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int oid=Integer.parseInt(request.getParameter("oid"));
			User user =CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
			int uid=user.getId();
			PayService payService=new PayServiceImpl();
			Order order=payService.getOrderByOid(uid, oid);
			String json=order.getShopMessage();
			JSONArray jsonArray=JSONArray.fromObject(json);
			List<ShopCart> shopCarts=jsonArray.toList(jsonArray, ShopCart.class);
			
			List<ShopCartBean> list=new ArrayList<ShopCartBean>(shopCarts.size());
			SellerService service=new SellerServiceImpl();
			Product product=null;
			ShopCartBean shopCartBean=null;
			for (ShopCart shop : shopCarts) {
				
				product=service.GetProductByPid(uid, shop.getPid());
				shopCartBean=new ShopCartBean(product,shop.getCount());
				list.add(shopCartBean);
			}
			String jsons=JSONArray.fromObject(list).toString();
			
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(jsons);
		} catch (Exception e) {
			response.getWriter().print("");
			e.printStackTrace();
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
