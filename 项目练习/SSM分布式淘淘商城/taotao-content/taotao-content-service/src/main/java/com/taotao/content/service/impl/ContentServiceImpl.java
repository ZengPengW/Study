package com.taotao.content.service.impl;

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
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public EasyUIDataGridResult<TbContent> getContentList(Long categoryId, Integer page, Integer rows) {
		if (page == null)
			page = 1;
		if (rows == null)
			rows = 30;
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		if (categoryId != null && categoryId != 0) {
			Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(categoryId);
		}
		List<TbContent> list = contentMapper.selectByExample(example);

		EasyUIDataGridResult<TbContent> dataGridResult = new EasyUIDataGridResult<TbContent>();
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		dataGridResult.setTotal(pageInfo.getTotal());
		dataGridResult.setRows(list);

		return dataGridResult;
	}

	@Override
	public TaotaoResult saveContent(TbContent tContent) {
		// 1.注入mapper

		// 2.补全其他的属性
		tContent.setCreated(new Date());
		tContent.setUpdated(tContent.getCreated());
		// 3.插入内容表中
		contentMapper.insertSelective(tContent);
		return TaotaoResult.ok();

	}

}
