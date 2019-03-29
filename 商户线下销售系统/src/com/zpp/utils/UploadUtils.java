package com.zpp.utils;

import java.io.File;
import java.util.UUID;

public class UploadUtils {
	/**
	 * 获取随机名称
	 * 
	 * @param realName 真实名称
	 * @return uuid
	 */
	public static String getUUIDName(String realName) {
		// realname 可能是 1.jpg 也可能是 1
		// 获取后缀名
		int index = realName.lastIndexOf(".");
		if (index == -1) {
			return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		} else {
			return UUID.randomUUID().toString().replace("-", "").toUpperCase() + realName.substring(index);
		}
	}

	/**
	 * 获取文件真实名称
	 * 
	 * @param name
	 * @return
	 */
	public static String getRealName(String name) {
		// c:/upload/1.jpg 1.jpg
		// 获取最后一个"/"
		int index = name.lastIndexOf("\\");
		return name.substring(index + 1);
	}

	/**
	 * 获取文件目录
	 * 目录分离算法
	 * @param name 文件名称
	 * @return 目录
	 */
	public static String getDir(String name) {
		int i = name.hashCode();
		String hex = Integer.toHexString(i);
		int j = hex.length();
		for (int k = 0; k < 8 - j; k++) {
			hex = "0" + hex;
		}
		return File.separator + hex.charAt(0) + File.separator + hex.charAt(1)+File.separator;
	}

	public static String getFileName(String header) {
		/**
		 * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
		 * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
		 * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
		 */
		String[] tempArr1 = header.split(";");
		/**
		 * 火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
		 * IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		// 获取文件名，兼容各种浏览器的写法
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}

}
