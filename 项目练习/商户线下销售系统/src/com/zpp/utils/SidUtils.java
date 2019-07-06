package com.zpp.utils;

import java.security.MessageDigest;


public class SidUtils {

	final static String key="zpzydsxzqf"; 
	public static String Md5(String dataStr) {
		
		try {
           
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
           
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

	}
	public static String getSid(String username,String password) {
		String datastr=username+key+password;
		
		for (int i = 0; i <3; i++) {
			datastr=Md5(datastr);
		}
		// System.out.println(datastr);
		return datastr;
		
	}
}
