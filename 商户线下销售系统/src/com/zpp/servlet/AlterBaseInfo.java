package com.zpp.servlet;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.User;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.CookiesUtils;
import com.zpp.utils.JsonUtils;
import com.zpp.utils.SidUtils;

import Jedis.JedisPoolUtils;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class AlterBaseInfo
 */
@WebServlet("/AlterBaseInfo")
public class AlterBaseInfo extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String shopname=request.getParameter("shopname");
		String payment=request.getParameter("payment");
		String yanzhengma=request.getParameter("yanzhengma");
		String emailmsg=String.valueOf(request.getSession().getAttribute("emailmsg"));
		request.getSession().removeAttribute("emailmsg");
		User user=CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
		String oldusername=user.getUsername();
		int uid=user.getId();
		String oldsid=user.getSid();
		try {
			
			if(!emailmsg.equals(yanzhengma)) {
				throw new RuntimeException("ÑéÖ¤Âë´íÎó");
			}else {
				SellerService service=new SellerServiceImpl();
				if(payment!=null&&!payment.isEmpty())service.alterFinancePayByUid(uid, payment);
				if(!user.getShopname().equals(shopname)){
					service.alterUserShopNameByUid(uid, shopname);
					user.setShopname(shopname);
				}
				
				
				if((username!=null&&!username.isEmpty())&&!oldusername.equals(username)&&service.alterUserNameByUid(uid, username)) {
					
					user.setUsername(username);
					Jedis jedis=JedisPoolUtils.getJedis();
					String newsid= SidUtils.getSid(username, user.getPassword());
					user.setSid(newsid);
					
					JSONArray jsonArray=JSONArray.fromObject(user);
					jedis.hdel("users", oldsid);
					jedis.hset("users", newsid, jsonArray.toString());
					jedis.close();
					Cookie cookie=new Cookie("sid", newsid);
					cookie.setMaxAge(24*60*60*30);
					response.addCookie(cookie);
				}
				
				request.setAttribute("isSuccess", true);
			}
			
		} catch (Exception e) {
			request.setAttribute("isSuccess", false);
			e.printStackTrace();
		}finally {
			request.getRequestDispatcher("/page/success.jsp").forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
