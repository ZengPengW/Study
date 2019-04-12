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
	 //��̬������������¼��ǰ������������Ӧ�ð�����Ƴ��̰߳�ȫ�ġ�
	private static int onlineCount = 0;
	
	  //concurrent�����̰߳�ȫSet���������ÿ���ͻ��˶�Ӧ��MyWebSocket������Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
    private static CopyOnWriteArraySet<ShowTekeSocket> webSocketSet=new CopyOnWriteArraySet<ShowTekeSocket>();
    //��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
    private Session session;
    private int uid;
    //���ӳɹ�ִ�з���
    @OnOpen
    public void onOpen(Session session){
    	this.session=session;
    	webSocketSet.add(this);
    	addOnlineCount();
    	System.out.println("������ ������"+onlineCount);
    }
    
    //�رշ���
    @OnClose
    public void OnClose(){
    	webSocketSet.remove(this);
    	
    	subOnlineCount();
    	System.out.println("�ر�����  ������"+onlineCount);
    }
    
  private static PayService payService=new PayServiceImpl();
    //�յ���Ϣ��ִ��
    @OnMessage
    public void onMessage(String message,Session session){
    	
    	System.out.println("���Կͻ�����Ϣ :"+message);
    	if(message.contains("sid:")) {
    		User user=CookiesUtils.getUser(message.substring(4));
    		this.uid=user.getId();
    	}else {
    		//��������
    		if("1".equals(message)) {//��Ʒ�����Ƿ��и���
    			
    			try {
				long count=payService.getAllOrderCountByUid(uid,"ȫ��");
				this.sendMessage(""+count);	
				} catch (SQLException | IOException e) {				
					e.printStackTrace();
					try {
						this.sendMessage("-1");
					} catch (IOException e1) {					
						e1.printStackTrace();
					}	
				}
    		}else if ("2".equals(message)) {//ȡ����������
				
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
					long count=payService.getAllOrderCountByUid(uid,"ȫ��");
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
    
    //��������ʱ��
    @OnError
    public void OnError(Session session, Throwable error){
    	System.out.println("��������");
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
