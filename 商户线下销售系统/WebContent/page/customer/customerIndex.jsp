<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>-->
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>欢迎光临</title>
		<link href="../../css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="../../js/jquery-1.11.3.min.js"></script>
		<script src="../../js/bootstrap.min.js"></script>

	</head>
	<style>
		body {
			background-image: url(../../imgs/icon/bg.jpg);
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
	</style>
	<script type="application/javascript">
//		$(function() {
//			var maxheight = $(window).height();
//			var h = document.getElementById("#mybox").offsetTop;
//			var t = setInterval(function() {
//
//				document.getElementById("#mybox").offsetTop = h;
//				h--;
//				if(h == matchMedia / 2 + 90) {
//					clearInterval(t);
//				}
//			}, 1000)
//
//		});
	</script>

	<body>
		<div class="container">
			<div style="margin-top: 50%;" id="mybox">
<div class="row col-md-offset-3 col-md-3">

				<form class="form-horizontal " method="post" action="">

					<div class="form-group text-center ">

						<input type="text" class="form-control input-group-addon " style="border-radius: 20px;" id="inputEmail3" placeholder="输入你的姓名">
					</div>
					<div class="form-group text-center ">

						<input type="text" class="form-control input-group-addon " style="border-radius: 20px;" id="inputPassword3" placeholder="你的手机号">
					</div>
					
						
						
					
			</div>
<div class="form-group text-center ">
						
						<button type="submit" style=" width: 110px;   height: 40px;  font-size: 20px; border-radius: 20px; color: darkgoldenrod;" class="btn btn-default">进入</button>
						
					
					</div>
			</div>
			
			</form>

		</div>
		</div>
	</body>

</html>