<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
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

</head>
<style>
	.header{
	height: 46px;
    position: fixed;
    left: 0;
    top: 0;
    width: 100%;
    background: #2395ff;
     padding-left: 10px;
    z-index: 2;
  display: flex;
   justify-content: space-between;
    box-sizing: border-box;
  
   line-height: 46px;
}

.header .fon{
	font-size: 1.1em;
	color: white;
	margin-left: 37%;
	font-weight: 700;
	
}
.cart-group-2U2FI img {
    width: .96rem;
    width: 28.6vw;
    height: .96rem;
    height: 28.6vw;
     text-align: left;
}
body{
	margin-top:22% ;
}
</style>
<body>
<header class="header">
			<h1 class="fon">
				<strong>我的订单</strong>
			</h1>
		</header>
		
<div class="container">
	<div class="panel panel-default">
		
  <div class="panel-heading ">
  	<span>店铺名字</span>
  	<span class="col-sm-offset-10" style="right: 10%;position: fixed;" >实付款<strong  ><font color="red"  >￥100.23</font></strong></span>
  			
  		
  	 </div>	
  <div class="panel-body ">
  
   <div class="media">
  <a class="pull-left cart-group-2U2FI" href="#">
    <img src="../../imgs/icon/unchecked.gif" />
  </a>
  <div class="media-body text-left">
    <h5>用户名：小明</h5>
    <h5>手机:123***1079</h5>
    <h5>取货编号：13213</h5>
    <h5>创建时间：</h5><h6>2019-04-09 18:23:32</h6>
    <input style="display: none" value="oid"></input>
    <button type="button" class="btn btn-primary">取货</button>
  </div>
</div>
  	</div>
  	
 
</div>
	
	
</div>
	
	
</div>

</body>
</html>