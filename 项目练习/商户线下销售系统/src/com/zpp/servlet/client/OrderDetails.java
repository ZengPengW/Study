package com.zpp.servlet.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.Order;
import com.zpp.domain.Product;
import com.zpp.domain.ShopCart;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.CookiesUtils;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class OrderDetails
 */
@WebServlet("/OrderDetails")
public class OrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SellerService service=new SellerServiceImpl();
	private PayService payservice=new PayServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String oids=request.getParameter("oid");
			if(oids==null||oids.isEmpty())throw new RuntimeException("订单空指针");
			int oid=Integer.parseInt(oids);
			String equipment=CookiesUtils.getCookie(request.getCookies(), "equipment");
			String uids=CookiesUtils.getCookie(request.getCookies(), "uid");
			if(equipment==null||equipment.isEmpty()||uids==null||uids.isEmpty())throw new RuntimeException("用户空指针");
			int uid=Integer.parseInt(uids);
			
		//	PayService payservice=new PayServiceImpl();
			Order order=payservice.GetOrderByOid(uid, equipment, oid);
			String cart=order.getShopMessage();
			
			List<ShopCart> shopCarts = null;
			if (cart != null) {
				JSONArray jsonArray = JSONArray.fromObject(cart);
				shopCarts = jsonArray.toList(jsonArray, ShopCart.class);
			}
			List<Product> cartList = new ArrayList<Product>();
			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			if (shopCarts != null) {
			//	SellerService service = new SellerServiceImpl();
				
				Product p = null;
				
				double sumPrice = 0;
				for (ShopCart sp : shopCarts) {
					p = service.GetProductByPid(uid, sp.getPid());
					cartList.add(p);
					hm.put(sp.getPid(), sp.getCount());
					sumPrice += (p.getPrice() * sp.getCount());
				}
				
				request.setAttribute("cartlist", cartList);
				request.setAttribute("hm", hm);
				request.setAttribute("sumPrice", sumPrice);
			}	
			request.getRequestDispatcher("/page/customer/orderDetails.jsp").forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/page/errorpage.html");
			e.printStackTrace();
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
