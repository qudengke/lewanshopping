package com.atguigu.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.DETAIL_T_MALL_SKU;
import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.service.SearchServiceInf;
import com.atguigu.util.JedisPoolUtils;
import com.atguigu.util.MyHttpGetUtil;
import com.atguigu.util.MyJsonUtil;
import com.atguigu.util.MyProperty;
import com.atguigu.util.MyRedisDataUtil;

import redis.clients.jedis.Jedis;

@Controller
public class SearchController {

	@Autowired
	SearchServiceInf searchServiceImp;

	@RequestMapping("search_keywords")
	public String search_keywords(String keywords, ModelMap map) {
		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<OBJECT_T_MALL_SKU>();
		
		
		keywords = keywords.replaceAll(" ", "");
		// 访问关键字检索服务,输入关键字
		try {
			String url = MyProperty.getMyProperty("solr_keywords") + "search_keywords" + "/" + keywords + ".do";
			String doGet = MyHttpGetUtil.doGet(url);

			list_sku = MyJsonUtil.json_to_list(doGet, OBJECT_T_MALL_SKU.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		map.put("keywords", keywords);
		map.put("list_sku", list_sku);
		return "sale_keywords";
	}

	@RequestMapping("get_sku_by_id")
	public String get_sku_by_id(int spu_id, int sku_id, ModelMap map) {
		DETAIL_T_MALL_SKU obj_sku = searchServiceImp.get_sku_detail_by_id(sku_id);
		List<OBJECT_T_MALL_SKU> list_sku = searchServiceImp.get_sku_by_spu(spu_id);

		map.put("obj_sku", obj_sku);
		map.put("list_sku", list_sku);
		return "sale_search_detail";
	}

	@RequestMapping("get_sku_by_attr")
	public String get_sku_by_attr(String order, int class_2_id, MODEL_T_MALL_SKU_ATTR_VALUE list_av, ModelMap map) {
		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<OBJECT_T_MALL_SKU>();
		// 查询redis缓存数据
		Jedis jedis = JedisPoolUtils.getJedis();
		String[] keys = new String[list_av.getList_av().size()];
		for (int i = 0; i < list_av.getList_av().size(); i++) {
			keys[i] = "av_" + class_2_id + "_" + list_av.getList_av().get(i).getShxm_id() + "_"
					+ list_av.getList_av().get(i).getShxzh_id();
		}

		String out = "out";

		for (int i = 0; i < keys.length; i++) {
			out = out + "_" + keys;
		}

		Boolean exists = jedis.exists(out);
		if (!exists) {
			jedis.zinterstore(out, keys);
		}

		Set<String> zrange = jedis.zrange(out, 0, -1);
		if (zrange != null && zrange.size() > 0) {
			list_sku = MyRedisDataUtil.get_list_by_redis(zrange, OBJECT_T_MALL_SKU.class);
		} else {
			// mysql
			list_sku = searchServiceImp.get_sku_by_attr(order, class_2_id, list_av.getList_av());
		}
		map.put("list_sku", list_sku);
		return "sale_search_inner";
	}

	@RequestMapping("class_2_sku_search")
	public String class_2_sku_search(int class_2_id, String class_2_name, ModelMap map) {

		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<OBJECT_T_MALL_SKU>();

		// 查询redis缓存数据
		Jedis jedis = JedisPoolUtils.getJedis();
		Set<String> zrange = jedis.zrange("class_2_" + class_2_id, 0, -1);

		if (zrange != null && zrange.size() > 0) {
			list_sku = MyRedisDataUtil.get_list_by_redis(zrange, OBJECT_T_MALL_SKU.class);
		} else {
			// mysql
			list_sku = searchServiceImp.get_sku_by_class_2(class_2_id);

			// 同步redis
		}

		List<OBJECT_T_MALL_ATTR> list_attr = searchServiceImp.get_attr_by_class_2(class_2_id);
		map.put("list_sku", list_sku);
		map.put("list_attr", list_attr);
		map.put("class_2_id", class_2_id);
		map.put("class_2_name", class_2_name);
		return "sale_search";
	}

}
