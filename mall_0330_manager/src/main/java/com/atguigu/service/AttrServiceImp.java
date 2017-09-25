package com.atguigu.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.mapper.AttrMapper;

@Service
public class AttrServiceImp implements AttrServiceInf {

	@Autowired
	AttrMapper attrMapper;

	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2(int class_2_id) {
		List<OBJECT_T_MALL_ATTR> list_attr = attrMapper.select_attr_by_class_2(class_2_id);
		return list_attr;
	}

	public void save_attr(List<OBJECT_T_MALL_ATTR> list_attr, int class_2_id) {

		for (int i = 0; i < list_attr.size(); i++) {

			HashMap<Object, Object> hashMap1 = new HashMap<Object, Object>();
			hashMap1.put("class_2_id", class_2_id);
			hashMap1.put("attr_obj", list_attr.get(i));
			attrMapper.insert_attr(hashMap1);

			HashMap<Object, Object> hashMap2 = new HashMap<Object, Object>();
			hashMap2.put("list_value", list_attr.get(i).getList_value());
			hashMap2.put("attr_id", list_attr.get(i).getId());
			attrMapper.insert_values(hashMap2);
		}

	}



}
