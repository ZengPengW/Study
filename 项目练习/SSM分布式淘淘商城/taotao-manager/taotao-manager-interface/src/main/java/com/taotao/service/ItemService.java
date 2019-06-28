package com.taotao.service;
/**
 * 商品接口
 */

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	
	public TbItem getTbItemById(Long itemId);
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
	public TaotaoResult addItem(TbItem item,String desc,String itemParams);
	/**
	 * 修改商品
	 * @return
	 */
	public TaotaoResult updateItem(TbItem item, String desc,Long itemParamId,String itemParams);
	/**
	 * 删除商品
	 * @param ids
	 * @return
	 */
	public TaotaoResult deleteItem(String ids);
	/**
	 * 下架商品
	 * @param ids
	 * @return
	 */
	public TaotaoResult instockItem(String ids);
	/**
	 * 上架商品
	 * @param ids
	 * @return
	 */
	public TaotaoResult reshelfItem(String ids);
	
}
