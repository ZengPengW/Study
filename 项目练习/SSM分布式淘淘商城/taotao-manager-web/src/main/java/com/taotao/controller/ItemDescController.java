package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *  商品信息描述类
 * @author zp
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemDescService;
@Controller
public class ItemDescController {

	@Autowired
	private ItemDescService descService;
	
	@RequestMapping("/rest/item/query/item/desc/{ItemDescId}")
	@ResponseBody
	public TbItemDesc geTbItemDesc(@PathVariable Long ItemDescId) {
		return descService.geTbItemDescByID(ItemDescId);
		
	}
}
