package com.taotao.test.search;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrjTest {

	//添加索引
	@Test
	public void add() throws SolrServerException, IOException {
		//创建solrserver 建立连接 
		SolrServer solrServer=new HttpSolrServer("http://192.168.25.128:8081/solr");
		//创建solrinputdocument
		SolrInputDocument document=new SolrInputDocument();
		//向文档中添加域
		document.addField("id", "test01");
		document.addField("item_title", "这是一个测试");
		//将文档提交到索引库
		solrServer.add(document);
		//提交
		solrServer.commit();
	}
	
	//搜索索引
		@Test
		public void select() throws SolrServerException, IOException {
			//创建solrserver 建立连接 
			SolrServer solrServer=new HttpSolrServer("http://192.168.25.128:8081/solr");
		
			SolrQuery solrQuery=new SolrQuery();
			solrQuery.setQuery("一加");
			//solrQuery.set("q", "item_title:阿尔卡特");
		//	solrQuery.addFilterQuery("item_price:[0 TO 200000]");
			//设置查询域
			solrQuery.set("df", "item_keywords");
			//查询
			QueryResponse response = solrServer.query(solrQuery);
			//获取结果集
			SolrDocumentList results = response.getResults();
			//获取总记录数
			System.out.println("total:"+results.getNumFound());
			//获取内容
			for (SolrDocument solrDocument : results) {
				System.out.println(solrDocument.get("id"));
				System.out.println(solrDocument.get("item_title"));
				System.out.println(solrDocument.get("item_price"));
				System.out.println(solrDocument.get("item_image"));
				System.out.println("****************************************");
			}
		}
		
	
}
