<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
	<div class="row">
		<div class="col-lg-4 col-md-4 col-sm-6">
			<h4>
				<small>Welcome to Zpp</small>
			</h4>
		</div>
		<div class="col-lg-5 col-md-5 hidden-xs col-sm-6"></div>
		<div class="col-lg-3 col-md-3 col-sm-12" style="padding-top: 15px;">
			<h4>
				<img src="${pageContext.request.contextPath }/imgs/icon/me.svg" witch="20px" height="20px">${user.username}
				<a class="label label-danger" href="${pageContext.request.contextPath }/SignOut">退出</a> <a
					class="label label-danger" href="${pageContext.request.contextPath }/page/findPwd.html">修改密码</a>
			</h4>
		</div>
	</div>
</div>

<!--导航栏-->
<div class="container" style="margin-top: 10px;">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand active" href="${pageContext.request.contextPath }/page/index.jsp"> 首页 </a>
			</div>
			
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">

					<li class="${currpage eq 'addmer'?'active':'' }"><a
						href="${pageContext.request.contextPath }/page/admin/AddMerchandise.jsp">添加商品 </a></li>
					<li class="${currpage eq 'onsale'?'active':'' }"><a
						href="${pageContext.request.contextPath }/OnSaleProductList?currentPage=1&productClass=5YWo6YOo"
						id="zs">在售商品 </a></li>
					<li class="${currpage eq 'mer'?'active':'' }"><a
						href="${pageContext.request.contextPath }/FindProductAll?currentPage=1&productClass=5YWo6YOo"
						id="ck">商品仓库</a></li>
					<li class="${currpage eq 'base_info'?'active':'' }"><a
						href="${pageContext.request.contextPath }/page/admin/base_info_set.jsp">商铺设置</a></li>
					<li class="${currpage eq 'myqr'?'active':'' }"><a href="${pageContext.request.contextPath }/page/admin/MyQRcode.jsp" id="qr" >我的二维码</a></li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">订单中心 <span class="caret"></span></a>
						<ul class="dropdown-menu ">
							<li class="${'5YWo6YOo6K6i5Y2V' eq myclass?'active':'' }"><a href="${pageContext.request.contextPath }/AllOrderCheck?orderClass=5YWo6YOo&currentPage=1">全部订单</a></li>
							<li role="separator" class="divider"></li>
							<li class="${orderClass eq '5pyq5Y%2BW6LSn'?'active':'' }"><a href="${pageContext.request.contextPath }/AllOrderCheck?orderClass=5pyq5Y%2BW6LSn&currentPage=1">未取货订单</a></li>
							<li class="${orderClass eq '5bey5Y%2BW6LSn'?'active':'' }"><a href="${pageContext.request.contextPath }/AllOrderCheck?orderClass=5bey5Y%2BW6LSn&currentPage=1">已取货订单</a></li>
							<li role="separator" class="divider"></li>
							<li class="${currpage eq 'showpage'?'active':'' }"><a href="${pageContext.request.contextPath }/ShowTeke">展示未取货编号</a></li>

						</ul></li>
				</ul>

			</div>
		</div>
	</nav>
</div>
<script> var zpmess="3"; </script>
<c:if test="${!empty myclass && myclass eq '5YWo6YOo6K6i5Y2V'}">
<script> zpmess="1";</script>
</c:if>

<c:if test="${currpage eq 'showpage'}">
<script> zpmess="2";</script>
</c:if>>


<script>


var websocket = null;
var domain2=window.location.host;

//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://"+domain2+"${pageContext.request.contextPath }/ShowTekeSocket");
}
else {
    alert('当前浏览器 Not support websocket')
}

//连接成功建立的回调方法
websocket.onopen = function () {
   // setMessageInnerHTML("WebSocket连接成功");
    send("sid:${cookie.sid.value}");
}

</script>

<script src="${pageContext.request.contextPath }/js/MyWEBsocket.js"></script>
<script>
$(function(){
	//clearInterval(tt);
	//	setInterval(function  () {
//        
//    },30000) 
	
}); 

function getnewmessage(){
	send(zpmess);
	
}

</script>