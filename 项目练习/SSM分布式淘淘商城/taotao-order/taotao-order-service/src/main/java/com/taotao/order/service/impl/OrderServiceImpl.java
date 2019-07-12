package com.taotao.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.jedis.JedisClient;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

@Service
@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {

	
	@Autowired
	private TbOrderMapper orderMapper;
	
	@Autowired 
	private TbOrderItemMapper orderItemMapper;
	
	@Autowired 
	private TbOrderShippingMapper orderShippingMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ORDER_ID_GEN_KEY}")
	private String ORDER_ID_GEN_KEY;
	
	@Value("${ORDER_ID_BEGIN_VALUE}")
	private String ORDER_ID_BEGIN_VALUE;
	
	@Value("${ORDER_ITEM_ID_GEN_KEY}")
	private String ORDER_ITEM_ID_GEN_KEY;
	
	@Override
	public TaotaoResult createOrder(OrderInfo orderInfo) {
		//生成订单号，用redis里的incr 生成
		//不存在就设置初始值
		if(!jedisClient.exists(ORDER_ID_GEN_KEY)){
			jedisClient.set(ORDER_ID_GEN_KEY, ORDER_ID_BEGIN_VALUE);		
		}
		String orderId=jedisClient.incr(ORDER_ID_GEN_KEY).toString();
		
		//向订单表插入数据
		orderInfo.setOrderId(orderId);
		//免邮费
		orderInfo.setPostFee("0");
		//状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关
		orderInfo.setStatus(1);
		//创建时间
		orderInfo.setCreateTime(new Date());
		orderInfo.setUpdateTime(orderInfo.getCreateTime());
		
		//向订单表插入数据
		orderMapper.insert(orderInfo);
		//向订单明细表插入数据
		List<TbOrderItem> orderItems=orderInfo.getOrderItems();
		for (TbOrderItem tbOrderItem : orderItems) {
			//获得主键id
			String oid = jedisClient.incr(ORDER_ITEM_ID_GEN_KEY).toString();
			tbOrderItem.setOrderId(orderId);
			tbOrderItem.setId(oid);
			
			//插入数据
			orderItemMapper.insert(tbOrderItem);
			
		}
		
		//插入订单物流表
		TbOrderShipping orderShipping=orderInfo.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(new Date());
		orderShipping.setUpdated(orderShipping.getCreated());
		orderShippingMapper.insert(orderShipping);
		
		return TaotaoResult.ok(orderId);
	}

}


















