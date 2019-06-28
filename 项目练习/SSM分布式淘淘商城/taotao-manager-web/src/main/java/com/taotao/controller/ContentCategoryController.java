package com.taotao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.pojo.TbContentCategory;
/**
 * 内容分类的处理controller
 * @author zp
 *
 */
@Controller
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 获取树节点
	 * 
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(name="id",defaultValue="0")Long parentId){
		return contentCategoryService.getContentCategoryList(parentId);
	}
	/**
	 新增节点
	 */
	@RequestMapping(value="/content/category/create",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId, String name){
		return contentCategoryService.createContentCategory(parentId, name);
	}
	
	/**
	 * 删除节点
	 * @param id
	 * @throws IOException 
	 */
	@RequestMapping(value="/content/category/delete",method=RequestMethod.POST)
	public void deleteContentCategory(Long id,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		try {
			if(contentCategoryService.deleteContentCategoryById(id)) {				
				response.getWriter().print("删除成功");
			}else {
				response.getWriter().print("删除失败,只能删除叶子节点");
			}
		} catch (Exception e) {
			response.getWriter().print("发生异常!稍后再试!");
			e.printStackTrace();
		}
		
	}
	/**
	 * 更新节点
	 * @param id
	 * @param name
	 * @throws IOException 
	 */
	@RequestMapping(value="/content/category/update",method=RequestMethod.POST)
	public void updateContentCategory(Long id, String name,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		try {
			contentCategoryService.updateContentCategoryById(id, name);
			response.getWriter().print("更新成功");
		} catch (Exception e) {
			response.getWriter().print("更新失败");
			e.printStackTrace();
		}
		 
	}
	
	
}
