package com.zpp.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.zpp.domain.Product;
import com.zpp.domain.User;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.CookiesUtils;
import com.zpp.utils.JsonUtils;
import com.zpp.utils.UploadUtils;

import Jedis.JedisPoolUtils;
import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class AlterProductServlet
 */
@MultipartConfig
@WebServlet("/AlterProductServlet")
public class AlterProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		
		try {
			int pid=Integer.parseInt(request.getParameter("pid"));
			String productName = request.getParameter("productName");
			String productClass = request.getParameter("productClass");
			double price = Double.parseDouble(request.getParameter("price"));
			int productCount = Integer.parseInt(request.getParameter("productCount"));
			String productMessage=request.getParameter("productMessage");
			
			String savePath = request.getServletContext().getRealPath("/productImg");
			
			
			
			Jedis jedis=JedisPoolUtils.getJedis();
			String jsonstr=jedis.hget("users", CookiesUtils.getCookie(request.getCookies(), "sid"));
			User user=JsonUtils.getUser(jsonstr);
			jedis.close();
			
			boolean flag=false;
			
			String fileName = null;
			// �ϴ������ļ�
			Part part = request.getPart("productImg");// ͨ����file�ؼ�(<input type="file" name="file">)������ֱ�ӻ�ȡPart����
			// ��ȡ����ͷ������ͷ�ĸ�ʽ��form-data; name="file"; filename="snmp4j--api.zip"
			String header = part.getHeader("Content-Disposition");
			// ��ȡ�ļ���
			fileName = UploadUtils.getFileName(header);
		
			//У���ʽ
			String form=fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
			if(form!=null&&!form.isEmpty()) {

				if(!form.equals("png")&&!form.equals("jpg")&&!form.equals("jpeg")&&!form.equals("gif")&&!form.equals("svg")) {
					
					request.setAttribute("isSuccess", false);
					throw new RuntimeException("��ʽ����");
					
				}
				
				String dir=UploadUtils.getDir(fileName);
				fileName = UploadUtils.getUUIDName(fileName);
				
				// ���ļ�д��ָ��·��
				File file=new File(savePath + dir);
				
				if(!file.exists()&&!file.isDirectory()) {
					file.mkdirs();    
				}
				fileName=savePath + dir +fileName;
				part.write(fileName);
				
				
				SellerService service=new SellerServiceImpl();
				//User user=service.getUserBySid(CookiesUtils.getCookie(request.getCookies(), "sid"));
				Product product=new Product(user.getId(), productName, price, productCount, fileName.substring(fileName.indexOf(this.getServletContext().getContextPath().replace("/", "\\"))), productMessage, productClass);
				product.setPid(pid);
				flag=service.alterProduct(product);
				
			}else {
				SellerService service=new SellerServiceImpl();
				Product product=new Product(user.getId(), productName, price, productCount, null, productMessage, productClass);
				product.setPid(pid);
				flag=service.alterProduct(product);
			}
			
			
			
			request.setAttribute("isSuccess", flag);
		} catch (Exception e) {
			request.setAttribute("isSuccess", false);
			//e.printStackTrace();
		}finally {
			
			request.getRequestDispatcher("/page/success.jsp").forward(request, response);
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
