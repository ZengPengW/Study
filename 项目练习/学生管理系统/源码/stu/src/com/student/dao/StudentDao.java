package com.student.dao;

import java.sql.SQLException;
import java.util.List;

import com.student.domain.Student;
//ѧ��������ݿ����
public interface StudentDao {
	 final int PAGE_SIZE=5; 
	/*
	 * ��ѯ����ѧ��
	 * return List����
	 */
	public List<Student> findAll() throws SQLException;
	//�����������Ա����
	public  List<Student> searchStudent(String sname,String sgender) throws SQLException;
	//���ݵڼ�ҳ����
		public  List<Student> findStudentByPage(int currentPage) throws SQLException;
	//�鵥��ѧ��
	public Student findStudentById(int sid) throws SQLException;
	
	//���ѧ��
	public void insert(Student student) throws SQLException;
	
	//ɾ��ѧ��
	public void delete(int sid) throws SQLException;
	
	//����ѧ��
	public void edit(Student student) throws SQLException;
	
	//��ѯ�ܼ�¼��
	public int findCount() throws SQLException;
}
