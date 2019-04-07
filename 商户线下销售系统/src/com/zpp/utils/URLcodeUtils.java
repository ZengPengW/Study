package com.zpp.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLcodeUtils {
public static String encoder(String str) {
	String s=null;
	try {
		 s=URLEncoder.encode(str,"utf-8");
		
	} catch (UnsupportedEncodingException e) {
		System.err.println("±‡¬Î ß∞‹");
	}
	return s;
}

public static String decoder(String str) {
	String s=null;
	try {
		 s=URLDecoder.decode(str,"utf-8");
		
	} catch (UnsupportedEncodingException e) {
		System.err.println("Ω‚¬Î ß∞‹");
	}
	return s;
}
}
