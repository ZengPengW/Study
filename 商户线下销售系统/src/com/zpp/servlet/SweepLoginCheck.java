package com.zpp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.User;
import com.zpp.utils.CookiesUtils;

import Jedis.JedisPoolUtils;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class SweepLoginCheck
 */
@WebServlet("/SweepLoginCheck")
public class SweepLoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //http://localhost:8080/Zpp/SweepLoginCheck?ssid=8158ecec8657fb6571f86566e6876afd
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String ssid=request.getParameter("ssid");
			User user=CookiesUtils.getUser(ssid);		
				if(user==null) {
					throw new RuntimeException("Î´ÖªµÄµÇÂ¼Òì³£");
					
				}else {
					Cookie sid=new Cookie("sid", user.getSid());
					
					sid.setMaxAge(24*60*60);
					response.addCookie(sid);
					
					
				
					request.getSession().removeAttribute("checkcode_session");
					response.sendRedirect("page/index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("isSuccess", false);
			request.getRequestDispatcher("/page/success.jsp").forward(request, response);
		}
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
