package com.zpp.service;

import java.sql.SQLException;

import com.zpp.domain.User;

public interface SellerService {
	public boolean register(User user) throws SQLException;
	public boolean isExistName(String username)throws SQLException; 
	public boolean isExistEmail(String email)throws SQLException; 
	public boolean upPassWord(String email,String newPassWord) throws SQLException; 
	public boolean isExistUser (String name,String password)throws SQLException;
	public User getUserByName(String name)throws SQLException;

}
