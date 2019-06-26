package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamAndCatName;
import com.taotao.service.ItemParamService;

/**
 *  商品参数类
 * @author zp
 *
 */
@Controller
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EasyUIDataGridResult<TbItemParamAndCatName> getItemParamList(Integer page,Integer rows){
		System.out.println();
		return itemParamService.getItemParamList(page, rows);
		
	}
}
