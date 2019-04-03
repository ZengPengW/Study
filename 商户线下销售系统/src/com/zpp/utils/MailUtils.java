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
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		//设置发送的协议
		props.setProperty("mail.transport.protocol", "SMTP");
		
		//设置发送邮件的服务器
		props.setProperty("mail.host", "smtp.qq.com");
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//设置发送人的帐号和密码
				return new PasswordAuthentication(myemail, "lqudhopfpxvmbbbg");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);

		//设置发送者
		message.setFrom(new InternetAddress(myemail));

		//设置发送方式与接收者
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		//设置邮件主题
		message.setSubject("ZPP系统-保护验证");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		//String url="http://localhost:8080/MyTomcat/UserServlet?method=active&code="+emailMsg;
		//String content="<h1>来自xxx的激活邮件!激活请点击以下链接!</h1><h3><a href='"+url+"'>"+url+"</a></h3>";
		SimpleDateFormat sm=new SimpleDateFormat("yyyy 年 MM 月 dd 日");
		Date date=new Date();
		String nowtime=sm.format(date.getTime());
		String content="<div style=\"line-height:40px;height:40px\">&nbsp;</div>"
				+ "<p style=\"margin:0;padding:0;font-size:14px;line-height:30px;color:#333;font-family:arial,sans-serif;font-weight:bold\">亲爱的用户：</p>"
				+"<div style=\"line-height:20px;height:20px\">&nbsp;</div>"
				+"<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\">您好！感谢您使用zpp服务，您正在进行邮箱验证，本次请求的验证码为：</p>"
				+"<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\">"
				+"<b style=\"font-size:18px;color:#f90\">"+emailMsg+"</b>"
				+"<span style=\"margin:0;padding:0;margin-left:10px;line-height:30px;font-size:14px;color:#979797;font-family:'宋体',arial,sans-serif\">(为了保障您帐号的安全性，请在1小时内完成验证。)</span></p>"
				+"<div style=\"line-height:80px;height:80px\">&nbsp;</div>"
				+"<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\">zpp帐号团队</p>"
				+"<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\">"+nowtime+"</p>"
				+"<div style=\"line-height:20px;height:20px\">&nbsp;</div>";
		//设置邮件内容
		message.setContent(content, "text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送
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
