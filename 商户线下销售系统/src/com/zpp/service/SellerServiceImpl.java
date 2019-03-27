package com.zpp.service;

import java.sql.SQLException;

import com.zpp.dao.Sellerdao;
import com.zpp.dao.impl.SellerdaoImpl;
import com.zpp.domain.User;
import com.zpp.utils.SidUtils;

import Jedis.JedisPoolUtils;
import redis.clients.jedis.Jedis;

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

	@Override
	public boolean upPassWord(String email, String newPassWord) throws SQLException {
		Sellerdao dao=new SellerdaoImpl();
		if(dao.upPassWord(email, newPassWord)) {
			User user=dao.getUser(email);
			Jedis jedis=JedisPoolUtils.getJedis();
			jedis.hdel("users", user.getSid());
			String sid=SidUtils.getSid(user.getUsername(), user.getPassword());
			return dao.alterSid(email, sid);
		}else {
			return false;
		}
	}

	@Override
	public boolean isExistUser(String name, String password) throws SQLException {
		Sellerdao dao=new SellerdaoImpl();
		return dao.isExistUser(name, password);
	}

	@Override
	public User getUserByName(String name) throws SQLException {
		Sellerdao dao=new SellerdaoImpl();
		return dao.getUser(name);
	}
	
	

}
