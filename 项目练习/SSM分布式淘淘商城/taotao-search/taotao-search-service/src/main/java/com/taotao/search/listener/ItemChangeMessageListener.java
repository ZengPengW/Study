package com.taotao.search.listener;


import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.SearchService;
/**
 * 接收消息的监听器
 * @author zp
 *
 */
public class ItemChangeMessageListener implements MessageListener {

	@Autowired
	private SearchService searchService;
	
	@Override
	public void onMessage(Message message) {
		
		//判断消息类型
		if(message instanceof TextMessage) {
			String ms;
			try {
				ms = ((TextMessage) message).getText();
				
				String[] idss =null;
				if (ms.indexOf("delete:")!=-1) {
					
					int index=ms.indexOf(":")+1;
					ms=ms.substring(index);
					idss = ms.split(",");
					searchService.deleteItemByIds(idss);
					
				}else {
					idss = ms.split(",");
				}
				
				
			TaotaoResult taotaoResult = searchService.updateItemByIds(idss);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}

	}

}
