package com.zpp.dao;

import java.sql.SQLException;

import com.zpp.domain.User;

public interface Sellerdao {

	public boolean register(User user) throws SQLException;
	public boolean isExistName(String username)throws SQLException; 	
	public boolean isExistEmail(String email)throws SQLException; 
}
