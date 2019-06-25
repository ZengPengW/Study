package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;

	/**
	 * 根据parentId查询子节点
	 */
	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		if (parentId == null)
			parentId = 0l;
		// 查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		// 转换成EasyUITreeNode
		List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();

		for (TbItemCat tbItemCat : list) {
			resultList.add(new EasyUITreeNode(tbItemCat.getId(), tbItemCat.getName(),
					tbItemCat.getIsParent() ? "closed" : "open"));
		}
		return resultList;
	}

}
