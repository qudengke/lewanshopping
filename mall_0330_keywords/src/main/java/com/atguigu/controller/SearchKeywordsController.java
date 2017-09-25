package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.util.MyProperty;
import com.atguigu.util.MySolrServer;

@Controller
public class SearchKeywordsController {

	@RequestMapping("search_keywords/{keywords}")
	@ResponseBody
	public List<OBJECT_T_MALL_SKU> search_keywords(@PathVariable String keywords) {
		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<OBJECT_T_MALL_SKU>();

		HttpSolrServer mySolr = MySolrServer.getMySolr(MyProperty.getMyProperty("solr_sku"));

		SolrQuery solrQuery = new SolrQuery();

		solrQuery.setQuery("sku_mch:" + keywords);

		try {
			QueryResponse query = mySolr.query(solrQuery);

			list_sku = query.getBeans(OBJECT_T_MALL_SKU.class);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list_sku;
	}

}
