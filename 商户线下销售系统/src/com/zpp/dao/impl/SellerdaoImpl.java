package com.zpp.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.zpp.dao.Sellerdao;
import com.zpp.domain.Finance;
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
			
			if (us != null) {
				String sql2="insert into finance(uid)values (?)";
				qr.update(sql2, us.getId());
				return true;
			}
				

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
	public User isExistUser(String name, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM users WHERE (email=? or username=?) and password=?";
		User user = qr.query(sql, new BeanHandler<User>(User.class), name, name, password);
		return user;
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
			String sql1="delete from product where pid=? and uid=?";
			String sql2="delete from onse where pid=? and uid=?";
			qr.update(conn, sql1,pid,uid);
			qr.update(conn, sql2,pid,uid);
			
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
		if(product.getProductImg()!=null&&!product.getProductImg().isEmpty()) {
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
		Jedis jedis=JedisPoolUtils.getJedis();
		Long count =this.CheckOnsaleProductCount(product.getUid(),"全部");
		
		jedis.hset("onsale", ""+product.getUid(), String.valueOf(count));
		jedis.close();
		conn.close();
			
	
		return true;
		
	}

	@Override
	public boolean isExisOnSale(int uid, int pid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from onse where uid=? and pid=?";
		Product product =qr.query(sql, new BeanHandler<Product>(Product.class),uid,pid);
		if(product==null) {
			return false;
		}else {
			return true;
		}
		
	}

	@Override
	public boolean publishProduct(int uid, int pid) throws SQLException {
		QueryRunner qr = new QueryRunner();
		Connection conn=DataSourceUtils.getConnection();
		conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
		conn.setAutoCommit(false);
		try {
			String sql1="select * from product where uid=? and pid=?";
			String sql2="insert into onse(uid,productName,price,productCount,productImg,productMessage,productClass,pid,version) values(?,?,?,?,?,?,?,?,?)";
			
			Product product =qr.query(conn, sql1, new BeanHandler<Product>(Product.class),uid,pid);
			int is=qr.update(conn, sql2,product.getUid(),product.getProductName(),product.getPrice(),product.getProductCount(),product.getProductImg(),product.getProductMessage(),product.getProductClass(),product.getPid(),1);
			if(is!=1)throw new RuntimeException("上架失败");
		} catch (Exception e) {
			conn.rollback();
			conn.setAutoCommit(true);
			conn.close();
			return false;
		}
		
		conn.commit();
		conn.setAutoCommit(true);
		conn.close();
		
		Jedis jedis=JedisPoolUtils.getJedis();
		int count=Integer.parseInt(jedis.hget("onsale", ""+uid))+1;
		jedis.hset("onsale", ""+uid, ""+count);
		jedis.close();
		return true;
	}

	@Override
	public boolean onSaleDelete(int uid, int pid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="delete from onse where uid=? and pid=?";
		int is=qr.update(sql,uid,pid);
		if(is>=1) {
			Jedis jedis=JedisPoolUtils.getJedis();
			int count=Integer.parseInt(jedis.hget("onsale", ""+uid))-1;
			if(count<0)count=0;
			jedis.hset("onsale", ""+uid, ""+count);
			jedis.close();
			return true;
		}
		else return false;
	}

	@Override
	public ArrayList<Object> GetOnSalePid(int uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select pid from onse where uid=?";
		List<Object[]> obj=qr.query(sql, new ArrayListHandler(),uid);
		
		ArrayList<Object> al=new ArrayList<Object>();
		for (Object[] i : obj) {
			al.add(i[0]);
		}
		return al;
	}

	@Override
	public Finance GetFinanceByUid(int uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from finance where uid=?";
		Finance finance=qr.query(sql,new BeanHandler<Finance>(Finance.class),uid);
		return finance;
	}

	@Override
	public boolean alterUserNameByUid(int uid,String username) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		//System.out.println("update users set username="+username+" where id="+uid+"");
		try {
			String sql="update users set username=? where id=?";
			qr.update(sql, username,uid);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public boolean alterUserShopNameByUid(int uid, String shopname) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		//System.out.println("update users set shopname="+shopname+" where id="+uid+"");
		try {
			String sql="update users set shopname=? where id=?";
			qr.update(sql, shopname,uid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean alterFinancePayByUid(int uid, String pay) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		//System.out.println("update finance set pay="+pay+" where uid="+uid+"");
		if(pay!=null&&!pay.isEmpty()) {
			try {
				
				String sql="update finance set pay=? where uid=?";
				qr.update(sql, pay,uid);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String getUserSid(int uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select sid from users where id=?";
		String sid=qr.query(sql, new ScalarHandler<String>(),uid);
		return sid;
	}

	@Override
	public User getUserById(int uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from users where id=?";
		User user=qr.query(sql, new BeanHandler<User>(User.class),uid);
		return user;
	}

	

}
