package com.atguigu.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.mapper.SkuMapper;

@Service
public class SkuServiceImp implements SkuServiceInf {

	@Autowired
	SkuMapper skuMapper;

	public void save_sku(OBJECT_T_MALL_PRODUCT spu, MODEL_T_MALL_SKU_ATTR_VALUE list_av, T_MALL_SKU sku) {
		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
		hashMap.put("sku", sku);
		hashMap.put("spu", spu);
		skuMapper.insert_sku(hashMap);

		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("list_av", list_av.getList_av());
		map.put("sku_id", sku.getId());
		map.put("spu_id", spu.getSpu_id());
		skuMapper.insert_sku_av(map);
	}

	public List<T_MALL_PRODUCT> get_spu_by_tm(int class_1_id, int class_2_id, int pp_id) {
		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
		hashMap.put("class_1_id", class_1_id);
		hashMap.put("class_2_id", class_2_id);
		hashMap.put("pp_id", pp_id);
		List<T_MALL_PRODUCT> list_spu = skuMapper.select_spu_by_tm(hashMap);
		return list_spu;
	}

}
