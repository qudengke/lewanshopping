package com.atguigu.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.bean.T_MALL_CLASS_1;

public interface AttrMapper {

	List<T_MALL_CLASS_1> select_class_1();

	List<com.atguigu.bean.OBJECT_T_MALL_ATTR> select_attr_by_class_2(int class_2_id);

	void insert_attr(HashMap<Object, Object> hashMap1);

	void insert_values(HashMap<Object, Object> hashMap2);
}
