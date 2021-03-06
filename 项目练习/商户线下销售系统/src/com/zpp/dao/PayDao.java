package com.zpp.dao;

import java.sql.SQLException;
import java.util.List;

import com.zpp.domain.Money;
import com.zpp.domain.Order;

public interface PayDao {

	public boolean updateFinanceOnPay(int uid,double payMoney)throws SQLException;
	
	public boolean addOrderOnPay(Order order)throws SQLException; 
	
	public boolean isExistenceGid(int uid,String gid)throws SQLException;
	
	public List<Order> GetOrderByEquipment(int uid,String equipment)throws SQLException;
	
	public Order GetOrderByOid(int uid,String equipment,int oid)throws SQLException;
	
	public boolean tekeOrder(int uid,String equipment,int oid)throws SQLException;
	
	public List<Order> getAllOrderByUid(int uid,int currentPage,String orderClass)throws SQLException;
	
	public Long getAllOrderCountByUid(int uid,String orderClass)throws SQLException;
	
	public boolean orderOptionByUid(int uid,int oid,int isOption)throws SQLException;
	public Order getOrderByOid(int uid,int oid)throws SQLException;
	
	public List<Order> OrderSearchLike(int uid, String info)throws SQLException;
	
	public List<Order> getTeke(int uid,int isteke,int statu)throws SQLException;
	
	public boolean tekeMoney(int uid,int primary)throws SQLException;
	
	public boolean addMoneyTab(Money money)throws SQLException;
	
	public List<Money> getMonerList (int uid,int page)throws SQLException;
	public Long getMonerCount (int uid)throws SQLException;
	public boolean IsExistOrder(int uid,String equipment,String time,String gid,String phone,String username)throws SQLException;
}
