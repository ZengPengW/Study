package com.zpp.service;

import java.sql.SQLException;

import com.zpp.domain.Order;

public interface PayService {
	public boolean updateFinanceOnPay(int uid,double payMoney)throws SQLException;

	public boolean addOrderOnPay(Order order)throws SQLException; 
	
	public boolean isExistenceGid(int uid,String gid)throws SQLException;
}
