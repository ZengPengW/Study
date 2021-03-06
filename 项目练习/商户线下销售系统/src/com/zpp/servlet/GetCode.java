package com.zpp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.utils.MailUtils;

/**
 * ��ȡ������֤��
 */
@WebServlet("/GetCode")
public class GetCode extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	request.setCharacterEncoding("utf-8");
		String email=request.getParameter("email"); 
		String msg=MailUtils.sendMail(email); 
		if(msg!=null) {
			request.getSession().setAttribute("emailmsg", msg);
			request.getSession().setAttribute("currEmail", email);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
