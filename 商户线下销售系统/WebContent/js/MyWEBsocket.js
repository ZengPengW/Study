
var lockReconnect = false; 
var upcount=0;
var websocket = null;
var domain2=window.location.host;
var wsurl="ws://"+domain2+loc+"/ShowTekeSocket/"+uid;



function createWebSocket(url) {
    try{
        if('WebSocket' in window){
        	websocket = new WebSocket(url);
        }else if('MozWebSocket' in window){  
        	websocket = new MozWebSocket(url);
        }else{
            layui.use(['layer'],function(){
              var layer = layui.layer;
              layer.alert("您的浏览器不支持websocket协议,建议使用新版谷歌、火狐等浏览器，请勿使用IE10以下浏览器，360浏览器请使用极速模式，不要使用兼容模式！"); 
            });
        }
        initEventHandle();
    }catch(e){
        reconnect(url);
        console.log(e);
    }     
}


function reconnect(url) {
    if(lockReconnect) return;
    lockReconnect = true;
    setTimeout(function () {     //没连接上会一直重连，设置延迟避免请求过多
        createWebSocket(url);
        lockReconnect = false;
    }, 2000);
}



var heartCheck = {
	    timeout: 60000,        //1分钟发一次心跳
	    timeoutObj: null,
	    serverTimeoutObj: null,
	    reset: function(){
	        clearTimeout(this.timeoutObj);
	        clearTimeout(this.serverTimeoutObj);
	        return this;
	    },
	    start: function(){
	        var self = this;
	        this.timeoutObj = setTimeout(function(){
	            //这里发送一个心跳，后端收到后，返回一个心跳消息，
	            //onmessage拿到返回的心跳就说明连接正常
	        	websocket.send("ping");
	          //  console.log("ping!")
	            self.serverTimeoutObj = setTimeout(function(){//如果超过一定时间还没重置，说明后端主动断开了
	            	send("close"+uid);
	            	websocket.close();     //如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect 会触发onclose导致重连两次
	            }, self.timeout)
	        }, this.timeout)
	    }
	}





//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
//	alert("ws://"+domain2+loc+"/ShowTekeSocket/"+uid);
    websocket = new WebSocket(wsurl);
}
else {
    alert('当前浏览器 Not support websocket')
}
   //连接发生错误的回调方法
websocket.onerror = function () {
	reconnect(wsurl);
};


//连接成功建立的回调方法
websocket.onopen = function () {
	  heartCheck.reset().start();    
}

//连接关闭的回调方法
websocket.onclose = function () {
   // setMessageInnerHTML("WebSocket连接关闭");
	 reconnect(wsurl);
}


//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
	
	closeWebSocket();
}


//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
   // document.getElementById('message').innerHTML += innerHTML + '<br/>';
	alert(innerHTML);
}

//关闭WebSocket连接
function closeWebSocket() {
	send("close"+uid);
    websocket.close();
}

//发送消息
function send(mess) {
   // var message = document.getElementById('text').value;
  
    websocket.send(mess);
}



