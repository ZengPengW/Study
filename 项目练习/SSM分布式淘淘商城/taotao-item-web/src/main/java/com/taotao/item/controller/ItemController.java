package com.taotao.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemDescService;
import com.taotao.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemDescService itemDescService;
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	public String getItem(@PathVariable Long itemId,Model model){
		TbItemDesc itemDesc = itemDescService.geTbItemDescByID(itemId);
		TbItem tbItem = itemService.getTbItemById(itemId);
		Item item=new Item(tbItem);
		
		//传递数据
		model.addAttribute("item", item);
		model.addAttribute("itemDesc",itemDesc);
		
		return "item";
	}
	
	@RequestMapping(value="/rest/item/param/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId){
		String param= itemService.getItemParam(itemId);
		
		return param;
	}
}















