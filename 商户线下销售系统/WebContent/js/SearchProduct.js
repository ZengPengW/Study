$(function(){
	$("#search").click(function(){
		var likeName=$("#likeName").val();
		
		$.post("/Zpp/ProductNameSearch",{likeName:likeName},function(data,status){
			
				$("#limt").remove();
				$("[name='productlist']").remove();
				$(data).each(function(index,c){
					$("#ProductTable").append("<tr class='text-center' name='productlist'>"
							+"<td><img width='180px' height='180px' class='img-rounded' src="+c.productImg+" /></td>"
							+"<td><h3><strong>"+c.productClass+"</strong></h3></td>"
							+"<td><h3><strong>"+c.productName+"</strong></h3></td>"
							+"<td><h3><strong>"+c.productCount.toFixed(2)+"</strong></h3></td>"
							+"<td><h3><strong>"+c.price.toFixed(2)+"</strong></h3></td>"
							+"<td><textarea cols='20' rows='5' readonly class='form-control'>"+c.productMessage+"</textarea></td>"
							+"<td class='text-center'><div class='btn-group' style='margin: 10% auto auto auto;'><button type='button' class='btn btn-info' value='"+c.pid+"'>修改</button><button type='button' class='btn btn-danger' value='"+c.pid+"'>删除</button><button type='button' class='btn btn-success' value='"+c.pid+"'>发布</button></div></td></tr>");
				});
			
		},"json");
	});
	


	$("#search2").click(function(){
		var likeName=$("#likeName").val();
		
		$.post("/Zpp/OnSaleProductSearch",{likeName:likeName},function(data,status){
			
				$("#limt").remove();
				$("[name='productlist']").remove();
				$(data).each(function(index,c){
					$("#ProductTable").append("<tr class='text-center' name='productlist'>"
							+"<td><img width='180px' height='180px' class='img-rounded' src="+c.productImg+" /></td>"
							+"<td><h3><strong>"+c.productClass+"</strong></h3></td>"
							+"<td><h3><strong>"+c.productName+"</strong></h3></td>"
							+"<td><h3><strong>"+c.productCount.toFixed(2)+"</strong></h3></td>"
							+"<td><h3><strong>"+c.price.toFixed(2)+"</strong></h3></td>"
							+"<td><textarea cols='20' rows='5' readonly class='form-control'>"+c.productMessage+"</textarea></td>"
							+"<td class='text-center'><div class='btn-group' style='margin: 10% auto auto auto;'><button type='button' class='btn btn-info' value='"+c.pid+"'>修改</button><button type='button' class='btn btn-danger' value='"+c.pid+"'>删除</button><button type='button' class='btn btn-success' value='"+c.pid+"'>发布</button></div></td></tr>");
				});
			
		},"json");
	});


});

