<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta HTTP-EQUIV="Expires" CONTENT="0">
<title>支付状态</title>
</head>

<script type="text/javascript">
		function countDown(){
		setTimeout("location.href='${pageContext.request.contextPath }/MyOrderCheck'",2000);
             
	}
	
		countDown();
</script>
<style>
.container{text-align:center;}
.container div{margin:0;padding:0;display:inline;}
</style>
<body>
<div class="container" >
	
<div   >
			<c:if test="${status==0}">
						<img alt="错误" src="${pageContext.request.contextPath }/imgs/icon/fail.svg" class="img-thumbnail" >
			<br>
			
			<span class="alert alert-danger" ><font color="red">${message}</font></span>
			
		
			</c:if>
			<c:if test="${status==1}">
			<img alt="成功" src="${pageContext.request.contextPath }/imgs/icon/success.svg" class="img-thumbnail">
			<br>
			<span class="alert alert-success"><font color="#11A3FC">${message}</font></span>
			</c:if>	
			</div>		
		
		

	</div>

		

	</body>

</html>