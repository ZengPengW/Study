package com.taotao.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.mapper.SearchItemMapper;
import com.taotao.search.service.SearchService;

@Service
public class SearchServiceimpl implements SearchService {

	@Autowired
	private SearchItemMapper searchItemMapper;

	@Autowired
	private SolrServer solrServer;
	
	@Autowired
	private SearchDao searchDao;
	
	@Override
	public TaotaoResult importAllSearchItems() throws Exception {
		List<SearchItem> searchItemList = searchItemMapper.getSearchItemList();
		
		
		for (SearchItem searchItem : searchItemList) {
			SolrInputDocument document=new SolrInputDocument();
			document.addField("id", searchItem.getId().toString());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			document.addField("item_desc", searchItem.getItem_desc());
			solrServer.add(document);
		}		
		solrServer.commit();
		return TaotaoResult.ok();
	}

	@Override
	public SearchResult search(String queryString, Integer page, Integer rows) throws Exception {
		SolrQuery solrQuery=new SolrQuery();
		//设置查询主要条件
		if(StringUtils.isNotBlank(queryString))
			solrQuery.setQuery(queryString);
		else 
			solrQuery.setQuery("*:*");
		//设置分页
		if(page==null)page=1;
		if(rows==null)rows=60;
		solrQuery.setStart((page-1)*rows);
		solrQuery.setRows(rows);
		//设置搜索域
		solrQuery.set("df", "item_keywords");
		
		//设置高亮
		solrQuery.setHighlight(true);
		solrQuery.setHighlightSimplePre("<em style='color:red'>");
		solrQuery.setHighlightSimplePost("</em>");
		solrQuery.addHighlightField("item_title");
		
		//查询
		SearchResult searchResult = searchDao.search(solrQuery);
		//设置总页数
		Long pageCount=0l;
		pageCount=searchResult.getTotalCount()%rows==0?searchResult.getTotalCount()/rows:(searchResult.getTotalCount()/rows)+1;
		searchResult.setPageCount(pageCount);
		
		return searchResult;
	}

}
