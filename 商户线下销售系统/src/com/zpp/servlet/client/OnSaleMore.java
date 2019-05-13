package com.zpp.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.Product;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.Base64Utils;
import com.zpp.utils.CookiesUtils;
import com.zpp.utils.JsonUtils;

/**
 * Servlet implementation class OnSaleMore
 */
@WebServlet("/OnSaleMore")
public class OnSaleMore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SellerService service=new SellerServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
try {
		
			
			int currentPage=Integer.parseInt(request.getParameter("currentPage"));
			String productClass=request.getParameter("productClass");
			
			int uid=Integer.parseInt(CookiesUtils.getCookie(request.getCookies(), "uid"));
			
			//SellerService service=new SellerServiceImpl();
			String deproductClass=Base64Utils.decoder(productClass);
			
			List<Product> list=service.OnSaleProductByID(uid, currentPage,deproductClass);
			String str=JsonUtils.toJsonString(list);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(str);
		} catch (Exception e) {
			
			response.sendRedirect(request.getContextPath()+"/page/errorpage.html");
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
