package com.zpp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

 
public class MailUtils {
private static final String myemail="zpzydsxzqf@qq.com";
	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.����һ���������ʼ��������Ự���� Session

		Properties props = new Properties();
		//���÷��͵�Э��
		props.setProperty("mail.transport.protocol", "SMTP");
		
		//���÷����ʼ��ķ�����
		props.setProperty("mail.host", "smtp.qq.com");
		props.setProperty("mail.smtp.auth", "true");// ָ����֤Ϊtrue

		// ������֤��
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//���÷����˵��ʺź�����
				return new PasswordAuthentication(myemail, "lqudhopfpxvmbbbg");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.����һ��Message�����൱�����ʼ�����
		Message message = new MimeMessage(session);

		//���÷�����
		message.setFrom(new InternetAddress(myemail));

		//���÷��ͷ�ʽ�������
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		//�����ʼ�����
		message.setSubject("ZPPϵͳ-������֤");
		// message.setText("����һ�⼤���ʼ�����<a href='#'>���</a>");

		//String url="http://localhost:8080/MyTomcat/UserServlet?method=active&code="+emailMsg;
		//String content="<h1>����xxx�ļ����ʼ�!����������������!</h1><h3><a href='"+url+"'>"+url+"</a></h3>";
		SimpleDateFormat sm=new SimpleDateFormat("yyyy �� MM �� dd ��");
		Date date=new Date();
		String nowtime=sm.format(date.getTime());
		String content="<div style=\"line-height:40px;height:40px\">&nbsp;</div>"
				+ "<p style=\"margin:0;padding:0;font-size:14px;line-height:30px;color:#333;font-family:arial,sans-serif;font-weight:bold\">�װ����û���</p>"
				+"<div style=\"line-height:20px;height:20px\">&nbsp;</div>"
				+"<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'����',arial,sans-serif\">���ã���л��ʹ��zpp���������ڽ���������֤�������������֤��Ϊ��</p>"
				+"<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'����',arial,sans-serif\">"
				+"<b style=\"font-size:18px;color:#f90\">"+emailMsg+"</b>"
				+"<span style=\"margin:0;padding:0;margin-left:10px;line-height:30px;font-size:14px;color:#979797;font-family:'����',arial,sans-serif\">(Ϊ�˱������ʺŵİ�ȫ�ԣ�����1Сʱ�������֤��)</span></p>"
				+"<div style=\"line-height:80px;height:80px\">&nbsp;</div>"
				+"<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'����',arial,sans-serif\">zpp�ʺ��Ŷ�</p>"
				+"<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'����',arial,sans-serif\">"+nowtime+"</p>"
				+"<div style=\"line-height:20px;height:20px\">&nbsp;</div>";
		//�����ʼ�����
		message.setContent(content, "text/html;charset=utf-8");

		// 3.���� Transport���ڽ��ʼ�����
		Transport.send(message);
	}
	
	public static String sendMail(String email) {
	
		int max=999999;
		int min=100000;
		int ems=(int) (Math.random()*(max-min)+min);
		String emailMsg=String.valueOf(ems);
		try {
			MailUtils.sendMail(email, emailMsg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return emailMsg;
	}
	
	
}
