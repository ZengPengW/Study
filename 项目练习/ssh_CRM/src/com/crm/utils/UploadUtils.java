package com.crm.utils;

import java.util.UUID;

public class UploadUtils {

	public static String getUuidFileName(String fileName) {
		int index=fileName.lastIndexOf('.');
		String extions=fileName.substring(index);
		return UUID.randomUUID().toString().replace("-", "")+extions;
	}
	
	//Ŀ¼�����㷨
	public static String getPath(String uuidFileName) {
		int code=uuidFileName.hashCode();
		int d1=code&0xf;//һ��Ŀ¼
		int code2=code>>>4;
		int d2=code2&0xf;//����Ŀ¼
		return "/"+d1+"/"+d2;
		
	}
}
