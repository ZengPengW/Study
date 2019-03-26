package com.zpp.service;

import java.sql.SQLException;

import com.zpp.dao.Sellerdao;
import com.zpp.dao.impl.SellerdaoImpl;
import com.zpp.domain.User;

public class SellerServiceImpl implements SellerService {

	@Override
	public boolean register(User user) throws SQLException {
		Sellerdao dao=new SellerdaoImpl();
		return dao.register(user);
	}

	@Override
	public boolean isExistName(String username) throws SQLException {
		Sellerdao dao=new SellerdaoImpl();
		return dao.isExistName(username);
	}

	@Override
	public boolean isExistEmail(String email) throws SQLException {
		Sellerdao dao=new SellerdaoImpl();
		return dao.isExistEmail(email);
	}
	
	

}
