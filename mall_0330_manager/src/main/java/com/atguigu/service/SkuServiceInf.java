package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;

public interface SkuServiceInf {
	public List<T_MALL_PRODUCT> get_spu_by_tm(int class_1_id, int class_2_id, int pp_id);

	public void save_sku(OBJECT_T_MALL_PRODUCT spu, MODEL_T_MALL_SKU_ATTR_VALUE list_av, T_MALL_SKU sku);

}
