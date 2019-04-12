package com.zpp.service;

import java.sql.SQLException;
import java.util.List;

import com.zpp.dao.PayDao;
import com.zpp.dao.impl.PayDaoImpl;
import com.zpp.domain.Order;

public class PayServiceImpl implements PayService{

	PayDao dao=new PayDaoImpl();
	@Override
	public boolean updateFinanceOnPay(int uid, double payMoney) throws SQLException {
		return dao.updateFinanceOnPay(uid, payMoney);
	}
	@Override
	public boolean addOrderOnPay(Order order) throws SQLException {
		return dao.addOrderOnPay(order);
	}
	@Override
	public boolean isExistenceGid(int uid, String gid) throws SQLException {
		return dao.isExistenceGid(uid, gid);
	}
	@Override
	public List<Order> GetOrderByEquipment(int uid, String equipment) throws SQLException {
		return dao.GetOrderByEquipment(uid, equipment);
	}
	@Override
	public Order GetOrderByOid(int uid, String equipment, int oid) throws SQLException {
		return dao.GetOrderByOid(uid, equipment, oid);
	}
	@Override
	public boolean tekeOrder(int uid, String equipment, int oid) throws SQLException {
		return dao.tekeOrder(uid, equipment, oid);
	}
	@Override
	public List<Order> getAllOrderByUid(int uid,int currentPage,String orderClass) throws SQLException {
		return dao.getAllOrderByUid(uid, currentPage, orderClass);
	}
	@Override
	public Long getAllOrderCountByUid(int uid,  String orderClass) throws SQLException {
		return dao.getAllOrderCountByUid(uid, orderClass);
	}
	@Override
	public boolean orderOptionByUid(int uid, int oid, int isOption) throws SQLException {
		return dao.orderOptionByUid(uid, oid, isOption);
	}
	@Override
	public Order getOrderByOid(int uid, int oid) throws SQLException {
		return dao.getOrderByOid(uid, oid);
	}
	@Override
	public List<Order> OrderSearchLike(int uid, String info) throws SQLException {
		return dao.OrderSearchLike(uid, info);
	}
	@Override
	public List<Order> getTeke(int uid, int isteke, int statu) throws SQLException {
		return dao.getTeke(uid, isteke, statu);
	}
	
}
