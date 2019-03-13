package com.student.dao;

import java.sql.SQLException;
import java.util.List;

import com.student.domain.Student;
//学生表的数据库操作
public interface StudentDao {
	 final int PAGE_SIZE=5; 
	/*
	 * 查询所有学生
	 * return List集合
	 */
	public List<Student> findAll() throws SQLException;
	//根据姓名或性别查找
	public  List<Student> searchStudent(String sname,String sgender) throws SQLException;
	//根据第几页查找
		public  List<Student> findStudentByPage(int currentPage) throws SQLException;
	//查单个学生
	public Student findStudentById(int sid) throws SQLException;
	
	//添加学生
	public void insert(Student student) throws SQLException;
	
	//删除学生
	public void delete(int sid) throws SQLException;
	
	//更新学生
	public void edit(Student student) throws SQLException;
	
	//查询总记录数
	public int findCount() throws SQLException;
}
