package com.zpp.servlet.client;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.Order;
import com.zpp.domain.User;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.service.SellerService;
import com.zpp.utils.CookiesUtils;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class SweepTeke
 */
@WebServlet("/SweepTeke")
public class SweepTeke extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //http://127.0.0.1:8080/Zpp/SweepTeke?uid=18&oid=1&equipment=FCBF987CEB05B9466F980E5D04C55FD4
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String mark;
			
			String sid=CookiesUtils.getCookie(request.getCookies(), "sid");
			if(sid==null||sid.isEmpty()) {
				mark="商家未登陆";
				request.setAttribute("status", 0);
				request.setAttribute("message", mark);				
				request.getRequestDispatcher("/page/errorMessage.jsp").forward(request, response);
				return;
			}
			User user=CookiesUtils.getUser(sid);
			
			int oid=Integer.parseInt(request.getParameter("oid"));
			int ouid=Integer.parseInt(request.getParameter("uid"));
			String equipment=request.getParameter("equipment");
			if(user.getId()!=ouid) {
				mark="此订单不是此商家的";
				request.setAttribute("status", 0);
				request.setAttribute("message", mark);				
				request.getRequestDispatcher("/page/errorMessage.jsp").forward(request, response);;
				return;
			}
			PayService payService=new PayServiceImpl();
			Order order=payService.GetOrderByOid(ouid, equipment, oid);
			if(order==null) {
				mark="此订单不存在";
				request.setAttribute("status", 0);
				request.setAttribute("message", mark);				
				request.getRequestDispatcher("/page/errorMessage.jsp").forward(request, response);;
				return;
			}
			
			if(order.getIsteke()!=0) {
				mark="此订单已经取过货了 请勿重复取货！！！";
				request.setAttribute("status", 0);
				request.setAttribute("message", mark);				
				request.getRequestDispatcher("/page/errorMessage.jsp").forward(request, response);;
				return;
			}
			if(payService.tekeOrder(ouid, equipment, oid)) {
				mark="确认取货成功！！！";
				request.setAttribute("status", 1);
				request.setAttribute("message", mark);
				request.setAttribute("uid", ouid);	
				request.setAttribute("oid", oid);
				
				
				  ConcurrentHashMap<String, ShowTekeSocket>mywebsocket=ShowTekeSocket.getWebsocket();
				 String temp=null;
				 if(mywebsocket.size()>0){
					 for(String item: mywebsocket.keySet()){
						  
						  temp=item.substring(0,item.indexOf("a"));
		  			 	if(temp.equals(String.valueOf(ouid))){
		  			 		//System.out.println(item+"   "+order.getUid());
		  			 		try {
									mywebsocket.get(item).sendMessage("del"+order.getGid());
								} catch (IOException e) {						
									e.printStackTrace();
									continue;
								}
		  			 	}
		  	        }
				 }
				
				
				
				
				request.getRequestDispatcher("/page/errorMessage.jsp").forward(request, response);;
				return;
			}else {
				mark="未知错误取货失败！！！";
				request.setAttribute("status", 0);
				request.setAttribute("message", mark);				
				request.getRequestDispatcher("/page/errorMessage.jsp").forward(request, response);;
				return;
			}
			
			
		} catch (Exception e) {
			String mark="未知错误取货失败！！！";
			request.setAttribute("message", mark);				
			request.getRequestDispatcher("/page/errorMessage.jsp").forward(request, response);;
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
