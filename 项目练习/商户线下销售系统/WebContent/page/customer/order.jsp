<%@page import="com.zpp.service.SellerServiceImpl"%>
<%@page import="com.zpp.service.SellerService"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.zpp.domain.Product"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="com.zpp.domain.ShopCart"%>
<%@page import="java.util.List"%>
<%@page import="com.zpp.utils.CookiesUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>确认订单</title>
<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maxinum-scale=1,user-scalable=no">
<link href="${pageContext.request.contextPath }/css/bootstrap.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath }/css/order.css"
	type="text/css" rel="stylesheet">

</head>
<body style="padding-top: 13%; padding-bottom: 13%;">
<% 
String cart = CookiesUtils.getCookie(request.getCookies(), "cart");
List<ShopCart> shopCarts = null;
if (cart != null) {
	JSONArray jsonArray = JSONArray.fromObject(cart);
	shopCarts = jsonArray.toList(jsonArray, ShopCart.class);
}
List<Product> cartList = new ArrayList<Product>();
HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
if (shopCarts != null) {
	SellerService service = new SellerServiceImpl();
	int uid = Integer.parseInt(CookiesUtils.getCookie(request.getCookies(), "uid"));
	Product p = null;
	

	int shoppingCount = 0;
	double sumPrice = 0;
	for (ShopCart sp : shopCarts) {
		p = service.GetProductByPid(uid, sp.getPid());
		cartList.add(p);
		hm.put(sp.getPid(), sp.getCount());
		sumPrice += (p.getPrice() * sp.getCount());
	}
	sumPrice=Double.valueOf(String.format("%.2f", sumPrice));
	request.setAttribute("cartlist", cartList);
	request.setAttribute("hm", hm);
	request.setAttribute("sumPrice", sumPrice);		
}
%>

	<div class="container">


		<header class="header">
			<span ><a href="${pageContext.request.contextPath }/OnSaleProductListClient?currentPage=1&productClass=5YWo6YOo"><img src="${pageContext.request.contextPath }/imgs/icon/index.svg" width="50%" height="100%" alt="首页"></a></span>
			<h1 class="fon" >
				<strong>我的订单</strong>
			</h1>
		</header>

		<div class="footer">
			<div id="paybox"
				style="position: absolute; left: 0; font-size: 2rem; color: white;">
				<span id="paymoney"> ￥${sumPrice}</span>
			</div>
			<div class="settlement" id="settlement">去支付</div>
		</div>
	</div>


	<div class="container">
		<div class="row " style="margin-top: 10%;">
			<ul class="cart-group-253X-" style="padding: 10px; margin: 0">
				<c:forEach items="${cartlist}" var="ls">
				<li><img  class='cart-group-2U2FI' src='${ls.productImg}'><div class='cart-group-OgNfZ'><p class='cart-group-2_GET'>${ls.productName}</p></div> <span class='cart-group-1vF9L'>×&nbsp;${hm.get(ls.pid)}</span> <span class='cart-group-3GWE2'><span><span class='cart-group-12n-9'></span></span></span> <span class='cart-group-31C83 cart-group-2EEJb'><span class='cart-group-12n-9'></span>¥${ls.price}</span></li>	
				
				</c:forEach>	
			</ul>
		</div>

	</div>
<script type="text/javascript">
$("#settlement").click(function(){
	window.location.href="${pageContext.request.contextPath }/MyAlipayServlet";
	
});

</script>
</body>
</html>