package com.zpp.service;

import java.sql.SQLException;

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

}
