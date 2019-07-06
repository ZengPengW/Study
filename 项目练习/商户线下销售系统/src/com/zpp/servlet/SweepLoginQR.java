package com.zpp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.WriterException;
import com.zpp.utils.QrCodeCreateUtil;

/**
 * Servlet implementation class SweepLoginQR
 */
@WebServlet("/SweepLoginQR")
public class SweepLoginQR extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String uuid=request.getParameter("uuid");
			String host=request.getParameter("addr");
			String loc="http://"+host+request.getContextPath()+"/SweepLogin?uuid="+uuid;
			//System.out.println(loc);
			boolean bl=QrCodeCreateUtil.createQrCode(response.getOutputStream(), loc, 900, "JPEG");
		} catch (WriterException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
