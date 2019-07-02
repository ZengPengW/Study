package com.taotao.search.mapper;
/**
 * 定义mapper 关联查询三张表搜索时的数据
 * @author zp
 *
 */

import java.util.List;

import com.taotao.common.pojo.SearchItem;

public interface SearchItemMapper {
	/**
	 * 查询所有商品数据
	 * @return
	 */
	public List<SearchItem> getSearchItemList();
	
}
