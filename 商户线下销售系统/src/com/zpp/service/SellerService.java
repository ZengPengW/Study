package com.zpp.service;

import java.sql.SQLException;
import java.util.List;

import com.zpp.domain.Product;
import com.zpp.domain.User;

public interface SellerService {
	public boolean register(User user) throws SQLException;
	public boolean isExistName(String username)throws SQLException; 
	public boolean isExistEmail(String email)throws SQLException; 
	public boolean upPassWord(String email,String newPassWord) throws SQLException; 
	public boolean isExistUser (String name,String password)throws SQLException;
	public User getUserByName(String name)throws SQLException;
	public boolean isExisProductName(String productname,int uid)throws SQLException;
	public User getUserBySid(String sid)throws SQLException;
	public boolean AddProduct(Product product)throws SQLException;
	public Long CheckProductCount(int uid,String productClass)throws SQLException;
	public List<Object> FindProductClass(int uid)throws SQLException;
	public List<Product> FindAllProduct(int uid,int currentPage,String productClass)throws SQLException;
	
}
