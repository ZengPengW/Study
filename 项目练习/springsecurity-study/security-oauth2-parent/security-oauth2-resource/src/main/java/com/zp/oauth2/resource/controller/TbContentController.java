package com.zp.oauth2.resource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zp.oauth2.resource.dto.ResponseResult;
import com.zp.oauth2.resource.pojo.TbContent;
import com.zp.oauth2.resource.service.TbContentService;

@RestController
public class TbContentController {
	
	@Autowired
	private TbContentService tbContentService;
	
	@PostMapping("/list")
	public ResponseResult<List<TbContent>> list(){
		return new ResponseResult<List<TbContent>>(200,HttpStatus.OK.toString(),tbContentService.selectAll());
	}
	
	@GetMapping("/aa")
	public ResponseResult<List<TbContent>> aa(){
		return new ResponseResult<List<TbContent>>(200,HttpStatus.OK.toString(),tbContentService.selectAll());
	}
	
}
