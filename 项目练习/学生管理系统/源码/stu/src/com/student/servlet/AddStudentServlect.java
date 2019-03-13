package com.student.servlet;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.domain.Student;
import com.student.servlect.StudentService;
import com.student.servlect.StudentServiceImpl;

public class AddStudentServlect extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
//		Map<String, String[]> map=request.getParameterMap();
//		
//		Student student=new Student();
//		try {
//			BeanUtils.populate(student,map);
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}
//		
		//客户端获取数据
		String sname=request.getParameter("sname");
		String gender=request.getParameter("gender");
		String phone=request.getParameter("phone");
		String brithder=request.getParameter("birthday");
		String[] hobby=request.getParameterValues("hobby");
		String info=request.getParameter("info");
		
		String hobbys="";
		for (String s : hobby) {
			hobbys+=(s+", ");
		}
		hobbys=hobbys.substring(0, hobbys.length()-2);
		//System.out.println(hobbys);
		try {
			//添加数据
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(brithder);
			Student student=new Student(sname,gender,phone,date,hobbys,info);
			StudentService service=new StudentServiceImpl();
			service.insert(student);
			
			//跳转到列表页面
			response.sendRedirect("StudentListServlet");
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
