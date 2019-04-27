package com.zpp.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.Order;
import com.zpp.domain.User;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.servlet.client.ShowTekeSocket;
import com.zpp.utils.CookiesUtils;

/**
 * Servlet implementation class SweepLogin
 */
@WebServlet("/SweepLogin")
public class SweepLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String mark;
			
			String sid=CookiesUtils.getCookie(request.getCookies(), "sid");
			String uuid=request.getParameter("uuid");
			if(sid==null||sid.isEmpty()) {
				mark="ÉÌ¼ÒÎ´µÇÂ½ <a href='"+request.getContextPath()+"/page/login.jsp'>µã»÷µÇÂ¼</a>";
				request.setAttribute("status", 0);
				request.setAttribute("message", mark);				
				request.getRequestDispatcher("/page/errorMessage.jsp").forward(request, response);
				return;
			}else{
				ConcurrentHashMap<String, ShowTekeSocket> webSocketSet = ShowTekeSocket.getWebsocket();
				Iterator<Map.Entry<String, ShowTekeSocket>> entries=webSocketSet.entrySet().iterator();
				
				
				
				while(entries.hasNext()){
					Map.Entry<String, ShowTekeSocket> entry=entries.next();
					if(entry.getValue().getSession().isOpen()==false){
						entries.remove();
					}else {
						String item=null;
						item=entry.getKey();
						
						
						if (item.equals(uuid)) {

							try {						
								entry.getValue().sendMessage(sid);
								break;
							} catch (IOException e) {
								e.printStackTrace();
								continue;
							}
						}
					}
				}
				
				mark="µÇÂ¼³É¹¦£¡£¡£¡";
				request.setAttribute("status", 1);
				request.setAttribute("message", mark);				
				request.getRequestDispatcher("/page/errorMessage.jsp").forward(request, response);	
			}
			
			
			
		} catch (Exception e) {
			String mark="Î´Öª´íÎóÊ§°Ü£¡£¡£¡";
			request.setAttribute("message", mark);				
			request.getRequestDispatcher("/page/errorMessage.jsp").forward(request, response);;
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
