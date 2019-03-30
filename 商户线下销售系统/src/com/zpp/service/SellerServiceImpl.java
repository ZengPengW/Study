package com.zpp.service;

import java.sql.SQLException;
import java.util.List;

import com.zpp.dao.Sellerdao;
import com.zpp.dao.impl.SellerdaoImpl;
import com.zpp.domain.Product;
import com.zpp.domain.User;
import com.zpp.utils.SidUtils;

import Jedis.JedisPoolUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

public class SellerServiceImpl implements SellerService {
	Sellerdao dao=new SellerdaoImpl();
	@Override
	public boolean register(User user) throws SQLException {
		
		return dao.register(user);
	}

	@Override
	public boolean isExistName(String username) throws SQLException {
	
		return dao.isExistName(username);
	}

	@Override
	public boolean isExistEmail(String email) throws SQLException {
		
		return dao.isExistEmail(email);
	}

	@Override
	public boolean upPassWord(String email, String newPassWord) throws SQLException {
		
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
		
		return dao.isExistUser(name, password);
	}

	@Override
	public User getUserByName(String name) throws SQLException {
		
		return dao.getUser(name);
	}

	@Override
	public boolean isExisProductName(String productname, int uid) throws SQLException {
		
		return dao.isExisProductName(productname, uid );
	}

	@Override
	public User getUserBySid(String sid) throws SQLException {
		
		Jedis jedis=JedisPoolUtils.getJedis();
		String userJson=jedis.hget("users", sid);
		if(userJson==null) {
			
			User user=dao.getUserBySid(sid);
			if(user!=null) {
				JSONArray json=JSONArray.fromObject(user);
				String jsonstr=json.toString();
				jedis.hset("users",user.getSid() , jsonstr);
			}
			return user;
		}else {
			
			JSONArray jsonArray= new JSONArray().fromObject(userJson);
			Object o=jsonArray.get(0);
			JSONObject jsonObject2=JSONObject.fromObject(o);
			User user =(User)JSONObject.toBean(jsonObject2, User.class);
			//System.out.println(user.getEmail()+" "+user.getPassword());
			return user;
		}
	
	}

	@Override
	public boolean AddProduct(Product product) throws SQLException {
		
		return dao.AddProduct(product);
	}

	@Override
	public Long CheckProductCount(int uid) throws SQLException {
		
		return dao.CheckProductCount(uid);
	}

	@Override
	public List<Object> FindProductClass(int uid) throws SQLException {
		return dao.FindProductClass(uid);
		
	}
	
	

}
