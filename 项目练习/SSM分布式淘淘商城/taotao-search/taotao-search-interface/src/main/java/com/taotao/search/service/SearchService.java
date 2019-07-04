package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;

public interface SearchService {
	/**
	 * 导入所有商品数据到索引库中
	 * @return
	 */
	public TaotaoResult importAllSearchItems()throws Exception ;
	
	/**
	 *根据 搜索条件搜索结果
	 * @param queryString 查询的主要条件
	 * @param page 查询当前页码
	 * @param rows 每页显示的条数
	 * @return
	 * @throws Exception
	 */
	public SearchResult search(String queryString,Integer page,Integer rows)throws Exception;
	/**
	 * 根据id更新索引
	 * @param itemIds
	 * @return
	 * @throws Exception
	 */
	public TaotaoResult updateItemByIds(String... itemIds) throws Exception;
	
	/**
	 * 根据id删除索引
	 */
	public TaotaoResult deleteItemByIds(String... itemIds) throws Exception;
}
