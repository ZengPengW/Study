package com.zpp.servlet.client;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.zpp.domain.Order;
import com.zpp.domain.User;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.utils.CookiesUtils;

/**
 * Servlet implementation class OrderOption
 */
@WebServlet("/OrderOption")
public class OrderOption extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int oid = Integer.parseInt(request.getParameter("oid"));
			int isOption = Integer.parseInt(request.getParameter("isOption"));
			PayService payService = new PayServiceImpl();
			User user = CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
			boolean flag = payService.orderOptionByUid(user.getId(), oid, isOption);
			if(flag) {
				Order order=payService.getOrderByOid(user.getId(), oid);
				String mess = null;
				if (isOption == 2) {
					mess = order.getGid();	
				} else if (isOption == 3) {
					mess = "del"+order.getGid();
				}
				if (mess != null && !mess.isEmpty()) {
					ConcurrentHashMap<String, ShowTekeSocket> webSocketSet = ShowTekeSocket.getWebsocket();
					Iterator<Map.Entry<String, ShowTekeSocket>> entries=webSocketSet.entrySet().iterator();
					
					String temp = null;
					
					while(entries.hasNext()){
						Map.Entry<String, ShowTekeSocket> entry=entries.next();
						if(entry.getValue().getSession().isOpen()==false){
							entries.remove();
						}else {
							String item=null;
							item=entry.getKey();
							temp = item.substring(0, item.indexOf("a"));
							
							if (temp.equals(String.valueOf(user.getId()))) {

								try {						
									entry.getValue().sendMessage(mess);
								} catch (IOException e) {
									e.printStackTrace();
									continue;
								}
							}
						}
					}
					
					

				}
			}
			
			response.getWriter().print(flag);

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(false);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
