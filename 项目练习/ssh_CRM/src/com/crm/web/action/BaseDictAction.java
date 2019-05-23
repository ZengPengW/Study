package com.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.crm.domain.BaseDict;
import com.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {

	private BaseDict baseDict=new BaseDict();
	@Override
	public BaseDict getModel() {
		return baseDict;
	}
	private BaseDictService baseDictService;

	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	//查询字典 根据类型名称
	public String findByTypeCode() {
		List<BaseDict> list=baseDictService.findByTypeCode(baseDict.getDict_type_code());
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[] {"dict_sort","dict_enable","dict_memo"});
		JSONArray jsonArray=JSONArray.fromObject(list,jsonConfig);
		String jsonString=jsonArray.toString();
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().println(jsonString);
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return NONE;
	}
	

}
