<%@page import="com.zpp.domain.Finance"%>
<%@page import="com.zpp.service.SellerServiceImpl"%>
<%@page import="com.zpp.service.SellerService"%>
<%@page import="com.zpp.utils.JsonUtils"%>
<%@page import="com.zpp.domain.User"%>
<%@page import="com.zpp.utils.CookiesUtils"%>
<%@page import="Jedis.JedisPoolUtils"%>
<%@page import="redis.clients.jedis.Jedis"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商铺设置</title>
		<link href="../../css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="../../js/jquery-1.11.3.min.js"></script>
		<script src="../../js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../../js/messages_zh.js"></script>
		<script type="text/javascript" src="../../js/jquery.validate.min.js" ></script>
		<script type="text/javascript" src="../../js/base_info.js"></script>
	</head>
	<%
		request.setAttribute("currpage", "base_info");
		String sid = CookiesUtils.getCookie(request.getCookies(), "sid");
		User user = CookiesUtils.getUser(sid);
		String email = user.getEmail();
		int index = email.indexOf("@");
		email = email.substring(0, 2) + "********" + email.substring(index);
		request.setAttribute("user", user);
		SellerService service = new SellerServiceImpl();
		Finance finance = service.GetFinanceByUid(user.getId());
		if (finance == null) {
			finance = new Finance();
		}
	%>
<style>
		
		
		label.error {
			background: url(../../imgs/icon/unchecked.gif) no-repeat 10px 3px;
			padding-left: 30px;
			font-family: georgia;
			font-size: 15px;
			font-style: normal;
			color: red;
		}
	</style>

	<body>
		<!--头部-->
		<jsp:include page="/page/head.jsp" flush="true"></jsp:include>

		<div class="container">
			<div class="row">
				<div class="col-md-2">
					<img src="../../imgs/icon/me.svg" width="100px" height="100px" />
				</div>
				<div class="col-md-3"></div>
				<div class="col-md-5">
					<h1>
					<span class="label label-danger">商铺资料</span>
				</h1>
				</div>
			</div>
			<div class="row">
				<div class="container">
					<form role="form" class="form-horizontal " action="/Zpp/AlterBaseInfo" method="post" id="base_info" >
						<div class="form-group">
							<label for="shopname" class="col-sm-3 control-label">商铺名称</label>
							<div class="col-sm-6">
								<div class="input-group">
									<input type="text" class="form-control" id="shopname" placeholder="请输入你要修改商铺名称" name="shopname" value="${user.shopname}" readonly>
									<span class="input-group-btn">
        								<button class="btn btn-danger " type="button" id="shopnamechange">修改</button>
      								</span>
								</div>

							</div>
						</div>
						<div class="form-group">
							<label for="username" class="col-sm-3 control-label">用户名</label>
							<div class="col-sm-6">
								<div class="input-group">
								<input type="text" class="form-control" id="username" placeholder="请输入你要修改的用户名" name="username" value="${user.username }" readonly>
									<span class="input-group-btn">
        								<button class="btn btn-success " type="button" id="usernamechange">修改</button>
      								</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label" >邮箱</label>
							<div class="col-sm-6">
								<%=email%>
							</div>
						</div>
						<div class="form-group">
							<label for="payment" class="col-sm-3 control-label">用于提现的支付宝</label>
							<div class="col-sm-6">
								<div class="input-group">
								<input type="text" class="form-control" id="payment" placeholder="请输入你要绑定的支付宝账户" name="payment" readonly value="<%=finance.getPay() == null ? " " : finance.getPay()%>">
									<span class="input-group-btn">
        								<button class="btn btn-warning " type="button" id="paychange">修改</button>
      								</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="yanzhengma" class="col-sm-3 control-label">验证码</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="yanzhengma" name="yanzhengma" placeholder="请输入邮箱验证码">
								<input type="button" value="获取验证码" onclick="getyzm(this)" id="getcheck" class="btn btn-primary" />
							</div>

						</div>
						<div class="form-group text-center">
							<div class="col-lg-offset-4 col-sm-4 ">
								<button type="submit" style="width: 80%; height: 40px; background-color: red; font-size: 20px; color: white;" class="btn btn-default">保存</button>
							</div>
						</div>
						<br />

					</form>

				</div>

			</div>

		</div>

	</body>
<!-- websocket -->
<script src="${pageContext.request.contextPath }/js/tone.js" type="text/javascript" ></script>
<script>var uid="${user.id}a5"+new Date().getTime();</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/MyWEBsocket.js" ></script>
<script >
//连接成功建立的回调方法
websocket.onopen = function () {
   // setMessageInnerHTML("WebSocket连接成功");
  //  send("sid:${cookie.sid.value}");
   
    
}
</script>
<!-- message -->
<link href="${pageContext.request.contextPath }/css/message.css" rel="stylesheet" type="text/css"/>



<div id="winpop">
 <div class="title">短消息！<span class="close" onclick="myclose()">×</span></div>
    <div class="con">有新的订单啦</div>
</div>

</html>