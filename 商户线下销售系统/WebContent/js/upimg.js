$(function(){
	$("#productImg").change(function(){
	 	var filePath=$(this).val();
	 	var fileformat=filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
		if(!fileformat.match(/.png|.jpg|jpeg|.gif/)){
			alert('上传错误,文件格式必须为：png/jpg/jpeg/gif');
        	return; 
		}
		var src= window.URL.createObjectURL(this.files[0]); 
		$("#spimg").prop("src",src);
		
	});
	
});
