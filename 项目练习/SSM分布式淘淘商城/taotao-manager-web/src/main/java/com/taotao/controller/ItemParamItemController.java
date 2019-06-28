package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamItemService;

/**
 * 商品规格参数Controller
 * @author zp
 *
 */
@Controller
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;
	
	/**
	 * 获取商品规格参数
	 */
	@RequestMapping("/rest/item/param/item/query/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParamItem(@PathVariable Long itemId) {
		return itemParamItemService.getItemParamItemByItemId(itemId);
	}
	
}
