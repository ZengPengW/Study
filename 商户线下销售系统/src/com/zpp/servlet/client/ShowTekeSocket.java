package com.zpp.servlet.client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.zpp.domain.Order;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;

/**
 * Servlet implementation class ShowTekeSocket
 */
@ServerEndpoint("/ShowTekeSocket/{uid}")
public class ShowTekeSocket  {
	 //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;
	
	  //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    //private static CopyOnWriteArraySet<ShowTekeSocket> webSocketSet=new CopyOnWriteArraySet<ShowTekeSocket>();
	 private static ConcurrentHashMap<String, ShowTekeSocket> webSocketSet = new ConcurrentHashMap<String, ShowTekeSocket>();
	//private static IdentityHashMap<String, ShowTekeSocket>webSocketSet=new IdentityHashMap<String ,ShowTekeSocket>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private static String uid="";
    
    public  String getSUid() {
		return uid;
	}
    public static ConcurrentHashMap<String, ShowTekeSocket> getWebsocket () {
		return webSocketSet;
	}
    //连接成功执行方法
    @OnOpen
    public void onOpen(@PathParam("uid") String param, Session session, EndpointConfig config){
    	
    	//System.out.println(param);
    	this.session=session;
    	//System.out.println(session.getPathParameters());
    	//System.out.println(session.getQueryString());
    	//System.out.println(session.getRequestURI().toString());	
    	uid=param;
    	webSocketSet.put(param, this);
    	addOnlineCount();
    //	System.out.println("新连接 人数："+onlineCount+"uid"+param);
    }
    
    //关闭方法
    @OnClose
    public void OnClose(){
    //	webSocketSet.remove(this);
    	
    //	subOnlineCount();
//    	
//    	
//			if(!uid.equals("")){
//				webSocketSet.remove(uid);
//				System.out.println("关闭连接  人数：1关闭id"+uid);
//				subOnlineCount();
//			}else {
//				System.out.println("没关");
//			}
					
    }
    
  private static PayService payService=new PayServiceImpl();
    //收到消息后执行
    @OnMessage
    public void onMessage(String message,Session session){
    	if(message.contains("close")){
    		String tempuid=message.substring(5);
    		webSocketSet.remove(tempuid);
    	//	System.out.println("关闭连接  人数：1关闭id"+tempuid);
    		return;
    		
    	}
    	//System.out.println("来自客户端消息 :"+message);
//    	if(message.contains("sid:")) {
//    		User user=CookiesUtils.getUser(message.substring(4));
//    		this.uid=user.getId();
//    	}else {
    		int curruid=0;
    		//发回数据
    		
    			//send(oid+"已取货"+ud);
    			//send(oid+"已备货"+ud);
    			
    			int oid=Integer.parseInt(message.substring(0,message.indexOf("已")));
    			curruid=Integer.parseInt(message.substring(message.indexOf("货")+1));
    			try {
					Order order =payService.getOrderByOid(curruid, oid);
					//System.out.println(oid+" "+curruid);//注释
	    			if(message.contains("取")){
	    				message="del"+order.getGid();	
	    			}else {
	    				message=order.getGid();	
					}
					
	    			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			
    		
//    		 for(ShowTekeSocket item: webSocketSet){
//    			 	if(item.uid==curruid){
//    			 		try {
//							item.sendMessage(message);
//						} catch (IOException e) {						
//							e.printStackTrace();
//							continue;
//						}
//    			 	}
//    	        }
 //   		System.out.println("当前UID "+curruid);
    		String temp=null;
   		 for(String item: webSocketSet.keySet()){
   			 temp=item.substring(0,item.indexOf("a"));
   			
		 	if(temp.equals(String.valueOf(curruid))){
		 	//	 System.out.println(temp+" "+curruid+" "+message);
		 		try {
					webSocketSet.get(item).sendMessage(message);
				} catch (IOException e) {						
					e.printStackTrace();
					continue;
				}
		 	}
        }	
        	
    	}
    	
    
    
    //发生错误时候
    @OnError
    public void OnError(Session session, Throwable error){
    //	System.out.println("发生错误");
        error.printStackTrace();
    }
    
    
    
    public void sendMessage(String message) throws IOException{
    	//System.out.println(message);
    	         this.session.getAsyncRemote().sendText(message);
    	         //this.session.getAsyncRemote().sendText(message);
    	     }
    	 
    	     public static synchronized int getOnlineCount() {
    	         return onlineCount;
    	     }
    	 
    	     public static synchronized void addOnlineCount() {
    	         ShowTekeSocket.onlineCount++;
    	     }
    	 
    	     public static synchronized void subOnlineCount() {
    	         ShowTekeSocket.onlineCount--;
    	     }
}
