package com.zpp.utils;

import com.zpp.domain.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {
public static User getUser(String jsonstr){
	JSONArray jsonArray= new JSONArray().fromObject(jsonstr);
	Object o=jsonArray.get(0);
	JSONObject jsonObject2=JSONObject.fromObject(o);
	User user =(User)JSONObject.toBean(jsonObject2, User.class);
	return user;
}
}
