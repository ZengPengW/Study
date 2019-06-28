package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamAndCatName;
import com.taotao.service.ItemParamService;

/**
 *  商品参数模版管理
 * @author zp
 *
 */
@Controller
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
	
	/**
	 * 分页查询商品规格模版
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EasyUIDataGridResult<TbItemParamAndCatName> getItemParamList(Integer page,Integer rows){
		
		return itemParamService.getItemParamList(page, rows);
		
	}
	/**
	 *  通过cid查询商品规格模版
	 * @param item_cat_id
	 * @return
	 */
	@RequestMapping("/item/param/query/itemcatid/{item_cat_id}")
	@ResponseBody
	public TaotaoResult getItemParamByCatId(@PathVariable Long item_cat_id) {
		return itemParamService.getItemParamByCid(item_cat_id);		
	}
	
	/**
	 * 保存商品规格
	 * @param item_cat_id
	 * @param itemParam
	 * @return
	 */
	@RequestMapping("/item/param/save/{item_cat_id}")
	@ResponseBody
	public TaotaoResult saveItemParam(@PathVariable Long item_cat_id,TbItemParam itemParam) {
		itemParam.setItemCatId(item_cat_id);
//		System.out.println(itemParam.getParamData()==null);
//		System.out.println(itemParam.getParamData().replace("[", "").replace("]", "").trim().length()==0);
		if(itemParam.getParamData()==null||itemParam.getParamData().replace("[", "").replace("]", "").trim().length()==0) {
			return new TaotaoResult(500, "", null);
		}
		return itemParamService.insertItemParam(itemParam);
	}
	
	/**
	 * 删除商品规格
	 * @param ids
	 * @return
	 */
	@RequestMapping("/item/param/delete")
	@ResponseBody
	public TaotaoResult deleteItemParam(String ids) {
		return itemParamService.deleteItemParamByIds(ids);
	}
}
