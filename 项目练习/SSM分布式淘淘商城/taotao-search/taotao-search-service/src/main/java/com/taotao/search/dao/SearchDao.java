package com.taotao.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;

/**
 * 从索引库中搜索商品的dao
 * @author zp
 *
 */
@Repository
public class SearchDao {
	
	@Autowired
	private SolrServer solrServer;
	
	/**
	 * 根据查询条件查询结果集
	 * @param query
	 * @return
	 * @throws Exception
	 */
	
	public SearchResult search(SolrQuery query)throws Exception{
		QueryResponse queryResponse = solrServer.query(query);
		SolrDocumentList results = queryResponse.getResults();
		//封装返回的结果集
		SearchResult searchResult=new SearchResult();
		searchResult.setTotalCount(results.getNumFound());
		
		List<SearchItem> list=new ArrayList<SearchItem>();
		SearchItem item=null;
		
		//取高亮
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		
		for (SolrDocument solrDocument : results) {
			item=new SearchItem();
			item.setId(Long.parseLong((String) solrDocument.get("id")));		
			item.setSell_point(solrDocument.get("item_sell_point").toString());
			item.setPrice((Long) solrDocument.get("item_price"));
			item.setImage(solrDocument.get("item_image").toString());
			item.setCategory_name(solrDocument.get("item_category_name").toString());
			//取高亮
			List<String> list2 = highlighting.get(solrDocument.get("id")).get("item_title");
			String highlightingString="";
			if(list2!=null&&list2.size()>0) {
				highlightingString=list2.get(0);
			}else {
				highlightingString=(String) solrDocument.get("item_title");
			}
			item.setTitle(highlightingString);
			//item.setCategory_name((String)solrDocument.get("item_desc"));
			list.add(item);
		}
		searchResult.setItemList(list);
		return searchResult;
		
	}
}
