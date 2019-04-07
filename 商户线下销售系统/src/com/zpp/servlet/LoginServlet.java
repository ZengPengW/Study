package com.zpp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.zpp.domain.User;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;

import Jedis.JedisPoolUtils;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		String auto_login=request.getParameter("auto_login");
	//	System.out.println(name+auto_login);
		SellerService service=new SellerServiceImpl();
		
		try {
			User user=service.isExistUser(name, password);
			if(user==null) {
				throw new RuntimeException("Î´ÖªµÄµÇÂ¼Òì³£");
				
			}else {
				Cookie sid=new Cookie("sid", user.getSid());
				if("on".equals(auto_login)){
					sid.setMaxAge(24*60*60*365);
					response.addCookie(sid);
				}else {
					sid.setMaxAge(20*60*60);
					response.addCookie(sid);
				}
				
				Jedis jedis=JedisPoolUtils.getJedis();
				boolean hexists=jedis.hexists("users",user.getSid());
				if(!hexists) {
					JSONArray json=JSONArray.fromObject(user);
					String jsonstr=json.toString();
					jedis.hset("users",user.getSid() , jsonstr);
				}
				
				jedis.close();
				//System.out.println(jsonstr);
				request.getSession().removeAttribute("checkcode_session");
				response.sendRedirect("page/index.jsp");
			}
			
		} catch (Exception e) {
			request.setAttribute("isSuccess", false);
			request.getRequestDispatcher("/page/success.jsp").forward(request, response);
			//	e.printStackTrace();
		}
			
		
		
		
		
		
		//"on"
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
