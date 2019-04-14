package com.zpp.servlet.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.qrcode.encoder.QRCode;
import com.zpp.domain.Order;
import com.zpp.domain.Product;
import com.zpp.domain.ShopCart;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.Base64Utils;
import com.zpp.utils.CookiesUtils;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@SuppressWarnings({ "deprecation", "unchecked", "static-access" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String shopcart=CookiesUtils.getCookie(request.getCookies(),"cart");
			//System.out.println(shopcart);
			if(shopcart==null||shopcart.isEmpty())throw new RuntimeException("商品信息不存在");
			
			int uid = Integer.parseInt(CookiesUtils.getCookie(request.getCookies(), "uid"));
			
			JSONArray jsonArray=JSONArray.fromObject(shopcart);
			List<ShopCart> list=jsonArray.toList(jsonArray, ShopCart.class);
			
			//List<Product> buyProduct=new ArrayList<Product>();
			//计算支付金额
			SellerService service=new SellerServiceImpl();
			Product p=null;
			double  payMoney=0;
			for (ShopCart ls : list) {
				p=service.GetProductByPid(uid, ls.getPid());
				//buyProduct.add(p);
				payMoney+=(ls.getCount()*p.getPrice());
			}
			
			/*
			 * 一系列支付过程后
			 */
			
			//支付成功 写入数据库
			PayService payService=new PayServiceImpl();
			//更新余额表
		
			boolean bl=payService.updateFinanceOnPay(uid, payMoney);
			
			//插入订单数据
			Date date=new Date(System.currentTimeMillis());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=sdf.format(date);//时间
			
			String gid=null;//取货编号
			bl=true;
			while(bl) {
				int nob=(int) (Math.random()*800000+100000);
				gid=nob+"";
				bl=payService.isExistenceGid(uid, gid);
			}
			
			String username=CookiesUtils.getCookie(request.getCookies(), "username");
			username=Base64Utils.decoder(username);
			String phone=CookiesUtils.getCookie(request.getCookies(), "phone");
			String shopMessage=shopcart;
			String equipment=CookiesUtils.getCookie(request.getCookies(), "equipment");
			if(equipment==null||equipment.isEmpty()) {
				equipment=request.getSession().getId();
				Cookie cookie5 =new Cookie("equipment", equipment);
				cookie5.setMaxAge(24*30*30*365);
				response.addCookie(cookie5);
			}
			Order order=new Order(uid, time, gid, username, phone, shopMessage,equipment,payMoney);
			
			payService.addOrderOnPay(order);
			
			//清空购物车
			Cookie cookie =new Cookie("cart", "cart");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			
			int status=1;
			request.setAttribute("message", "支付成功。。。正在跳转");
			request.setAttribute("status",status);
		
			List<Order> oList=payService.GetOrderByEquipment(uid, equipment);
			time=time+".0";
			for (Order or : oList) {
				
				if(or.getTime().equals(time)){
					
					order=or;
					break;
				}
			}
			
			String json=jsonArray.fromObject(order).toString();
			  ConcurrentHashMap<String, ShowTekeSocket>mywebsocket=ShowTekeSocket.getWebsocket();
			 String temp=null;
			  for(String item: mywebsocket.keySet()){
				  
				  temp=item.substring(0,item.indexOf("a"));
  			 	if(temp.equals(String.valueOf(order.getUid()))){
  			 		//System.out.println(item+"   "+order.getUid());
  			 		try {
							mywebsocket.get(item).sendMessage(json);
						} catch (IOException e) {						
							e.printStackTrace();
							continue;
						}
  			 	}
  	        }
			request.getRequestDispatcher("/page/customer/paySuccess.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/page/errorpage.html");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
