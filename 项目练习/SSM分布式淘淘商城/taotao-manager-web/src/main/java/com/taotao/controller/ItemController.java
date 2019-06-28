package com.taotao.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	  * 分页查询商品表表
	 */
	@RequestMapping(value="/item/list",method=RequestMethod.GET)
	@ResponseBody
	public EasyUIDataGridResult<TbItem> getItemList(Integer page,Integer rows){	
		EasyUIDataGridResult<TbItem> itemList = itemService.getItemList(page, rows);
		return itemList;
	}
	
	/**
	 *    新增商品
	 */
	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult itemSave(TbItem item ,String desc,String itemParams) {
		
		return itemService.addItem(item, desc,itemParams);
	}
	
	/**
	 *    更新商品
	 */
	@RequestMapping("/rest/item/update")
	@ResponseBody
	public TaotaoResult itemUpdate(TbItem item ,String desc,Long itemParamId,String itemParams) {		
		
		return itemService.updateItem(item, desc, itemParamId, itemParams);
	}
	/**
	 *    删除商品
	 */
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public TaotaoResult itemDelete(String ids) {		
		return itemService.deleteItem(ids);
	}
	/**
	 *    下架商品
	 */
	@RequestMapping("/rest/item/instock")
	@ResponseBody
	public TaotaoResult itemInstock(String ids) {		
		return itemService.instockItem(ids);
	}
	
	/**
	 *    上架商品
	 */
	@RequestMapping("/rest/item/reshelf")
	@ResponseBody
	public TaotaoResult itemReshelf(String ids) {		
		return itemService.reshelfItem(ids);
	}
}
