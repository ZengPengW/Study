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

}
