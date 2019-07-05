package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.service.ItemDescService;

@Service
@Transactional
public class ItemDescServiceImpl implements ItemDescService {

	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public TbItemDesc geTbItemDescByID(Long ItemId) {
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(ItemId);
		
		return itemDesc;
	}

}
