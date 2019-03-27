package com.zpp.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.zpp.dao.Sellerdao;
import com.zpp.domain.User;
import com.zpp.utils.DataSourceUtils;

public class SellerdaoImpl implements Sellerdao {

	@Override
	public boolean register(User user) throws SQLException {
		try {
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			String sql="insert into users(sid,username,password,email,shopname)values (?,?,?,?,?)";
			qr.update(sql,user.getSid(),user.getUsername(),user.getPassword(),user.getEmail(),user.getShopname());
			
			sql="select * from users where username=? ";
			User us=qr.query(sql,new BeanHandler<User>(User.class) ,user.getUsername());
			
			if(us!=null) return true;
			
		} catch (Exception e) {
			return false;
		}
		
		return false;
		
	}

	@Override
	public boolean isExistName(String username) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="SELECT * FROM users WHERE username=?";
		User user=qr.query(sql, new BeanHandler<User>(User.class), username);
		if(user==null)return false;
		else return true;
		
	}

	@Override
	public boolean isExistEmail(String email) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="SELECT * FROM users WHERE email=?";
		User user=qr.query(sql, new BeanHandler<User>(User.class), email);
		if(user==null)return false;
		else return true;
	}

	@Override
	public boolean upPassWord(String email,String newPassWord) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="UPDATE  users SET password=? WHERE email=?";
		int isSuccess=qr.update(sql,newPassWord ,email);
		if(isSuccess==1)return true;
		else return false;
	}

	@Override
	public User getUser(String name) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="SELECT * FROM users WHERE email=? or username=?";
		User user=qr.query(sql, new BeanHandler<User>(User.class), name,name);
		return user;
	}

	@Override
	public boolean alterSid(String email,String sid) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="UPDATE  users SET sid=? WHERE email=?";
		int isSuccess=qr.update(sql,sid ,email);
		if(isSuccess==1)return true;
		else return false;
	}

	@Override
	public boolean isExistUser(String name, String password) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="SELECT * FROM users WHERE (email=? or username=?) and password=?";
		User user=qr.query(sql, new BeanHandler<User>(User.class), name,name,password);
		if(user==null)return false;
		else return true;
	}

}
