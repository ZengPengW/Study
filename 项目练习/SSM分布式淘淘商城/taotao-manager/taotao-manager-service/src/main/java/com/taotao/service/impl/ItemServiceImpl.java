package com.taotao.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
@Service
public class ItemServiceImpl implements ItemService,Serializable {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired 
	private TbItemDescMapper itemDescMapper;
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public EasyUIDataGridResult<TbItem> getItemList(Integer page, Integer rows) {
		if (page==null)page=1;
		if(rows==null)rows=30;
		PageHelper.startPage(page, rows);
		TbItemExample example=new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(list);
		EasyUIDataGridResult<TbItem> dataGridResult=new EasyUIDataGridResult<>();
		dataGridResult.setRows(list);
		dataGridResult.setTotal(pageInfo.getTotal());
		return dataGridResult;
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public TbItem getTbItemById(Long itemId) {		
		return itemMapper.selectByPrimaryKey(itemId);
	}

	
	@Override
	public TaotaoResult addItem(TbItem item, String desc) {
		//生成商品id
		long itemId=IDUtils.getItemId();
		
		//补全item的属性
		item.setId(itemId);
		item.setStatus((byte) 1);//商品状态 1 正常 2下架 3 删除
		Date date=new Date();
		item.setCreated(date);
		item.setUpdated(date);
		
		//向商品表中插入数据
		itemMapper.insert(item);
		//创建一个商品描述表对应的pojo
		TbItemDesc itemDesc =new TbItemDesc();
		//补全pojo的属性
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		//向商品描述表插入数据
		itemDescMapper.insert(itemDesc);
		//返回结果
		
		return TaotaoResult.ok();
	}
	
	

}
