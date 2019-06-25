package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
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
	 *   新增商品
	 */
	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult itemSave(TbItem item ,String desc) {
		return itemService.addItem(item, desc);
	}
}
