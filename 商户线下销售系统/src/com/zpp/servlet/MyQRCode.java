package com.zpp.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.WriterException;
import com.zpp.domain.User;
import com.zpp.utils.CookiesUtils;
import com.zpp.utils.QrCodeCreateUtil;

/**
 * Servlet implementation class MyQRCode
 */
@WebServlet("/MyQRCode")
public class MyQRCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String host=request.getParameter("addr");
		//System.out.println(host);
		User user = CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
		if (user != null) {
//			String savePath = request.getServletContext().getRealPath("/imgs/QR");

			// 把文件写到指定路径
//			File file = new File(savePath);
//
//			if (!file.exists() && !file.isDirectory()) {
//				file.mkdirs();
//			}
		//	file=new File(savePath+File.separator+user.getId()+"jpg");
			host="http://"+host+request.getContextPath()+"/IndexClient?uid="+user.getId();
			System.out.println(host);
			try {
				QrCodeCreateUtil.createQrCode(response.getOutputStream(), host, 900, "JPEG");
			} catch (WriterException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
