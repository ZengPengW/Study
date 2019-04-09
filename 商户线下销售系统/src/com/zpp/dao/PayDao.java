package com.zpp.dao;

import java.sql.SQLException;

import com.zpp.domain.Order;

public interface PayDao {

	public boolean updateFinanceOnPay(int uid,double payMoney)throws SQLException;
	
	public boolean addOrderOnPay(Order order)throws SQLException; 
	
	public boolean isExistenceGid(int uid,String gid)throws SQLException;
}
