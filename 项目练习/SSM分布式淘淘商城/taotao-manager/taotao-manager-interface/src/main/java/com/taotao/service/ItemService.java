package com.taotao.service;
/**
 * 商品接口
 */

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	
	public TbItem geTbItemById(Long itemId);
	/**
	 * 根据当前页码和行数,进行分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public EasyUIDataGridResult<TbItem> getItemList(Integer page,Integer rows);
	
	/**
	 *  添加商品
	 * @param item
	 * @param desc
	 * @return 返回TaotaoResult
	 */
	public TaotaoResult addItem(TbItem item,String desc);
}
