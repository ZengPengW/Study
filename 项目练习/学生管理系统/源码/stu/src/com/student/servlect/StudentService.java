package com.student.servlect;

import java.sql.SQLException;
import java.util.List;

import com.student.domain.PageBean;
import com.student.domain.Student;
/*
 * 这是学生业务处理规范
 */
public interface StudentService {
	/*
	 * 查询所有学生
	 * return List集合
	 */
	public List<Student> findAll() throws SQLException;
	public void insert(Student student) throws SQLException;
	public void delete(int sid) throws SQLException;
	
	public Student findStudentById(int sid) throws SQLException;
	public void edit(Student student) throws SQLException;
	
	public List<Student> searchStudent(String sname,String sgender) throws SQLException;
	public  PageBean<Student> findStudentByPage(int currentPage) throws SQLException;
}
