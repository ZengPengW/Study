package com.taotao.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.mapper.SearchItemMapper;

/**
 * 从索引库中搜索商品的dao
 * @author zp
 *
 */
@Repository
public class SearchDao {
	
	@Autowired
	private SolrServer solrServer;
	
	@Autowired
	private SearchItemMapper searchItemMapper;
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
	
	/**
	 * 更新索引库
	 * @param itemIds
	 * @return
	 * @throws Exception
	 */
	public TaotaoResult updateItemByIds(String... itemIds) throws Exception{
		List<SearchItem> items = searchItemMapper.getSearchItemListById(itemIds);
		List<SolrInputDocument> documents=new ArrayList<SolrInputDocument>();
		if (items!=null&&items.size()>0) {			
			SolrInputDocument document;
			for (SearchItem searchItem : items) {
				document=new SolrInputDocument();
				document.addField("id", searchItem.getId().toString());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());
				document.addField("item_desc", searchItem.getItem_desc());
				documents.add(document);
			}
			solrServer.add(documents);
			solrServer.commit();
		}
		
		
		return TaotaoResult.ok();
	}
}
