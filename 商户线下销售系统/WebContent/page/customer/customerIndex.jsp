<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1, maxinum-scale=1,user-scalable=no">
		<title>欢迎光临</title>
		<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/welcome.js" ></script>

	</head>
	<style>
		body {
			background-image: url(${pageContext.request.contextPath }/imgs/icon/bg.jpg);
			background-position: center 0;
			background-repeat: no-repeat;
			background-attachment: fixed;
			background-size: cover;
			-webkit-background-size: cover;
			-o-background-size: cover;
			-moz-background-size: cover;
			-ms-background-size: cover;
		}
		
		.input-group-addon {
			color: white;
			width: 250px;
			background-color: RGBA(255, 255, 255, 0.4);
		}
		
		.btn-default {
			width: 374px;
			background-color: rgba(255, 255, 255, 0.4);
		}
		label.error {
			font-family: georgia;
			font-style: normal;
			color: red;
		}
	</style>


	<body>
		
		<div id="mybox" class="container text-center col-lg-offset-2 " style=" position: relative; left: -110%; transform:translateY(50%); ">
			
			<div class="row col-md-offset-3 col-md-3" >
			<img  src="/Zpp/imgs/icon/hi.svg" width=30% height=30%  class="img-rounded" style="display: ">
				<form class="form-horizontal " method="post" action="${pageContext.request.contextPath }/ClientInit" id="welcome" role="form" >
					<input type="text" style=" display: none;" value="${uid}" name="curUid" />
					<input type="text" style=" display: none;" value="1" name="currentPage" />
					<input type="text" style=" display: none;" value="5YWo6YOo" name="productClass" />
				<label for="username" class="col-sm-3 control-label"></label>
				<label for="phone" class="col-sm-3 control-label"></label>
				
					<div class="form-group text-center ">
						<input type="text" class="form-control input-group-addon " style="border-radius: 20px;" id="username" name="username" placeholder="输入你的姓名">
					</div>
					<div class="form-group text-center ">
						<input type="text" class="form-control input-group-addon " style="border-radius: 20px;" id="phone" name="phone" placeholder="你的手机号">
					</div>

			</div>

			<div class="form-group text-center ">
				<div class=" col-lg-offset-1  col-md-9 col-lg-7">
					<button type="submit" style=" width: 110px;   height: 40px;  font-size: 20px; border-radius: 20px; color: darkgoldenrod;" class="btn btn-default">进入</button>
				</div>
			</div>
			</form>
		</div>
	</body>
	<script type="application/javascript">
		var box = document.getElementById("mybox");
		var aa = -110;
	
		function piaoyi() {
			box.style.left = aa + "%";
			aa = aa + 5;

			if(aa >=5) {
				clearInterval(ds);
				//im2.style.left=79+"%";
			}

		}

		var ds = setInterval("piaoyi()", 100);
	</script>
</html>