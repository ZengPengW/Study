package com.zpp.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import com.zpp.dao.Sellerdao;
import com.zpp.domain.Product;
import com.zpp.domain.User;
import com.zpp.utils.DataSourceUtils;

import Jedis.JedisPoolUtils;
import redis.clients.jedis.Jedis;

public class SellerdaoImpl implements Sellerdao {

	@Override
	public boolean register(User user) throws SQLException {
		try {
			QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "insert into users(sid,username,password,email,shopname)values (?,?,?,?,?)";
			qr.update(sql, user.getSid(), user.getUsername(), user.getPassword(), user.getEmail(), user.getShopname());

			sql = "select * from users where username=? ";
			User us = qr.query(sql, new BeanHandler<User>(User.class), user.getUsername());

			if (us != null)
				return true;

		} catch (Exception e) {
			return false;
		}

		return false;

	}

	@Override
	public boolean isExistName(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM users WHERE username=?";
		User user = qr.query(sql, new BeanHandler<User>(User.class), username);
		if (user == null)
			return false;
		else
			return true;

	}

	@Override
	public boolean isExistEmail(String email) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM users WHERE email=?";
		User user = qr.query(sql, new BeanHandler<User>(User.class), email);
		if (user == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean upPassWord(String email, String newPassWord) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "UPDATE  users SET password=? WHERE email=?";
		int isSuccess = qr.update(sql, newPassWord, email);
		if (isSuccess == 1)
			return true;
		else
			return false;
	}

