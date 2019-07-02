package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.SearchService;

/**
 * 一键导入商品数据到索引库
 * @author zp
 *
 */
@Controller
public class ImportAllItems {

	@Autowired
	private SearchService searchService;
	
	/**
	 * 导入所有商品数据到索引库中
	 * @return
	 */
	@RequestMapping("/index/importAll")
	@ResponseBody
	public TaotaoResult importAll() {
		try {
			return searchService.importAllSearchItems();
		} catch (Exception e) {
			TaotaoResult taotaoResult=new TaotaoResult();
			taotaoResult.setStatus(500);
			e.printStackTrace();
			return taotaoResult;
			
		}
	}
}








