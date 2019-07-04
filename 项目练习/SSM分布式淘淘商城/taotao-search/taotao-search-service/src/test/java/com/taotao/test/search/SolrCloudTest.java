package com.taotao.test.search;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrCloudTest {

	@Test
	public void testAdd() throws SolrServerException, IOException {
		//1.创建solrserver 集群实现类
		CloudSolrServer cloudSolrServer=new CloudSolrServer("192.168.25.128:2182,192.168.25.128:2183,192.168.25.128:2184");
				
		//2.设置默认的搜索的collrction 默认的属于库表示core所对应的,是指中国collection 索引集合
		cloudSolrServer.setDefaultCollection("collection2");
		
		
//		UpdateResponse response = cloudSolrServer.deleteById("156224338423818",10000);
//		System.out.println(response.getRequestUrl());
//		System.out.println(response.getStatus());
//		System.out.println(response.getResponse());
//		cloudSolrServer.commit();
		
		//3.创建solrinputdocument对象
		SolrInputDocument document=new SolrInputDocument();
		
		//4.添加域到文档
		document.addField("id", "22");
		document.addField("item_title", "item_title");
		document.addField("name", "item_name");
		//5.将文档提交到索引库
		cloudSolrServer.add(document);
		//6.提交
		cloudSolrServer.commit();
		
		
	}
}
