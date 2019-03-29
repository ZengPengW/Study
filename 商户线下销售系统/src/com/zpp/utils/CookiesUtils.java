package com.zpp.utils;

import javax.servlet.http.Cookie;

public class CookiesUtils {

	public static String getCookie(Cookie[] cookies,String name) {
		for (int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals(name)) {
				return cookies[i].getValue();
			}
		}
		return null;
	}
}
