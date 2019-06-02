package com.crm.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.crm.domain.User;
import com.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
/*
 * 用户管理类
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user=new User();
	@Override
	public User getModel() {		
		return user;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String regist(){
		userService.regist(user);	
		return LOGIN;
	}
	
	public String login() {
		User isExistUser=userService.login(user);
		if(isExistUser!=null) {
			//成功
			ActionContext.getContext().getSession().put("existUser", isExistUser);
			return SUCCESS;
		}else {
			//失败
			this.addActionError("用户名或密码错误");
			return LOGIN;
		}
		
	}
	
	//findAllUser
	public String findAllUser() throws IOException {
		List<User> list=userService.findAll();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"user_code","user_password","user_state"});
		JSONArray jsonArray=JSONArray.fromObject(list,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());;
		return NONE;
	}
}
