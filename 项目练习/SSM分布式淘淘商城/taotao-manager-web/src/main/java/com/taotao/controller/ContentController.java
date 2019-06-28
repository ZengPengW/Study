package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;


/**
 * 内容管理
 * @author zp
 *
 */
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	/**
	 * 查询内容数据
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult<TbContent> getContentList(Long categoryId,Integer page,Integer rows) {
		return contentService.getContentList(categoryId,page, rows);
	}
	
	/**
	 * 添加内容
	 * @param tContent
	 * @return
	 */
	@RequestMapping(value="/content/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveContent(TbContent tContent){
		
		return contentService.saveContent(tContent);
	}
	/**
	 * 修改内容
	 * @param tContent
	 * @return
	 */
	@RequestMapping("/rest/content/edit")
	@ResponseBody
	public TaotaoResult updateContent(TbContent tContent){
		
		return contentService.updateContent(tContent);
	}
	
	/**
	 * 删除内容
	 * @param tContent
	 * @return
	 */
	@RequestMapping("/content/delete")
	@ResponseBody
	public TaotaoResult deleteContent(String ids){
		
		return contentService.deleteContent(ids);
	}
}
