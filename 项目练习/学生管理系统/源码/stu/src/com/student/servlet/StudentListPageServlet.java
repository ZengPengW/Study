package com.student.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.domain.PageBean;
import com.student.domain.Student;
import com.student.servlect.StudentService;
import com.student.servlect.StudentServiceImpl;

/**
 * 分页查询
 */
public class StudentListPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//第几页
		int currentPage =Integer.parseInt(request.getParameter("currentPage"));
		//根据页数获取数据
		StudentService service=new StudentServiceImpl();
		try {
			PageBean<Student> pageBean=service.findStudentByPage(currentPage);
			request.setAttribute("pagebean", pageBean);
			request.getRequestDispatcher("list_page.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
