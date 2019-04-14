<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>重设密码</title>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js" ></script>
	<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet"/>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>

	 <script  src="${pageContext.request.contextPath }/js/MyValidate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/regutils.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js" ></script>
      <script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js" ></script>
	</head>
	<style>
		.asy{
			text-decoration: none;
			color: #666666;
			}
		label.error{
				background:url(${pageContext.request.contextPath }/imgs/icon/unchecked.gif) no-repeat 10px 3px;
				padding-left: 30px;
				font-family:georgia;
				font-size: 15px;
				font-style: normal;
				color: red;
			}	
	</style>
	<body>
		<div class="row">
		<div class="col-lg-1 col-md-1">
				
			</div>
			<div class="col-md-4">
				<a class="asy" href="index.jsp"><img src="${pageContext.request.contextPath }/imgs/icon/shouye.svg" style="width: 30px; height: 30px;" />首页</a>
			</div>
			
		</div>
		<div class="row">
			<div class="col-lg-1 col-md-1">
				
			</div>
			<div class="col-md-5 ">
				<img src="${pageContext.request.contextPath }/imgs/icon/findPW.svg" width="8%" height="8%" />
				<strong style="color:#666666; font-size: 25px;">找回密码</strong>
			</div>
			<div class="col-md-2 col-xs-offset-4 col-lg-offset-0 col-sm-offset-0 col-md-offset-0">
			
			</div>
		
		</div>
	
		<hr width="100%" style="height: 2px;" color="#808080" />
<br />
<div class="row" style="width: 80%;">
	
<div class="container" >
	<form role="form" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/AlterPassWordServlet" id="changepassword">
					<div class="form-group">
							<label for="password"   class="col-sm-3 control-label">新密码</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="password" name="password"
									placeholder="请输入新密码" >
									
							</div>
							
					</div>
						<div class="form-group">
							<label for="repassword"  class="col-sm-3 control-label">确认密码</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="repassword" name="repassword"
									placeholder="确认密码">
								<span style="color: red;"> </span>
							</div>
							
						</div>
					<div class="form-group">
							<div class="col-xs-offset-2 col-lg-offset-3 col-md-6 ">
     							 <button type="submit" style=" width: 80%;   height: 40px; background-color: red; font-size: 20px; color: white;"  class="btn btn-default">确认修改</button>
    						</div>
					</div>	
				
			</form>
</div>
</div>	
			
	
			</body>
</html>
