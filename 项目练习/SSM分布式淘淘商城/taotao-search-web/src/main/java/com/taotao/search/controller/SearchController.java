package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchService;

/**
 * 商品搜索Controller
 * @author zp
 *
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@Value("${ITEM_ROWS}")
	private Integer ITEM_ROWS;
	
	@RequestMapping("/search")
	public String search(@RequestParam(defaultValue="1")Integer page,@RequestParam(name="q") String queryString,Model model) throws Exception {
		queryString=new String(queryString.getBytes("iso-8859-1"),"utf-8");
		//查询商品
		SearchResult result = searchService.search(queryString, page, ITEM_ROWS);
		
		model.addAttribute("query",queryString);
		model.addAttribute("totalPages", result.getPageCount());
		model.addAttribute("itemList", result.getItemList());
		model.addAttribute("page", page);
		return "search";
	}
}