	@Override
	public User getUser(String name) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM users WHERE email=? or username=?";
		User user = qr.query(sql, new BeanHandler<User>(User.class), name, name);
		return user;
	}

	@Override
	public boolean alterSid(String email, String sid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "UPDATE  users SET sid=? WHERE email=?";
		int isSuccess = qr.update(sql, sid, email);
		if (isSuccess == 1)
			return true;
		else
			return false;
	}

	@Override
	public boolean isExistUser(String name, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM users WHERE (email=? or username=?) and password=?";
		User user = qr.query(sql, new BeanHandler<User>(User.class), name, name, password);
		if (user == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean isExisProductName(String productname, int uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE uid=?  and productName=?";
		Product product = qr.query(sql, new BeanHandler<Product>(Product.class), uid, productname);
		if (product == null)
			return false;
		else
			return true;
	}

	@Override
	public User getUserBySid(String sid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM users WHERE sid=?";
		User user = qr.query(sql, new BeanHandler<User>(User.class), sid);
		return user;
	}

	@Override
	public boolean AddProduct(Product product) throws SQLException {

		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product(uid,productName,price,productCount,productImg,productMessage,productClass)values (?,?,?,?,?,?,?)";
	
		int mark = qr.update(sql, product.getUid(), product.getProductName(), product.getPrice(),
				product.getProductCount(),product.getProductImg(), product.getProductMessage(), product.getProductClass());
		if (mark == 1)
			return true;
		return false;
	}

	@Override
	public Long CheckProductCount(int uid,String productClass) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT count(*) FROM product WHERE uid=?";
		Long count=0l ;
		if("全部".equals(productClass)){
		 count =(Long) qr.query(sql, new ScalarHandler(), uid);
		}else {
			sql+=" and productClass=?";
			count =(Long) qr.query(sql, new ScalarHandler(), uid,productClass);
		}
		return count;
	}

	@Override
	public List<Object> FindProductClass(int uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		//System.out.println("SELECT DISTINCT productClass FROM product where uid="+uid);
		String sql = "SELECT DISTINCT productClass FROM product where uid=?";
		List<Object[]> ProductClass=qr.query(sql, new ArrayListHandler(),uid);
		
		
		List<Object> al= new ArrayList<Object>();
		for (Object[] object : ProductClass) {
			for (Object obj : object) {
				//System.out.println(obj);
				al.add(obj);
			}
		}
		return al;
	}

	public static final int pageSize=5;
	@Override
	public List<Product> FindAllProduct(int uid, int currentPage,String productClass) throws SQLException {
		
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		List<Product> list=null;
		if("全部".equals(productClass)){
			String sql="select * from product where uid=? limit ? offset ?";
		    list = qr.query(sql, new BeanListHandler<Product>(Product.class),uid,pageSize,pageSize*(currentPage-1));
		}else {
			String sql="select * from product where uid=? and productClass=? limit ? offset ?";
			list = qr.query(sql, new BeanListHandler<Product>(Product.class),uid,productClass,pageSize,pageSize*(currentPage-1));
		}
		
		return list;
	}

	@Override
	public List<Product> GetProductByName(String likeName,int uid) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where uid=? and  productName like ?";
		likeName="%"+likeName+"%";
		List<Product> products=qr.query(sql,new BeanListHandler<Product>(Product.class),uid,likeName);
		return products;
	}

	@Override
	public List<Product> OnSaleProductByID(int uid, int currentPage, String productClass) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		List<Product> list=null;
		if("全部".equals(productClass)){
			String sql="select * from onse where uid=? limit ? offset ?";
			//System.out.println("select * from onse where uid="+uid+" limit "+pageSize+" offset "+pageSize*(currentPage-1)+"");
		    list = qr.query(sql, new BeanListHandler<Product>(Product.class),uid,pageSize,pageSize*(currentPage-1));
		}else {
			String sql="select * from onse where uid=? and productClass=? limit ? offset ?";
		//	System.out.println("select * from onse where uid="+uid+" and productClass="+productClass+" limit "+pageSize+" offset "+pageSize*(currentPage-1)+"");

			list = qr.query(sql, new BeanListHandler<Product>(Product.class),uid,productClass,pageSize,pageSize*(currentPage-1));
		}
		
		return list;
	}

	@Override
	public List<Product> GetOnSaleByName(String likeName, int uid) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from onse where uid=? and  productName like ?";
		likeName="%"+likeName+"%";
		List<Product> products=qr.query(sql,new BeanListHandler<Product>(Product.class),uid,likeName);
		return products;
	}

	@Override
	public List<Object> FindOnSaleProductClass(int uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		//System.out.println("SELECT DISTINCT productClass FROM product where uid="+uid);
		String sql = "SELECT DISTINCT productClass FROM onse where uid=?";
		List<Object[]> ProductClass=qr.query(sql, new ArrayListHandler(),uid);
		
		List<Object> al= new ArrayList<Object>();
		for (Object[] object : ProductClass) {
			for (Object obj : object) {			
				al.add(obj);
			}
		}
		return al;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean DeleteProduct(int uid, int pid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		Connection conn=DataSourceUtils.getConnection();
		conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
		conn.setAutoCommit(false);
		//depot
		try {
			String sql1="delete from product where pid=?";
			String sql2="delete from onse where pid=?";
			qr.update(conn, sql1,pid);
			qr.update(conn, sql1,pid);
			
		} catch (Exception e) {
			conn.rollback();
			conn.setAutoCommit(true);
			conn.close();
			return false;
		}finally {
			
			conn.commit();
			conn.setAutoCommit(true);
			conn.close();
			Jedis jedis=JedisPoolUtils.getJedis();
			String count=jedis.hget("depot", String.valueOf(uid));
			int v=Integer.parseInt(count)-1;
			if(v<0)v=0;
			jedis.hset("depot", String.valueOf(uid), String.valueOf(v));
			jedis.close();
		}
		return true;
	}

	@Override
	public Long CheckOnsaleProductCount(int uid, String productClass) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT count(*) FROM onse WHERE uid=?";
		Long count=0l ;
		if("全部".equals(productClass)){
		 count =(Long) qr.query(sql, new ScalarHandler(), uid);
		}else {
			sql+=" and productClass=?";
			count =(Long) qr.query(sql, new ScalarHandler(), uid,productClass);
		}
		return count;
	}

	@Override
	public Product GetProductByPid(int uid, int pid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE uid=? and pid=?";
		Product product=qr.query(sql, new BeanHandler<Product>(Product.class),uid,pid);
		return product;
	}
/*
	int uid;
	String productName;
	double price;
	int productCount;
	String productImg;
	String productMessage;
	String productClass;
	int pid;
 */
	@Override
	public boolean alterProduct(Product product) throws SQLException {
		QueryRunner qr = new QueryRunner();
		Connection conn=DataSourceUtils.getConnection();
		conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
		conn.setAutoCommit(false);
		
		String sql1="delete from onse where pid=? and uid=?";
		String sql2="update  product set productName=?,price=?,productCount=?,";
		boolean flag=false;
		if(!product.getProductImg().isEmpty()) {
			sql2+="productImg=?,";
			flag=true;
		}
		sql2+="productMessage=?,productClass=? where pid=? and uid=?";
		
		try {
			qr.update(conn, sql1,product.getPid(),product.getUid());
			if(flag)qr.update(conn, sql2,product.getProductName(),product.getPrice(),product.getProductCount(),product.getProductImg(),product.getProductMessage(),product.getProductClass(),product.getPid(),product.getUid());
			else
				qr.update(conn, sql2,product.getProductName(),product.getPrice(),product.getProductCount(),product.getProductMessage(),product.getProductClass(),product.getPid(),product.getUid());
			
			
		} catch (Exception e) {
			conn.rollback();
			conn.setAutoCommit(true);
			conn.close();
			return false;
		}
			
		conn.commit();
		conn.setAutoCommit(true);
		conn.close();
			
	
		return true;
		
	}

}
