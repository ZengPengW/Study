package com.zpp.servlet.client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;


import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.zpp.domain.Order;
import com.zpp.domain.User;
import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;
import com.zpp.utils.CookiesUtils;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ShowTekeSocket
 */
@ServerEndpoint("/ShowTekeSocket")
public class ShowTekeSocket  {
	 //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;
	
	  //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<ShowTekeSocket> webSocketSet=new CopyOnWriteArraySet<ShowTekeSocket>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private int uid;
    //连接成功执行方法
    @OnOpen
    public void onOpen(Session session){
    	this.session=session;
    	webSocketSet.add(this);
    	addOnlineCount();
    	System.out.println("新连接 人数："+onlineCount);
    }
    
    //关闭方法
    @OnClose
    public void OnClose(){
    	webSocketSet.remove(this);
    	
    	subOnlineCount();
    	System.out.println("关闭连接  人数："+onlineCount);
    }
    
  private static PayService payService=new PayServiceImpl();
    //收到消息后执行
    @OnMessage
    public void onMessage(String message,Session session){
    	
    	System.out.println("来自客户端消息 :"+message);
    	if(message.contains("sid:")) {
    		User user=CookiesUtils.getUser(message.substring(4));
    		this.uid=user.getId();
    	}else {
    		//发回数据
    		if("1".equals(message)) {//商品个数是否有更新
    			
    			try {
				long count=payService.getAllOrderCountByUid(uid,"全部");
				this.sendMessage(""+count);	
				} catch (SQLException | IOException e) {				
					e.printStackTrace();
					try {
						this.sendMessage("-1");
					} catch (IOException e1) {					
						e1.printStackTrace();
					}	
				}
    		}else if ("2".equals(message)) {//取货人数更新
				
    			try {
    				List<Order> order=payService.getTeke(uid, 0, 2);
    				String json=JSONArray.fromObject(order).toString();
    				this.sendMessage(json);	
    				} catch (SQLException | IOException e) {				
    					e.printStackTrace();
    					try {
    						this.sendMessage("-1");
    					} catch (IOException e1) {					
    						e1.printStackTrace();
    					}	
    				}
    			
    			
			}else if ("3".equals(message)) {
				try {
					long count=payService.getAllOrderCountByUid(uid,"全部");
					this.sendMessage(""+count);	
					} catch (SQLException | IOException e) {				
						e.printStackTrace();
						try {
							this.sendMessage("-1");
						} catch (IOException e1) {					
							e1.printStackTrace();
						}	
					}
			}
    		
        	
    	}
    	System.out.println(uid);
    }
    
    //发生错误时候
    @OnError
    public void OnError(Session session, Throwable error){
    	System.out.println("发生错误");
        error.printStackTrace();
    }
    
    
    
    public void sendMessage(String message) throws IOException{
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
