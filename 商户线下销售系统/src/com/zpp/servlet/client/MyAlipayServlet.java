package com.zpp.servlet.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.config.AlipayClientFactory;
import com.zpp.domain.Order;
import com.zpp.domain.Product;
import com.zpp.domain.ShopCart;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.Base64Utils;
import com.zpp.utils.CookiesUtils;
import com.zpp.utils.URLcodeUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MyAlipayServlet
 */
@WebServlet("/MyAlipayServlet")
public class MyAlipayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String shopcart=CookiesUtils.getCookie(request.getCookies(),"cart");
		//System.out.println(shopcart);
		if(shopcart==null||shopcart.isEmpty())throw new RuntimeException("��Ʒ��Ϣ������");
		
		int uid = Integer.parseInt(CookiesUtils.getCookie(request.getCookies(), "uid"));
		
		JSONArray jsonArray=JSONArray.fromObject(shopcart);
		List<ShopCart> list=jsonArray.toList(jsonArray, ShopCart.class);
		
		//List<Product> buyProduct=new ArrayList<Product>();
		//����֧�����
		SellerService service=new SellerServiceImpl();
		Product p=null;
		double  payMoney=0;
		for (ShopCart ls : list) {
			p=service.GetProductByPid(uid, ls.getPid());
		//	buyProduct.add(p);
			payMoney+=(ls.getCount()*p.getPrice());
		}
		payMoney=Double.valueOf(String.format("%.2f", payMoney));
		
		
		//����֧��
		String username=CookiesUtils.getCookie(request.getCookies(), "username");
		username=Base64Utils.decoder(username);
		String phone=CookiesUtils.getCookie(request.getCookies(), "phone");
		
		String equipment=CookiesUtils.getCookie(request.getCookies(), "equipment");
		if(equipment==null||equipment.isEmpty()) {
			equipment=request.getSession().getId();
			Cookie cookie5 =new Cookie("equipment", equipment);
			cookie5.setMaxAge(24*30*30*365);
			response.addCookie(cookie5);
		}
		Date date=new Date(System.currentTimeMillis());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(date);
		
		PayService payService=new PayServiceImpl();
		String gid=null;//ȡ�����
		boolean bl=true;
		while(bl) {
			int nob=(int) (Math.random()*800000+100000);
			gid=nob+"";
			bl=payService.isExistenceGid(uid, gid);
		}
		Order order=new Order(uid, time, gid, username, phone, shopcart, equipment, payMoney);
		
		String orderjson=JSONObject.fromObject(order).toString();
		
		String orderjsoneco=URLcodeUtils.encoder(Base64Utils.encoder(orderjson));
		
		Map<String, String> maps=new HashMap<String,String>();
	//	String productlist=JSONArray.fromObject(buyProduct).toString();
		maps.put("total_amount", payMoney+"");//�������
		maps.put("subject","����֧��");//����
		maps.put("store_id", uid+"");//�̻�id
		
		maps.put("product_code", "QUICK_WAP_PAY");//�ֻ�ҳ��
		maps.put("out_trade_no", date.getTime()+"");//�������
		
		//System.out.println(orderjsoneco);
		maps.put("passback_params", orderjsoneco);//��������
	   // maps.put("product_code", "FAST_INSTANT_TRADE_PAY");//����ҳ��
		maps.put("ReturnUrl", "http://47.106.67.171"+request.getContextPath()+"/MyOrderCheck");//ͬ��֪ͨ
		maps.put("NotifyUrl", "http://47.106.67.171"+request.getContextPath()+"/page/alipay/notify_url.jsp");//�첽֪ͨ
		
		
			AlipayClientFactory ali=new AlipayClientFactory();
			String form=ali.ydAndPc_Pay(maps);
			//String form=ali.Pc_Pay(maps);
			if(!form.equals("err")) {
//				Cookie cookie =new Cookie("cart", "cart");
//				cookie.setMaxAge(0);
//				response.addCookie(cookie);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(form);// ֱ�ӽ������ı�.h.tml�����ҳ��
				response.getWriter().flush();
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
