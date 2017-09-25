package com.atguigu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.util.JedisPoolUtils;
import com.atguigu.util.MyProperty;
import com.atguigu.util.MyRedisDataUtil;
import com.atguigu.util.MySolrServer;

import redis.clients.jedis.Jedis;

@Controller
public class IndexController {

	@RequestMapping("index")
	@ResponseBody
	public String index(HttpServletRequest request, ModelMap map) {

		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<OBJECT_T_MALL_SKU>();
		// 查询redis缓存数据
		Jedis jedis = JedisPoolUtils.getJedis();
		Set<String> zrange = jedis.zrange("class_2_" + 28, 0, -1);

		list_sku = MyRedisDataUtil.get_list_by_redis(zrange, OBJECT_T_MALL_SKU.class);

		HttpSolrServer solr = MySolrServer.getMySolr(MyProperty.getMyProperty("solr_sku"));
		try {
			solr.deleteByQuery("*:*");
			solr.addBeans(list_sku);
			solr.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "sale_index";
	}

}
