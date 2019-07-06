package com.zpp.servlet.client;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.dao.impl.SellerdaoImpl;
import com.zpp.domain.PageBean;
import com.zpp.domain.Product;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.Base64Utils;
import com.zpp.utils.CookiesUtils;
import com.zpp.utils.URLcodeUtils;


/**
 * Servlet implementation class OnSaleProductList
 */
@WebServlet(name = "OnSaleProductListClient", urlPatterns = { "/OnSaleProductListClient" })
public class OnSaleProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private SellerService service=new SellerServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		
			String curpage=request.getParameter("currentPage");
			int currentPage=1;
			
			if(curpage!=null&&!curpage.isEmpty())currentPage=Integer.parseInt(request.getParameter("currentPage"));
			String productClass=request.getParameter("productClass");
			if(productClass==null||productClass.isEmpty())
				productClass="5YWo6YOo";
			
			//String curUid=request.getParameter("curUid");
			int uid=0;
			//if(curUid==null||curUid.isEmpty()){
				String uids=CookiesUtils.getCookie(request.getCookies(), "uid");
				if (uids!=null&&!uids.isEmpty()) {
					uid=Integer.parseInt(uids);
				}else {
					throw new RuntimeException("uid空");
				}
//			}else{
//				uid=Integer.parseInt(curUid);
//			}
		//	System.out.println(uid);
			
		//	SellerService service=new SellerServiceImpl();
			
			String deproductClass=Base64Utils.decoder(productClass);
		
			List<Product> list=service.OnSaleProductByID(uid, currentPage,deproductClass);
	
			long totalSize =service.CheckOnsaleProductCount(uid, deproductClass);//总记录数
			long totalPage=totalSize/SellerdaoImpl.pageSize;//总页数
			if(totalSize%SellerdaoImpl.pageSize!=0)totalPage++;
			
			PageBean<Product> pageBean=new PageBean<Product>(currentPage,totalSize,totalPage,list,productClass);
			
			//System.out.println(pageBean.getProductClass());
			request.setAttribute("pageBean", pageBean);
			
			List<Object>productclass= service.FindOnSaleProductClass(uid);
			request.setAttribute("productclass", productclass);
			//System.out.println(productclass.get(0));
			
			request.getRequestDispatcher("/page/customer/customerProductList.jsp").forward(request, response);
		
			
		} catch (Exception e) {
			
			response.sendRedirect(request.getContextPath()+"/page/errorpage.html");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
