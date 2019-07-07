package com.taotao.listener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemDescService;
import com.taotao.service.ItemService;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;
/**
 * 监听器 
 * 生成静态页面
 * @author zp
 *
 */
public class ItemChangeGenHtmMessageListener implements MessageListener {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDescService itemDescService;
	
	@Autowired
	private FreeMarkerConfigurer freemarkerConfig;
	
	@Override
	public void onMessage(Message message) {
		//获取信息 商品id
		TextMessage textMessage=(TextMessage) message;
		String ms;
		try {
			ms = ((TextMessage) message).getText();
			if (ms.indexOf("delete:")!=-1) {
				
				int index=ms.indexOf(":")+1;
				ms=ms.substring(index);
				String[] idss = ms.split(",");
				for (String id : idss) {
					System.out.println(id);
					File file=new File("D:/freemarker/item/"+id+".html");
					file.delete();				
				}
				
				
			}else {
				Long itemid=Long.valueOf(textMessage.getText());
				TbItem tbItem = itemService.getTbItemById(itemid);
				Item item=new Item(tbItem);
				TbItemDesc desc=itemDescService.geTbItemDescByID(itemid);
				//生成静态页面 准备好模板 和数据集
				genHtmlFreeMarker(item,desc);
			}
			
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 生成静态页面
	 * @param item
	 * @param desc
	 * @throws Exception 
	 * 
	 */
	private void genHtmlFreeMarker(Item item, TbItemDesc desc) throws Exception {
		//获取configurer
		Configuration configuration=freemarkerConfig.getConfiguration();
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("item.ftl");
		
		//创建数据集
		Map<Object, Object> model=new HashMap<Object, Object>();
		model.put("item", item);
		model.put("itemDesc", desc);
		
		//输出
		Writer writer=new FileWriter(new File("D:/freemarker/item/"+item.getId()+".html"));
		template.process(model, writer);
		writer.close();
	}

}
