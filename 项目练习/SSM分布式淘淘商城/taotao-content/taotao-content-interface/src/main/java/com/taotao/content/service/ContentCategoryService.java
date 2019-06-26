package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {
	public List<EasyUITreeNode> getContentCategoryList(Long parentId);

	public TaotaoResult createContentCategory(Long parentId, String name);
}
