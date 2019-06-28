package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ItemParamItemService;

@Service
@Transactional
public class ItemParamItemServiceImpl implements ItemParamItemService {

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public TaotaoResult getItemParamItemByItemId(Long itemId) {
		TbItemParamItemExample example =new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

}
