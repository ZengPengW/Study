<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登陆</title>
<link href="../css/bootstrap.css" rel="stylesheet"/>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/stytle.js"></script>
<link href="../css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	
<div id="bgcolor" >
	
<div class="container" >
	
<div class="row">
	<div class="col-md-8 hidden-xs hidden-sm">
		<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
	</div>
<div class="col-md-4" id="lgform">
	<br />
	<center><h3 style=" color: red; "><strong>账户登陆</strong></h3></center>
<form role="form" class="form-horizontal">
 <div class="form-group">
							<label for="username" class="col-sm-3 control-label">用户名</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="username"
									placeholder="请输入用户名/邮箱">
							</div>
	</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="inputPassword3"
									placeholder="请输入密码">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-3 control-label">验证码</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="inputPassword3"
									placeholder="请输入验证码">
							</div>
							<div class="col-sm-3">
								<img src="./image/captcha.jhtml" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-10">
								<div class="checkbox">
									<label> <input type="checkbox" name="auto_login"> 自动登录
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-2 col-sm-10 ">
     							 <button type="submit" style=" width: 80%;   height: 40px; background-color: red; font-size: 20px; color: white;"  class="btn btn-default">登 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;陆</button>
    						</div>
						</div>
				
						<a href="" id="wjmm" style="color: #666666;">忘记密码</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="register.html"  style="color: red;" ><img src="../imgs/icon/zc.svg"  style="width: 20px; height: 20px;"/>立即注册</a>
</form>
		
	</div>
</div>
	
	
</div>
</div>


</body>
</html>