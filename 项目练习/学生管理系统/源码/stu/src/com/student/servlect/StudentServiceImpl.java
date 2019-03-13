package com.student.servlect;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.student.dao.StudentDao;
import com.student.dao.impl.StudentDaoImpl;
import com.student.domain.PageBean;
import com.student.domain.Student;
/*
 * 学生业务实现
 */
public class StudentServiceImpl implements StudentService {

	@Override
	public List<Student> findAll() throws SQLException {
		StudentDao dao=new StudentDaoImpl();
		return dao.findAll();
	}

	@Override
	public void insert(Student student) throws SQLException {
		StudentDao dao=new StudentDaoImpl();
		dao.insert(student);
	}
	@Override
	public void delete(int sid) throws SQLException {
		StudentDao dao=new StudentDaoImpl();
		dao.delete(sid);
	}

	@Override
	public Student findStudentById(int sid) throws SQLException {
		StudentDao dao=new StudentDaoImpl();
		return dao.findStudentById(sid);
	}

	@Override
	public void edit(Student student) throws SQLException {
		StudentDao dao=new StudentDaoImpl();
		 dao.edit(student);;
		
	}

	@Override
	public List<Student> searchStudent(String sname, String sgender) throws SQLException {
		StudentDao dao=new StudentDaoImpl();
		return dao.searchStudent(sname, sgender);
	}

	@Override
	public PageBean<Student> findStudentByPage(int currentPage) throws SQLException {
		StudentDao dao=new StudentDaoImpl();
		PageBean<Student> pageBean=new PageBean<Student>();
		int pagesize=StudentDao.PAGE_SIZE;
		pageBean.setPageSize(pagesize);
		pageBean.setCurrentPage(currentPage);
		
		List<Student> list=dao.findStudentByPage(currentPage);
		pageBean.setList(list);
		
		//总页数  总记录数
		int count =dao.findCount();
		pageBean.setTotalSize(count);
		pageBean.setTotalPage(count%StudentDao.PAGE_SIZE==0?count/pagesize:count/pagesize+1);
		
		return pageBean;
	}
}
