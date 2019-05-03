package com.zpp.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.utils.CookiesUtils;
import com.zpp.utils.QrCodeCreateUtil;

/**
 * Servlet implementation class TekeServlet
 */
@WebServlet("/TekeServlet")
public class TekeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int oid=Integer.parseInt(request.getParameter("oid"));
			String equipment=CookiesUtils.getCookie(request.getCookies(), "equipment");
			int uid=Integer.parseInt(CookiesUtils.getCookie(request.getCookies(), "uid"));
			String host=request.getParameter("addr");
			host="http://"+host+request.getContextPath()+"/SweepTeke?uid="+uid+"&oid="+oid+"&equipment="+equipment;
		//	System.out.println(host);//¼ÇµÃ×¢ÊÍ
			response.setContentType("text/html;charset=utf-8;");
			QrCodeCreateUtil.createQrCode(response.getOutputStream(),host, 1000, "JPEG");
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/page/errorpage.html");
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
