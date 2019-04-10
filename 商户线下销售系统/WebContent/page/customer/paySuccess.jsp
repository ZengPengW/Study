<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>支付状态</title>
</head>
<script type="text/javascript">
		function countDown(){
		setTimeout("location.href='${pageContext.request.contextPath }/MyOrderCheck'",2000);
             
	}
	countDown();

	</script>
<body>
<div class="container">
	
<div class="row col-md-6 col-lg-offset-3 col-md-offset-3  "  align="center" style="font-weight: 300;">
			<c:if test="${status==0}">
						<img alt="错误" src="${pageContext.request.contextPath }/imgs/icon/fail.svg" class="img-thumbnail" >
			
			
			<h2 class="alert alert-danger" ><font color="red">${message}</font></h2>
			
		
			</c:if>
			<c:if test="${status==1}">
			<img alt="成功" src="${pageContext.request.contextPath }/imgs/icon/success.svg" class="img-thumbnail">
			<h2 class="alert alert-success"><font color="#11A3FC">${message}</font></h2>
			</c:if>	
			</div>		
		
		

	</div>

		

	</body>

</html>