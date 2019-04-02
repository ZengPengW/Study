package com.zpp.utils;

import javax.servlet.http.Cookie;

import com.zpp.domain.User;

import Jedis.JedisPoolUtils;
import redis.clients.jedis.Jedis;

public class CookiesUtils {

	public static String getCookie(Cookie[] cookies,String name) {
		for (int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals(name)) {
				return cookies[i].getValue();
			}
		}
		return null;
	}
	public static User getUser(String sid) {
		Jedis jedis=JedisPoolUtils.getJedis();
		User user=JsonUtils.getUser(jedis.hget("users", sid));
		jedis.close();
		return user;
	}
}
