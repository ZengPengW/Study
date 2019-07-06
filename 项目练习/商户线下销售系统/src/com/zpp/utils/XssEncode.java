package com.zpp.utils;

public class XssEncode {

	public static String xssEncode(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append('��');// ȫ�Ǵ��ں�
				break;
			case '<':
				sb.append('��');// ȫ��С�ں�
				break;
			case '\'':
				sb.append('��');// ȫ�ǵ�����
				break;
			case '\"':
				sb.append('��');// ȫ��˫����
				break;
			case '&':
				sb.append('��');// ȫ��
				break;
			case '\\':
				sb.append('��');// ȫ��б��
				break;
			case '#':
				sb.append('��');// ȫ�Ǿ���
				break;
			case '(':
				sb.append('��');//
				break;
			case ')':
				sb.append('��');//
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}


}


