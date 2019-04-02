function deleteProdcut(item){
	
	sweetAlert({
		  title: "删除",
		  text: "删除后,上架商品也会同步删除!\n是否继续删除？",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#CD3333",
		  cancelButtonText:"取消",
		  confirmButtonText: "确认删除!",
		  closeOnConfirm: true
		}, function(){
			var pid=$(item).val();
			$.post("/Zpp/DeleteProduct",{pid:pid},function(data){
				if(data=="true"){
					$(item).parent().parent().parent().remove();
				}else{
					alert("未知异常,删除失败");
				}
			});
		});
	
//	var flag=confirm("删除后,上架商品也会同步删除!\n是否继续删除？");
//		if(flag){
//			var pid=$(item).val();
//			$.post("/Zpp/DeleteProduct",{pid:pid},function(data){
//				if(data=="true"){
//					$(item).parent().parent().parent().remove();
//				}else{
//					alert("未知异常,删除失败");
//				}
//			});
//		}
}

function alterProdcut(item){
	
	sweetAlert({
		  title: "修改",
		  text: "修改后,商品需要重新发布上架! \n 是否需要修改？",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#63B8FF",
		  cancelButtonText:"取消",
		  confirmButtonText: "继续修改!",
		  closeOnConfirm: true
		}, function(){
			var pid=$(item).val();
			window.location.href="AlterProduct?pid="+pid;
		});
//	var flag=confirm("修改后,商品需要重新发布上架! \n 是否需要修改？");
//	if(flag){
//		var pid=$(item).val();
//		window.location.href="AlterProduct?pid="+pid;
//	}
}

function publish(item){
	
	sweetAlert({
		  title: "发布",
		  text: "是否确认上架?",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#66CD00",
		  cancelButtonText:"取消",
		  confirmButtonText: "确认!",
		  //timer:1000,
		  closeOnConfirm: false
		}, function(){
			var pid=$(item).val();
			$.post("/Zpp/PublishServlet",{pid:pid},function(data){
				if(data=="true"){
					swal("上架成功!");
					list.push(pid);
					$(item).text("已发布");
					$(item).removeClass("btn-success").addClass("btn-warning");
					$(item).prop("disabled","disabled");
				}else{
					swal("上架失败!");
				}
			})
		//	swal("上架成功");
			
		});
	
}

function cancel(item) {
	sweetAlert({
		  title: "下架",
		  text: "是否确认下架?",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#CD3333",
		  cancelButtonText:"取消",
		  confirmButtonText: "确认!",
		  //timer:1000,
		  closeOnConfirm: true
		}, function(){
			var pid=$(item).val();
			$.post("/Zpp/OnSaleDelete",{pid:pid},function(data){
				if(data=="true"){
					$(item).parent().parent().parent().remove();
					
				}else{
					swal("下架失败! ");
				}
			})
		
			
		});
	
}
