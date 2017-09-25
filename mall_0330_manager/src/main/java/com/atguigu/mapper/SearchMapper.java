package com.atguigu.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

public interface SearchMapper {

	int test();

	List<Integer> select_value_by_attr_id(int shxm_id);

	T_MALL_USER_ACCOUNT select_user(T_MALL_USER_ACCOUNT user);

	List<OBJECT_T_MALL_SKU> select_sku_by_class_2(int class_2_id);

	List<OBJECT_T_MALL_SKU> select_sku_by_attr(HashMap<String, Object> hashMap);

	List<T_MALL_SKU> select_sku_by_spu(int spu_id);

}
