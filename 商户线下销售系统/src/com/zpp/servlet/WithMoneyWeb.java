package com.zpp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

import net.sf.json.JSONArray;

/**
 * Servlet implementation class WithMoneyWeb
 */
@WebServlet("/WithMoneyWeb")
public class WithMoneyWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
		SellerService service=new SellerServiceImpl();
		PayService payService=new PayServiceImpl();
		try {
			Finance finance=service.GetFinanceByUid(user.getId());
			List<Money> list=payService.getMonerList(user.getId());
			request.setAttribute("finance", finance);
			request.setAttribute("moneylist", list);
			request.getRequestDispatcher("/page/admin/withdrawMoney.jsp").forward(request, response);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
