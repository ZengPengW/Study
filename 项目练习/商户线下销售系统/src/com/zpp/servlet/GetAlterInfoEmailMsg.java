package com.zpp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.User;
import com.zpp.utils.CookiesUtils;
import com.zpp.utils.MailUtils;

/**
 * Servlet implementation class GetAlterLnfoEmailMsg
 */
@WebServlet("/GetAlterInfoEmailMsg")
public class GetAlterInfoEmailMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user =CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
			String email=user.getEmail(); 
			String msg=MailUtils.sendMail(email); 
			if(msg!=null) {
				request.getSession().setAttribute("emailmsg", msg);
				response.getWriter().print(true);
			}
		} catch (Exception e) {
			response.getWriter().print(false);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
