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
	//	request.setCharacterEncoding("utf-8");
		User user=new User();
		try {
			SellerService service=new SellerServiceImpl();
			BeanUtils.populate(user, request.getParameterMap());
			if(user.getUsername().indexOf(" ")!=-1||user.getPassword().indexOf(" ")!=-1||user.getEmail().indexOf(" ")!=-1||user.getShopname().indexOf(" ")!=-1) {
				throw new RuntimeException("�����пո�");
			}
			
			if(user.getPassword().length()<6||user.getPassword().length()>18) {
				throw new RuntimeException("���볤�ȴ���");
			}
			if(user.getUsername().length()<3||user.getUsername().length()>20) {
				throw new RuntimeException("�û������ȴ���");
			}
			boolean flag=service.isExistEmail(user.getEmail());
			if(flag){
				throw new RuntimeException("�����Ѿ�����");
			}
			flag=service.isExistName(user.getUsername());
			if(flag){
				throw new RuntimeException("�û����Ѿ�����");
			}
			String currEmail=(String) request.getSession().getAttribute("currEmail");
			if(!currEmail.equals(user.getEmail())){
				throw new RuntimeException("���䱻�����޸�");
			}
			user.setSid(SidUtils.getSid(user.getUsername(), user.getPassword()));
			
			boolean bl=service.register(user);
			if(bl) {
				response.sendRedirect("page/registerSuccess.html");
				
			}else {
				response.sendRedirect("page/registerfail.html");
				//request.getRequestDispatcher(.forward(request, response);
			}
			
		} catch (IllegalAccessException | InvocationTargetException | SQLException e) {
			response.sendRedirect("page/registerfail.html");
			e.printStackTrace();
		}finally {
			request.getSession().removeAttribute("emailmsg");
			request.getSession().removeAttribute("currEmail");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
