package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.service.SearchServiceImp;
import com.atguigu.util.JedisPoolUtils;
import com.atguigu.util.MyJsonUtil;

import redis.clients.jedis.Jedis;

@Controller
public class SearchRedisController {

	@Autowired
	SearchServiceImp searchServiceImp;

	@RequestMapping("refresh_attr")
	@ResponseBody
	public long refresh_attr(int class_2_id, @RequestParam("attr_array[]") int[] attr_array) {

		long sum = 0l;

		// 获得redis的客户端连接
		Jedis jedis = JedisPoolUtils.getJedis();

		// 循环分类属性id的数组
		for (int i = 0; i < attr_array.length; i++) {
			List<Integer> list_value_id = searchServiceImp.get_value_by_attr_id(attr_array[i]);
			// 根据分类属性的id查询，分类属性值的集合

			for (int j = 0; j < list_value_id.size(); j++) {
				String key = "av_" + class_2_id + "_" + attr_array[i] + "_" + list_value_id.get(j);

				// 循环分类属性值的结合，查询对应的sku集合
				// 根据分类属性和属性值的id查询对应的sku集合
				List<T_MALL_SKU_ATTR_VALUE> list_av = new ArrayList<T_MALL_SKU_ATTR_VALUE>();
				T_MALL_SKU_ATTR_VALUE t_MALL_SKU_ATTR_VALUE = new T_MALL_SKU_ATTR_VALUE();
				t_MALL_SKU_ATTR_VALUE.setShxm_id(attr_array[i]);
				t_MALL_SKU_ATTR_VALUE.setShxzh_id(list_value_id.get(j));
				list_av.add(t_MALL_SKU_ATTR_VALUE);
				List<OBJECT_T_MALL_SKU> list_sku = searchServiceImp.get_sku_by_attr("", class_2_id, list_av);

				// 循环sku集合，插入redis
				jedis.del(key);
				for (int k = 0; k < list_sku.size(); k++) {
					jedis.zadd(key, k, MyJsonUtil.get_json(list_sku.get(k)));
				}
				sum += list_sku.size();
			}
		}

		return sum;
	}

	@RequestMapping("goto_search_redis")
	public String goto_search_redis() {

		return "manager_search_redis";
	}

	@RequestMapping("refresh_class_2")
	@ResponseBody
	public int refresh_class_2(int class_2_id) {

		// 查询二级分类对应的商品sku集合
		List<OBJECT_T_MALL_SKU> list_sku = searchServiceImp.get_sku_by_class_2(class_2_id);

		// 获得redis的客户端连接
		Jedis jedis = JedisPoolUtils.getJedis();

		// 调用redis的api将sku商品集合放入redis
		jedis.del("class_2_" + class_2_id);
		for (int i = 0; i < list_sku.size(); i++) {
			jedis.zadd("class_2_" + class_2_id, i, MyJsonUtil.get_json(list_sku.get(i)));
		}
		return list_sku.size();
	}

}
