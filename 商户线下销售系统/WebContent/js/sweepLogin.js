var uuid=new Date().getTime();
var lockReconnect = false; 
var upcount=0;
var websocket = null;
var domain2=window.location.host;
var wsurl="ws://"+domain2+loc+"/ShowTekeSocket/"+uuid;



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
	            	send("close"+uuid);
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
	send("close"+uuid);
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
  if(backcount.length>10){
  	send("close"+uuid);
    websocket.close();
  	$("#loginMess").prop("color","green");
  	$("#loginMess").text("登录成功正在跳转。。。");
  	$("#loginMess").show();
//  	alert("location.href='"+loc+"/SweepLoginCheck?ssid="+backcount+"'");
  	setTimeout("location.href='"+loc+"/SweepLoginCheck?ssid="+backcount+"'",1000);
  	
  }

}




$(function(){
	$("#smdl").click(function(){
		$("#loginFrom").hide();
		$("#smlogin").show();
		$("#sm1").prop("color","red");
		$("#zh1").prop("color","black");
	});
	
	$("#zhdl").click(function(){
		$("#smlogin").hide();
		$("#loginFrom").show();
		$("#zh1").prop("color","red");
		$("#sm1").prop("color","black");
		
	});
		
	});
	
$(function(){
var domain=window.location.host;

$("#sweepimg").attr("src","/Zpp/SweepLoginQR?addr="+domain+"&uuid="+uuid);
	
});	