//接收到消息的回调方法
websocket.onmessage = function (event) {
	 heartCheck.reset().start();//拿到任何消息都说明当前连接是正常的
	 
   var backcount=event.data;
   if(backcount=='pong')return;
  // alert(backcount);
	if(zpmess=="1"){		
	if(backcount.length==6||backcount.indexOf("del")!=-1){
		return;
	}
	 show=setInterval("changeH('up')",2);
	 playSound();
		upcount++;
		
		if(upcount>=11){
			location.href="${pageContext.request.contextPath }/AllOrderCheck?orderClass=5YWo6YOo&currentPage=1";
			return;
		}
		if(backcount.length>18){
		if($("#orderlist").children("[name='ding']").length>=12)$("#orderlist").children("[name='ding']:last-child").remove();
			
		$.each($.parseJSON(event.data), function(index,c) {
		var	infomessage="<div class='col-lg-4 col-md-6 col-sm-6' name='ding'><div class='panel panel-default'><div class='panel-body'>用户："+c.username+"<span class='col-sm-offset-5' style='right: 10%;position: absolute;'>实付款:<strong  ><font color='red'  >￥"+c.money.toFixed(2)+"</font></strong></span></div><div class='panel-footer'><h5>用户名："+c.username+"</h5><h5>手机:"+c.phone+"</h5><h5>取货编号："+c.gid+"</h5><h5>创建时间：</h5><h6><small>"+c.time+"</small></h6><h6>订单状态:";

if(c.isteke=="0"){
infomessage=infomessage+"<font color='#00E067'>";

if(c.statu=="0")infomessage+="待确认···";
if(c.statu=="1")infomessage+="待备货···";
if(c.statu=="2")infomessage+="待取货···";
infomessage=infomessage+"</font></h6>";

}
if(c.isteke=="1"){
	infomessage=infomessage+"<font color='#FF4A07'>";
	infomessage+="已取货···";
	
	infomessage=infomessage+"</font></h6>";

	}
if(c.isteke=="1"){

infomessage=infomessage+"<button class='btn btn-primary disabled'>已确认</button><button class='btn btn-success disabled'>已备货</button><button class='btn btn-warning disabled'>已取货</button>";

}

if(c.isteke=="0"){
	
	if(c.statu=="0"){
	infomessage=infomessage+"<button class='btn btn-primary ' oid='"+c.oid+"' isOption='1' onclick='myoption(this)'>确认订单</button><button class='btn btn-success ' oid='"+c.oid+"' isOption='2' onclick='myoption(this)'>备货完毕</button>";

	}

	if(c.statu=="1"){
	infomessage=infomessage+"<button class='btn btn-primary disabled'>已确认</button><button class='btn btn-success ' oid='"+c.oid+"' isOption='2' onclick='myoption(this)'>备货完毕</button>";

	}	
	
	if(c.statu=="2"){
		
		infomessage=infomessage+"<button class='btn btn-primary disabled'>已确认</button><button class='btn btn-success disabled'>已备货</button>";
	}
	infomessage=infomessage+"<button class='btn btn-warning' oid='"+c.oid+"' isOption='3' onclick='myoption(this)'>取货</button>";

	
}

infomessage=infomessage+"<button class='btn btn-info' oid='"+c.oid+"' onclick='checkOrder(this)'>查看订单</button></div></div></div>";


$("#orderlist").prepend(infomessage);
			
		});
		}
		
		
		
	}else if(zpmess=="2"&&(backcount.length==6||backcount.indexOf("del")!=-1)){
	if(backcount.indexOf("del")!=-1){
		var num=backcount.substring(3);
		$("#"+num).remove();
		
	}else{
	var nob=Math.floor(Math.random()*4+1);
	if(nob==1){
	$("#tekeid").append("<h1 id='"+backcount+"'><span class='label label-success'>"+backcount+"</span></h1>");	
	}else if(nob==2){
	$("#tekeid").append("<h1 id='"+backcount+"'><span class='label label-warning'>"+backcount+"</span></h1>");		
	}else if(nob==3){
		$("#tekeid").append("<h1 id='"+backcount+"'><span class='label label-danger'>"+backcount+"</span></h1>");	
	}else if(nob==4){
	$("#tekeid").append("<h1 id='"+backcount+"'><span class='label label-info'>"+backcount+"</span></h1>");		
	}else if(nob==5){
		$("#tekeid").append("<h1 id='"+backcount+"'><span class='label label-primary'>"+backcount+"</span></h1>");	
	}
		
	}
		
	}else if(zpmess=="3"){
		if(backcount.length>=18){
		
			 show=setInterval("changeH('up')",2);
			 playSound();
		}	
	}
}




function tips_pop(){
	  var MsgPop=document.getElementById("winpop");
	  var popH=parseInt(MsgPop.style.height);//将对象的高度转化为数字
	   if (popH==0){
		 
	  show=setInterval("changeH('up')",2);
	   }
	  else { 
	   hide=setInterval("changeH('down')",2);
	  }
	}
	function changeH(str) {
	 var MsgPop=document.getElementById("winpop");
	 var popH=parseInt(MsgPop.style.height);
	 if(str=="up"){
	  MsgPop.style.display="block";//显示隐藏的窗口
	  if (popH<=100){
	  MsgPop.style.height=(popH+4).toString()+"px";
	  }
	  else{  
	  clearInterval(show);
	  }
	 }
	 if(str=="down"){ 
	  if (popH>=4){  
	  MsgPop.style.height=(popH-4).toString()+"px";
	  }
	  else{ 
	  clearInterval(hide);   
	  MsgPop.style.display="none";  //隐藏DIV
	  }
	 }
	}
	window.onload=function(){//加载
	document.getElementById('winpop').style.height='0px';
	// setTimeout("tips_pop()",800);//3秒后调用tips_pop()这个函数
	}
	function myclose(){
		hide=setInterval("changeH('down')",2);
	}
