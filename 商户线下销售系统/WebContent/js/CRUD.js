function deleteProdcut(item){
	var flag=confirm("删除后,上架商品也会同步删除!\n是否继续删除？");
		if(flag){
			var pid=$(item).val();
			$.post("/Zpp/DeleteProduct",{pid:pid},function(data){
				if(data=="true"){
					$(item).parent().parent().parent().remove();
				}else{
					alert("未知异常,删除失败");
				}
			});
		}
}

function alterProdcut(item){
	var flag=confirm("修改后,商品需要重新发布上架! \n 是否需要修改？");
	if(flag){
		var pid=$(item).val();
		window.location.href="AlterProduct?pid="+pid;
	}
}