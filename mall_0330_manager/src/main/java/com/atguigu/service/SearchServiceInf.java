package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;

public interface SearchServiceInf {
	
	List<Integer> get_value_by_attr_id(int shxm_id);

	public List<T_MALL_SKU> get_sku_by_spu(int spu_id);

	public List<OBJECT_T_MALL_SKU> get_sku_by_attr(String order, int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_av);

	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2(int class_2_id);

}
