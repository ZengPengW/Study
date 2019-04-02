$(function(){
	$("#search").click(function(){
		var likeName=$("#likeName").val();
		
		$.post("/Zpp/ProductNameSearch",{likeName:likeName},function(data,status){
			
				$("#limt").remove();
				$("[name='productlist']").remove();
				$(data).each(function(index,c){
					var str="<tr class='text-center' name='productlist'>"
						+"<td><img width='180px' height='180px' class='img-rounded' src="+c.productImg+" /></td>"
						+"<td><h3><strong>"+c.productClass+"</strong></h3></td>"
						+"<td><h3><strong>"+c.productName+"</strong></h3></td>"
						+"<td><h3><strong>"+c.productCount.toFixed(2)+"</strong></h3></td>"
						+"<td><h3><strong>"+c.price.toFixed(2)+"</strong></h3></td>"
						+"<td><textarea cols='20' rows='5' readonly class='form-control'>"+c.productMessage+"</textarea></td>";
					
					if(Mycontains(list,c.pid)){
						str=str+"<td class='text-center'><div class='btn-group' style='margin: 10% auto auto auto;'><button type='button' class='btn btn-info' value='"+c.pid+"' onclick='alterProdcut(this)'>修改</button><button type='button' class='btn btn-danger' value='"+c.pid+"'  onclick='deleteProdcut(this)'>删除</button><button type='button' class='btn btn-warning' value='"+c.pid+"' disabled='disabled'>已发布</button></div></td></tr>";
					}else{
						str=str+"<td class='text-center'><div class='btn-group' style='margin: 10% auto auto auto;'><button type='button' class='btn btn-info' value='"+c.pid+"' onclick='alterProdcut(this)'>修改</button><button type='button' class='btn btn-danger' value='"+c.pid+"'  onclick='deleteProdcut(this)'>删除</button><button type='button' class='btn btn-success' value='"+c.pid+"' onclick='publish(this)' >发布</button></div></td></tr>";
					}
					$("#ProductTable").append(str);
							
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
							+"<td class='text-center'><div class='btn-group' style='margin: 10% auto auto auto;'><button type='button' class='btn btn-danger' value='"+c.pid+"' onclick='cancel(this)'>下架</button></div></td></tr>");
				});
			
		},"json");
	});


});

function Mycontains(arr,obj){
	var i = arr.length;
    while (i--) {
        if (arr[i] == obj) {
            return true;
        }
    }
    return false;
}
