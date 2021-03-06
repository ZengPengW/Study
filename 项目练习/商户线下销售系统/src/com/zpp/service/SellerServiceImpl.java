package com.zpp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zpp.dao.Sellerdao;
import com.zpp.dao.impl.SellerdaoImpl;
import com.zpp.domain.Finance;
import com.zpp.domain.Product;
import com.zpp.domain.User;
import com.zpp.utils.CookiesUtils;
import com.zpp.utils.JsonUtils;
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
			jedis.close();
			return dao.alterSid(email, sid);
		}else {
			return false;
		}
	}

	@Override
	public User isExistUser(String name, String password) throws SQLException {
		
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
		
		
		User user=CookiesUtils.getUser(sid);
		if(user==null) {
			
			user=dao.getUserBySid(sid);
			if(user!=null) {
				Jedis jedis=JedisPoolUtils.getJedis();
				JSONArray json=JSONArray.fromObject(user);
				String jsonstr=json.toString();
				jedis.hset("users",sid , jsonstr);
				jedis.close();
			}
		
		}
			return user;
		
	
	}

	@Override
	public boolean AddProduct(Product product) throws SQLException {
		
		return dao.AddProduct(product);
	}

	@Override
	public Long CheckProductCount(int uid,String productClass) throws SQLException {
		
		return dao.CheckProductCount(uid,productClass);
	}

	@Override
	public List<Object> FindProductClass(int uid) throws SQLException {
		return dao.FindProductClass(uid);
		
	}

	@Override
	public List<Product> FindAllProduct(int uid, int currentPage,String productClass) throws SQLException {
		
		return dao.FindAllProduct(uid, currentPage, productClass);
	}

	@Override
	public List<Product> GetProductByName(String likeName, int uid) throws SQLException {
		return dao.GetProductByName(likeName, uid);
	}

	@Override
	public List<Product> OnSaleProductByID(int uid, int currentPage, String productClass) throws SQLException {
		return dao.OnSaleProductByID(uid, currentPage, productClass);
	}

	@Override
	public List<Product> GetOnSaleByName(String likeName, int uid) throws SQLException {
		return dao.GetOnSaleByName(likeName, uid);
	}

	@Override
	public List<Object> FindOnSaleProductClass(int uid) throws SQLException {
		return dao.FindOnSaleProductClass(uid);
	}

	@Override
	public Long CheckOnsaleProductCount(int uid, String productClass) throws SQLException {
		return dao.CheckOnsaleProductCount(uid, productClass);
	}

	@Override
	public boolean DeleteProduct(int uid, int pid) throws SQLException {
		return dao.DeleteProduct(uid, pid);
	}

	@Override
	public Product GetProductByPid(int uid, int pid) throws SQLException {
		return dao.GetProductByPid(uid, pid);
	}

	@Override
	public boolean alterProduct(Product product) throws SQLException {
		return dao.alterProduct(product);
	}

	@Override
	public boolean isExisOnSale(int uid, int pid) throws SQLException {
		return dao.isExisOnSale(uid, pid);
	}

	@Override
	public boolean publishProduct(int uid, int pid) throws SQLException {
		return dao.publishProduct(uid, pid);
	}

	@Override
	public boolean onSaleDelete(int uid, int pid) throws SQLException {
		return dao.onSaleDelete(uid, pid);
	}

	@Override
	public ArrayList<Object> GetOnSalePid(int uid) throws SQLException {
		return dao.GetOnSalePid(uid);
	}

	@Override
	public Finance GetFinanceByUid(int uid) throws SQLException {
		return dao.GetFinanceByUid(uid);
	}

	@Override
	public boolean alterUserNameByUid(int uid, String username) throws SQLException {
		return dao.alterUserNameByUid(uid, username);
	}

	@Override
	public boolean alterUserShopNameByUid(int uid, String shopname) throws SQLException {
		if(dao.alterUserShopNameByUid(uid, shopname)){
			Jedis jedis=JedisPoolUtils.getJedis();
			User user=dao.getUserById(uid);
			
			String jsonstr=JsonUtils.toJsonString(user);
			jedis.hdel("users", user.getSid());
			jedis.hset("users", user.getSid(), jsonstr);
			jedis.close();
			return true;
		}else
			return false;
	}

	@Override
	public boolean alterFinancePayByUid(int uid, String pay) throws SQLException {
		// TODO Auto-generated method stub
		return dao.alterFinancePayByUid(uid, pay);
	}

	@Override
	public User getUserById(int uid) throws SQLException {
		return dao.getUserById(uid);
	}
	
	

}
