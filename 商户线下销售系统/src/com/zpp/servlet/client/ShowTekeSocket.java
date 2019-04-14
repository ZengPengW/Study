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
	 //��̬������������¼��ǰ������������Ӧ�ð�����Ƴ��̰߳�ȫ�ġ�
	private static int onlineCount = 0;
	
	  //concurrent�����̰߳�ȫSet���������ÿ���ͻ��˶�Ӧ��MyWebSocket������Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
    //private static CopyOnWriteArraySet<ShowTekeSocket> webSocketSet=new CopyOnWriteArraySet<ShowTekeSocket>();
	 private static ConcurrentHashMap<String, ShowTekeSocket> webSocketSet = new ConcurrentHashMap<String, ShowTekeSocket>();
	//private static IdentityHashMap<String, ShowTekeSocket>webSocketSet=new IdentityHashMap<String ,ShowTekeSocket>();
    //��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
    private Session session;
    private static String uid="";
    
    public  String getSUid() {
		return uid;
	}
    public static ConcurrentHashMap<String, ShowTekeSocket> getWebsocket () {
		return webSocketSet;
	}
    //���ӳɹ�ִ�з���
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
    //	System.out.println("������ ������"+onlineCount+"uid"+param);
    }
    
    //�رշ���
    @OnClose
    public void OnClose(){
    //	webSocketSet.remove(this);
    	
    //	subOnlineCount();
//    	
//    	
//			if(!uid.equals("")){
//				webSocketSet.remove(uid);
//				System.out.println("�ر�����  ������1�ر�id"+uid);
//				subOnlineCount();
//			}else {
//				System.out.println("û��");
//			}
					
    }
    
  private static PayService payService=new PayServiceImpl();
    //�յ���Ϣ��ִ��
    @OnMessage
    public void onMessage(String message,Session session){
    	if(message.contains("close")){
    		String tempuid=message.substring(5);
    		webSocketSet.remove(tempuid);
    	//	System.out.println("�ر�����  ������1�ر�id"+tempuid);
    		return;
    		
    	}
    	//System.out.println("���Կͻ�����Ϣ :"+message);
//    	if(message.contains("sid:")) {
//    		User user=CookiesUtils.getUser(message.substring(4));
//    		this.uid=user.getId();
//    	}else {
    		int curruid=0;
    		//��������
    		
    			//send(oid+"��ȡ��"+ud);
    			//send(oid+"�ѱ���"+ud);
    			
    			int oid=Integer.parseInt(message.substring(0,message.indexOf("��")));
    			curruid=Integer.parseInt(message.substring(message.indexOf("��")+1));
    			try {
					Order order =payService.getOrderByOid(curruid, oid);
					//System.out.println(oid+" "+curruid);//ע��
	    			if(message.contains("ȡ")){
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
 //   		System.out.println("��ǰUID "+curruid);
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
    	
    
    
    //��������ʱ��
    @OnError
    public void OnError(Session session, Throwable error){
    //	System.out.println("��������");
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
