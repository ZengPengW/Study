package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamAndCatName;
import com.taotao.service.ItemParamService;
@Service
@Transactional
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public EasyUIDataGridResult<TbItemParamAndCatName> getItemParamList(Integer page, Integer rows) {
		if (page==null)page=1;
		if(rows==null)rows=30;
		PageHelper.startPage(page, rows);
		List<TbItemParamAndCatName> list=itemParamMapper.selectWithCatName();
		
		EasyUIDataGridResult<TbItemParamAndCatName> dataGridResult=new EasyUIDataGridResult<TbItemParamAndCatName>();
		PageInfo<TbItemParamAndCatName> pageInfo=new PageInfo<TbItemParamAndCatName>(list);
		dataGridResult.setTotal(pageInfo.getTotal());
		dataGridResult.setRows(list);
		
		return dataGridResult;
	}

}
