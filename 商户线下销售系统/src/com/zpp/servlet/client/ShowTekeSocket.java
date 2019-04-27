package com.zpp.servlet.client;

import java.io.IOException;

import java.util.concurrent.ConcurrentHashMap;


import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


import com.zpp.service.PayService;
import com.zpp.service.PayServiceImpl;

/**
 * Servlet implementation class ShowTekeSocket
 */
@ServerEndpoint("/ShowTekeSocket/{uid}")
public class ShowTekeSocket {
	// ��̬������������¼��ǰ������������Ӧ�ð�����Ƴ��̰߳�ȫ�ġ�
	private static int onlineCount = 0;

	// concurrent�����̰߳�ȫSet���������ÿ���ͻ��˶�Ӧ��MyWebSocket������Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
	// private static CopyOnWriteArraySet<ShowTekeSocket> webSocketSet=new
	// CopyOnWriteArraySet<ShowTekeSocket>();
	private static ConcurrentHashMap<String, ShowTekeSocket> webSocketSet = new ConcurrentHashMap<String, ShowTekeSocket>();
	// private static IdentityHashMap<String, ShowTekeSocket>webSocketSet=new
	// IdentityHashMap<String ,ShowTekeSocket>();
	// ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
	private Session session;
	private static String uid = "";
	public Session getSession() {
		return this.session;
	}
	public String getSUid() {
		return uid;
	}

	public static ConcurrentHashMap<String, ShowTekeSocket> getWebsocket() {
		return webSocketSet;
	}

	// ���ӳɹ�ִ�з���
	@OnOpen
	public void onOpen(@PathParam("uid") String param, Session session, EndpointConfig config) {

		// System.out.println(param);
		this.session = session;
		// System.out.println(session.getPathParameters());
		// System.out.println(session.getQueryString());
		// System.out.println(session.getRequestURI().toString());
		uid = param;
		webSocketSet.put(param, this);
	//	addOnlineCount();
	//	System.out.println("������ ������" + onlineCount + "uid" + param);
	}

	// �رշ���
	@OnClose
	public void OnClose() {
		// webSocketSet.remove(this);

		// subOnlineCount();
//    	
//    	
//			if(!uid.equals("")){
//				webSocketSet.remove(uid);
//				System.out.println("�ر�����  ������1�ر�id"+uid);
//				subOnlineCount();
//			}else {
//				System.out.println("û��");
//			}
//		System.out.println("ʣ������" + webSocketSet.size());
	}

	private static PayService payService = new PayServiceImpl();

	// �յ���Ϣ��ִ��
	@OnMessage
	public void onMessage(String message, Session session) {
		if ("ping".equals(message)) {
			this.session.getAsyncRemote().sendText("pong");
		} else {
			if (message.contains("close")) {
				String tempuid = message.substring(5);
				try {
					webSocketSet.get(tempuid).session.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
				webSocketSet.remove(tempuid);
			//	System.out.println("scʣ������" + webSocketSet.size());
				return;

			}

		}
	}

	// ��������ʱ��
	@OnError
	public void OnError(Session session, Throwable error) {
		System.out.println("socket��������");
		// error.printStackTrace();
	}

	public void sendMessage(String message) throws IOException {
		// System.out.println(message);
		if(this.session.isOpen()==true)this.session.getAsyncRemote().sendText(message);
		// this.session.getAsyncRemote().sendText(message);
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
