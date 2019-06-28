package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamAndCatName;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
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

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public TaotaoResult getItemParamByCid(Long cid) {
		TbItemParamExample example=new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		itemParam.setCreated(new Date());
		itemParam.setUpdated(itemParam.getCreated());
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteItemParamByIds(String ids) {
		String[] idss = ids.split(",");

		List<Long> id = new ArrayList<Long>(idss.length);
		for (int i = 0; i < idss.length; i++) {
			id.add(Long.valueOf(idss[i]));
		}
		
		TbItemParamExample example =new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(id);
		itemParamMapper.deleteByExample(example );
		return TaotaoResult.ok();
	}

}
