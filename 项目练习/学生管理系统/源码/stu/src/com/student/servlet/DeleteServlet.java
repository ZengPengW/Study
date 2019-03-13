package com.student.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.servlect.StudentService;
import com.student.servlect.StudentServiceImpl;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sid=Integer.parseInt(request.getParameter("sid"));
		StudentService service=new StudentServiceImpl();
	//	System.out.println("É¾³ý²Ù×÷");
		try {
			service.delete(sid);
			request.getRequestDispatcher("StudentListServlet").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
