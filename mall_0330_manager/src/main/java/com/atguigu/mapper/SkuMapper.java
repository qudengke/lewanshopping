package com.atguigu.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.bean.T_MALL_PRODUCT;

public interface SkuMapper {

	List<T_MALL_PRODUCT> select_spu_by_tm(HashMap<Object, Object> hashMap);

	void insert_sku(HashMap<Object, Object> hashMap);

	void insert_sku_av(HashMap<Object, Object> map);

}
