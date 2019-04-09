package com.zpp.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

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
			String sql="insert into `order`(uid,`time`,gid,username,phone,shopMessage,isteke,equipment) values(?,?,?,?,?,?,?,?)";
			
			int status=qr.update(sql,order.getUid(),order.getTime(),order.getGid(),order.getUsername(),order.getPhone(),order.getShopMessage(),0,order.getEquipment());
			
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

}
