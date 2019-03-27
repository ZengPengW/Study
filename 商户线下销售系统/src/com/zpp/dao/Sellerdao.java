package com.zpp.dao;

import java.sql.SQLException;

import com.zpp.domain.User;

public interface Sellerdao {

	public boolean register(User user) throws SQLException;
	public boolean isExistName(String username)throws SQLException; 	
	public boolean isExistEmail(String email)throws SQLException; 
	public boolean upPassWord(String email,String newPassWord) throws SQLException; 
	public User getUser(String name)throws SQLException;
	public boolean alterSid(String email,String sid)throws SQLException;
	public boolean isExistUser (String name,String password)throws SQLException;
}
