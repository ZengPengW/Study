package com.student.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.student.dao.StudentDao;
import com.student.domain.Student;
import com.student.utils.DataSourceUtils;

public class StudentDaoImpl implements StudentDao {

	/*
	 * 查询所有学生 return List集合
	 */
	@Override
	public List<Student> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		List<Student> list = null;
		String sql = "select * from stu";
		list = qr.query(sql, new BeanListHandler<Student>(Student.class));
		return list;

	}

	@Override
	public void insert(Student student) throws SQLException {
		String sql = "insert into stu values(null,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		qr.update(sql, student.getSname(), student.getGender(), student.getPhone(), student.getBirthday(),
				student.getHobby(), student.getInfo());

	}

	@Override
	public void delete(int sid) throws SQLException {
		String sql = "DELETE FROM stu WHERE sid=?";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		qr.update(sql,sid);
		
	}

	@Override
	public void edit(Student student) throws SQLException {
		String sql="update stu set sname=?,gender=?,phone=?,birthday=?,hobby=?,info=? where sid=?";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		qr.update(sql, student.getSname(), student.getGender(), student.getPhone(), student.getBirthday(),
				student.getHobby(), student.getInfo(),student.getSid());
	}

	@Override
	public Student findStudentById(int sid) throws SQLException {
		String sql = "select * FROM stu WHERE sid=?";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		Student student=qr.query(sql,new BeanHandler<Student>(Student.class),sid);
		return student;
	}

	@Override
	public  List<Student> searchStudent(String sname, String sgender) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * FROM stu WHERE 1=1 ";
		List<String> list=new ArrayList<String>();
		if(sname!=null&&sname.length()>0) {
			sql=sql+"and sname like ?";
			list.add("%"+sname+"%");
		}
		if(sgender!=null&&sgender.length()>0) {
			list.add(sgender);
			sql=sql+"and gender=?";
		}
		//System.out.println(sql+" "+sgender+" "+sname);
		List<Student> liststu=qr.query(sql,new BeanListHandler<Student>(Student.class),list.toArray());
		return liststu;
	}

	@Override
	public List<Student> findStudentByPage(int currentPage) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		List<Student> list = null;
		int len=PAGE_SIZE;
		String sql = "select * from stu limit ? offset ?";
		list = qr.query(sql, new BeanListHandler<Student>(Student.class),len,len*(currentPage-1));
		return list;
	}

	@Override
	public int findCount() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select count(*) from stu";
		Long count = (Long) qr.query(sql, new ScalarHandler());
		return count.intValue();
	}
}
