package com.zpp.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.zpp.domain.User;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.SidUtils;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		User user=new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			user.setSid(SidUtils.getSid(user.getUsername(), user.getPassword()));
			SellerService service=new SellerServiceImpl();
			boolean bl=service.register(user);
			if(bl) {
				response.sendRedirect("page/registerSuccess.html");
				
			}else {
				response.sendRedirect("page/registerfail.html");
				//request.getRequestDispatcher(.forward(request, response);
			}
			
		} catch (IllegalAccessException | InvocationTargetException | SQLException e) {
			request.getRequestDispatcher("page/registerfail.html").forward(request, response);
			//e.printStackTrace();
		}finally {
			request.getSession().removeAttribute("msg");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
