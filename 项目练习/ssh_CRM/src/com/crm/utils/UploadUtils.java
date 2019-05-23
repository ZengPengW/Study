package com.crm.utils;

import java.util.UUID;

public class UploadUtils {

	public static String getUuidFileName(String fileName) {
		int index=fileName.lastIndexOf('.');
		String extions=fileName.substring(index);
		return UUID.randomUUID().toString().replace("-", "")+extions;
	}
	
	//目录分离算法
	public static String getPath(String uuidFileName) {
		int code=uuidFileName.hashCode();
		int d1=code&0xf;//一级目录
		int code2=code>>>4;
		int d2=code2&0xf;//二级目录
		return "/"+d1+"/"+d2;
		
	}
}
