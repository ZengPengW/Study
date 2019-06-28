package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;

@Service
@Transactional
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);

		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);

		List<EasyUITreeNode> nodes = new ArrayList<EasyUITreeNode>();
		for (TbContentCategory tbContentCategory : list) {
			nodes.add(new EasyUITreeNode(tbContentCategory.getId(), tbContentCategory.getName(),
					tbContentCategory.getIsParent() ? "closed" : "open"));

		}
		return nodes;
	}

	@Override
	public TaotaoResult createContentCategory(Long parentId, String name) {
		// 1.构建对象 补全其他的属性
		TbContentCategory category = new TbContentCategory();
		category.setCreated(new Date());
		category.setIsParent(false);// 新增的节点都是叶子节点
		category.setName(name);
		category.setParentId(parentId);
		category.setSortOrder(1);
		category.setStatus(1);
		category.setUpdated(category.getCreated());
		// 2.插入contentcategory表数据
		contentCategoryMapper.insertSelective(category);
		// 3.返回taotaoresult 包含内容分类的id 需要主键返回

		// 判断如果要添加的节点的父节点本身叶子节点 需要更新其为父节点
		TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (parent.getIsParent() == false) {// 原本就是叶子节点
			parent.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKeySelective(parent);// 更新节点的is_parent属性为true
		}

		return TaotaoResult.ok(category);
	}

	@Override
	public boolean deleteContentCategoryById(Long id) {
		TbContentCategory category=contentCategoryMapper.selectByPrimaryKey(id);
		if(category.getIsParent()) {
			return false;
		}else {
			contentCategoryMapper.deleteByPrimaryKey(id);
			TbContentCategoryExample example=new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(category.getParentId());
			List<TbContentCategory> childs = contentCategoryMapper.selectByExample(example);
			if(childs==null||childs.size()==0) {
				TbContentCategory parent=contentCategoryMapper.selectByPrimaryKey(category.getParentId());
				parent.setIsParent(false);
				contentCategoryMapper.updateByPrimaryKey(parent);
			}
			return true;
		}
		
		
	}

	@Override
	public void updateContentCategoryById(Long id, String name) {
		
		TbContentCategory tbContentCategory=new TbContentCategory();
		tbContentCategory.setId(id);
		tbContentCategory.setName(name);
		contentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
		
	}

}
