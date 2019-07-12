package com.taotao.order.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.OrderInfo;

public interface OrderService {
	/**
	 * 创建订单参数
	 * @param orderInfo
	 * @return
	 */
	public TaotaoResult createOrder(OrderInfo orderInfo);
}
