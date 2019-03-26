<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>重设密码</title>
	<link href="../css/bootstrap.css" rel="stylesheet"/>
	<script src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/jquery-1.11.3.min.js" ></script>
	 <script  src="../js/MyValidate.js"></script>
	<script type="text/javascript" src="../js/regutils.js" ></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js" ></script>
      <script type="text/javascript" src="../js/messages_zh.js" ></script>
	</head>
	<style>
		.asy{
			text-decoration: none;
			color: #666666;
			}
		label.error{
				background:url(../imgs/icon/unchecked.gif) no-repeat 10px 3px;
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
				<a class="asy" href="index.jsp"><img src="../imgs/icon/shouye.svg" style="width: 30px; height: 30px;" />首页</a>
			</div>
			
		</div>
		<div class="row">
			<div class="col-lg-1 col-md-1">
				
			</div>
			<div class="col-md-5 ">
				<img src="../imgs/icon/findPW.svg" width="8%" height="8%" />
				<strong style="color:#666666; font-size: 25px;">找回密码</strong>
			</div>
			<div class="col-md-2 col-xs-offset-4 col-lg-offset-0 col-sm-offset-0 col-md-offset-0">
			
			</div>
		
		</div>
	
		<hr width="100%" style="height: 2px;" color="#808080" />
<br />
<div class="row" style="width: 80%;">
	
<div class="container" >
	<form role="form" class="form-horizontal" method="post" action="/Zpp/UpdataPassWordServlet" id="changepassword">
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
