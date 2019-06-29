package com.taotao.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.Ad1Node;

/**
 * 页面展示
 * 
 * @author zp
 *
 */
@Controller
public class PageController {

	@Autowired
	private ContentService contentService;

	@Value("${AD1_CATEGORY_ID}")
	private Long categoryId;

	@Value("${AD1_HEIGHT_B}")
	private String AD1_HEIGHT_B;
	
	@Value("${AD1_HEIGHT}")
	private String AD1_HEIGHT;
	
	@Value("${AD1_WIDTH}")
	private String AD1_WIDTH;
	
	@Value("${AD1_WIDTH_B}")
	private String AD1_WIDTH_B;
	
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		// 通过内容分类id查询内容列表
		List<TbContent> contentList = contentService.getContentListByCatId(categoryId);
		// 转成自定义pojo
		List<Ad1Node> ad1Nodes = new ArrayList<Ad1Node>();
		for (TbContent tbContent : contentList) {
			ad1Nodes.add(new Ad1Node(tbContent.getPic2(), AD1_HEIGHT, tbContent.getTitle(), AD1_WIDTH, tbContent.getPic(), AD1_WIDTH_B,
					tbContent.getUrl(), AD1_HEIGHT_B));
		}		
		model.addAttribute("ad1", JsonUtils.objectToJson(ad1Nodes));
		return "index";
	}
}
