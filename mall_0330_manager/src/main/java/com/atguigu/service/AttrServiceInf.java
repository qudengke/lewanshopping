package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;

public interface AttrServiceInf {


	List<com.atguigu.bean.OBJECT_T_MALL_ATTR> get_attr_by_class_2(int class_2_id);

}
