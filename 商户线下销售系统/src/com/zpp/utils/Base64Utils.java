package com.zpp.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {

	public static String encoder(String text) {
		String str=null;
		try {
			 str=Base64.getEncoder().encodeToString(text.getBytes("utf-8"));
			
		} catch (UnsupportedEncodingException e) {
			System.err.println("base ß∞‹");
		}
		return str;
	}
	public static String decoder(String base64Text) {
		
		String str = null;
		try {
			byte[] base64=Base64.getDecoder().decode(base64Text);
			str = new String(base64,"utf-8");
			
		} catch (Exception e) {
			System.err.println(base64Text+"baseΩ‚¬Î ß∞‹");
		}
		return str;
	}
	
}
