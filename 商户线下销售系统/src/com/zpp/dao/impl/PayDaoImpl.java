package com.zpp.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zpp.dao.PayDao;
import com.zpp.domain.Finance;
import com.zpp.domain.Order;
import com.zpp.utils.DataSourceUtils;

public class PayDaoImpl implements PayDao {

	//更新金额表 采用乐观锁
	@Override
	public boolean updateFinanceOnPay(int uid, double payMoney) throws SQLException {
		QueryRunner qr=new QueryRunner();
		Connection conn=DataSourceUtils.getConnection();
		conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		conn.setAutoCommit(true);
		
		String sql;
		try {
			Finance finance=null;
			while (true) {
				sql="select * from finance where uid=?";
				finance=qr.query(conn,sql,new BeanHandler<Finance>(Finance.class),uid);
				int version=finance.getVersion();
				double total=Double.valueOf(String.format("%.2f", payMoney+finance.getTotal()));
				double balance=Double.valueOf(String.format("%.2f", payMoney+finance.getBalance()));
				sql="update finance set soldout=?,balance=?,total=?,version=? where uid=? and version=?";
				int status=qr.update(conn, sql,finance.getSoldout()+1,balance,total,version+1,uid,version);
				//System.out.println(status);
				if(status==1) {
					break;
				}
				Thread.sleep(200);
			}
		
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}finally {
			
			conn.close();
		}
		
		return true;
	}

	@Override
	public boolean addOrderOnPay(Order order) throws SQLException {
		try {
			QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
			String sql="insert into `order`(uid,`time`,gid,username,phone,shopMessage,isteke,equipment,money) values(?,?,?,?,?,?,?,?,?)";
			
			int status=qr.update(sql,order.getUid(),order.getTime(),order.getGid(),order.getUsername(),order.getPhone(),order.getShopMessage(),0,order.getEquipment(),order.getMoney());
			
			if(status==1)return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean isExistenceGid(int uid, String gid) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
	//	System.out.println("select * from order where uid="+uid+" and gid="+gid+" and isteke="+0);
		String sql="select * from `order` where uid=? and gid=? and isteke=?";
		Order order=qr.query(sql, new BeanHandler<Order>(Order.class),uid,gid,0);
		
		if(order!=null)return true;
		return false;
	}

	@Override
	public List<Order> GetOrderByEquipment(int uid, String equipment) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from `order` where uid=? and equipment=? ORDER BY `time` DESC";
		List<Order> order=qr.query(sql, new BeanListHandler<Order>(Order.class),uid,equipment);
		return order;
		
	}

	@Override
	public Order GetOrderByOid(int uid, String equipment, int oid) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from `order` where uid=? and equipment=? and oid=?";
		Order order=qr.query(sql, new BeanHandler<Order>(Order.class),uid,equipment,oid);
		return order;
	}

	@Override
	public boolean tekeOrder(int uid, String equipment, int oid) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update `order` set isteke=? ,statu=? where uid=? and equipment=? and oid=?";
		int status=qr.update(sql,1,2,uid,equipment,oid);
		if(status==1)return true;
		else return false;
	}

	public static int pageSize=12;
	@Override
	public List<Order> getAllOrderByUid(int uid,int currentPage,String orderClass) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		List<Order> allOrder=null;
		if(orderClass.equals("全部")) {
			String sql="select * from `order` where uid=? ORDER BY `time` DESC LIMIT ? OFFSET ? ";
			allOrder=qr.query(sql, new BeanListHandler<Order>(Order.class),uid,pageSize,pageSize*(currentPage-1));
		}else if (orderClass.equals("已取货")) {
			String sql="select * from `order` where uid=? and isteke=?  ORDER BY `time` DESC LIMIT ? OFFSET ?";
			allOrder=qr.query(sql, new BeanListHandler<Order>(Order.class),uid,1,pageSize,pageSize*(currentPage-1));
		}else if (orderClass.equals("未取货")) {
			String sql="select * from `order` where uid=? and isteke=? ORDER BY `time` DESC LIMIT ? OFFSET ?";
			allOrder=qr.query(sql, new BeanListHandler<Order>(Order.class),uid,0,pageSize,pageSize*(currentPage-1));
		}
		
		return allOrder;
	}

	@Override
	public Long getAllOrderCountByUid(int uid, int currentPage, String orderClass) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		Long allOrder = 0l;
		if(orderClass.equals("全部")) {
			String sql="select count(*) from `order` where uid=?  ";
			allOrder=qr.query(sql, new ScalarHandler<Long>(),uid);
		}else if (orderClass.equals("已取货")) {
			String sql="select count(*) from `order` where uid=? and isteke=?  ";
			allOrder=qr.query(sql, new ScalarHandler<Long>(),uid,1);
		}else if (orderClass.equals("未取货")) {
			String sql="select count(*) from `order` where uid=? and isteke=? ";
			allOrder=qr.query(sql, new ScalarHandler<Long>(),uid,0);
		}
		return allOrder;
	}

	@Override
	public boolean orderOptionByUid(int uid, int oid, int isOption) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select statu from `order` where uid=? and oid=?";
		Integer i=qr.query(sql, new ScalarHandler<Integer>(),uid,oid);
		if(isOption==1&&i<1){
			//确认操作	
			sql="update  `order`  set statu=?  where uid=? and oid=?";
			int v=qr.update(sql,1,uid,oid);
			if(v==1)return true;
		}else if(isOption==2&&i<2){
			//备货操作
			 sql="update  `order`  set statu=?  where uid=? and oid=?";
			 int v=qr.update(sql,2,uid,oid);
			 if(v==1)return true;
		}else{
			//取货操作
			 sql="update  `order`  set isteke=?,statu=?  where uid=? and oid=?";
			 int v=qr.update(sql,1,2,uid,oid);
			 if(v==1)return true;
		}
		
		return false;
	}

	@Override
	public Order getOrderByOid(int uid, int oid) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from `order` where uid=?  and oid=?";
		Order order=qr.query(sql, new BeanHandler<Order>(Order.class),uid,oid);
		return order;
	}

	@Override
	public List<Order> OrderSearchLike(int uid,String info ) throws SQLException {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from `order` where uid=?  and (gid like ? or username like ? or phone like ?)";
		info="%"+info+"%";
		List<Order> list=qr.query(sql, new BeanListHandler<Order>(Order.class),uid,info,info,info);
		return list;
	}

}
