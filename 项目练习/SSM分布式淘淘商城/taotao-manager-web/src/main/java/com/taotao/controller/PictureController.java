package com.taotao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.utils.FastDFSClient;

/**
 * 图片上传Controller
 * @author zp
 *
 */
@Controller
public class PictureController {

	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	/**
	 *  文件上传
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping(value="/pic/upload",produces="application/json;charset=utf-8")
	@ResponseBody
	public String picUpload(MultipartFile uploadFile)  {
		Map result=new HashMap();
		try {
			//接收文件
			//取扩展名
			String originalFilename = uploadFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.indexOf(".")+1).toLowerCase();
			//拦截脏文件
			if(extName.indexOf("jpg")==-1&&extName.indexOf("gif")==-1&&extName.indexOf("png")==-1&&extName.indexOf("bmp")==-1) {
				throw new RuntimeException("非法格式");
			}
			//上传到图片服务器
			FastDFSClient fastDFSClient=new FastDFSClient("classpath:resource/client.conf");
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			//加载图片服务器地址的属性文件
			url=IMAGE_SERVER_URL+url;
			//响应数据
			result.put("error", 0);
			result.put("url", url);
			
			
		} catch (Exception e) {
			result.put("error", 1);
			result.put("message", "图片上传失败");
			e.printStackTrace();
		}
		return JsonUtils.objectToJson(result);
		
		
	}
}
