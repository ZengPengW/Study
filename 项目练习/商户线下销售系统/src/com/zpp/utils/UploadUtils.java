package com.zpp.utils;

import java.io.File;
import java.util.UUID;

public class UploadUtils {
	/**
	 * ��ȡ�������
	 * 
	 * @param realName ��ʵ����
	 * @return uuid
	 */
	public static String getUUIDName(String realName) {
		// realname ������ 1.jpg Ҳ������ 1
		// ��ȡ��׺��
		int index = realName.lastIndexOf(".");
		if (index == -1) {
			return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		} else {
			return UUID.randomUUID().toString().replace("-", "").toUpperCase() + realName.substring(index);
		}
	}

	/**
	 * ��ȡ�ļ���ʵ����
	 * 
	 * @param name
	 * @return
	 */
	public static String getRealName(String name) {
		// c:/upload/1.jpg 1.jpg
		// ��ȡ���һ��"/"
		int index = name.lastIndexOf("\\");
		return name.substring(index + 1);
	}

	/**
	 * ��ȡ�ļ�Ŀ¼
	 * Ŀ¼�����㷨
	 * @param name �ļ�����
	 * @return Ŀ¼
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
		 * String[] tempArr1 = header.split(";");����ִ����֮���ڲ�ͬ��������£�tempArr1���������������������
		 * �������google������£�tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
		 * IE������£�tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
		 */
		String[] tempArr1 = header.split(";");
		/**
		 * �������google������£�tempArr2={filename,"snmp4j--api.zip"}
		 * IE������£�tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		// ��ȡ�ļ��������ݸ����������д��
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}

}
