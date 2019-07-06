package com.zpp.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpp.domain.Finance;
import com.zpp.domain.Money;
import com.zpp.domain.User;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.service.SellerService;
import com.zpp.service.SellerServiceImpl;
import com.zpp.utils.CookiesUtils;

/**
 * Servlet implementation class WithMoney
 */
@WebServlet("/WithMoney")
public class WithMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SellerService service=new SellerServiceImpl();
	private PayService payService=new PayServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				int money=Integer.parseInt(request.getParameter("money"));
				String payee=request.getParameter("name");
				if(money<=0) {
					response.getWriter().print(false);
					return;
				}
				User user=CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
				//SellerService service=new SellerServiceImpl();
				Finance finance=service.GetFinanceByUid(user.getId());
				if(finance.getPay()==null||finance.getPay().isEmpty()){
					response.getWriter().print(false);
					return;
				}
				
				boolean flag=payService.tekeMoney(user.getId(), money);
				if(!flag){
					response.getWriter().print(false);
					return;
				}
				
				Date date=new Date(System.currentTimeMillis());
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time=sdf.format(date);
				Money moneybean=new Money(user.getId(), money, finance.getPay(), payee, time, 0);
				payService.addMoneyTab(moneybean);
				response.getWriter().print(true);
				
			} catch (Exception e) {
				response.getWriter().print(false);
				e.printStackTrace();
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
