package com.student.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.domain.Student;
import com.student.servlect.StudentService;
import com.student.servlect.StudentServiceImpl;

/**
 *模糊查询
 */
public class SearchStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//取值
		String sname=request.getParameter("sname");
		String sgender=request.getParameter("sgender");
		
		//查询
		StudentService service=new StudentServiceImpl();
		try {
			List<Student> students=service.searchStudent(sname, sgender);
			request.setAttribute("students", students);
			request.getRequestDispatcher("list.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
