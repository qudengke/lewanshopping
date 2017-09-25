package com.atguigu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.DETAIL_T_MALL_SKU;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.AttrMapper;
import com.atguigu.mapper.CartMapper;
import com.atguigu.mapper.SearchMapper;

@Service
public class CartServiceImp implements CartServiceInf {

	@Autowired
	CartMapper cartMapper;

	public void add_cart(T_MALL_SHOPPINGCAR cart) {
		cartMapper.insert_cart(cart);

	}

	public void update_cart(T_MALL_SHOPPINGCAR cart) {
		cartMapper.update_cart(cart);

	}

	public List<T_MALL_SHOPPINGCAR> get_list_cart_by_user_id(int yh_id) {
		List<T_MALL_SHOPPINGCAR> list_cart = cartMapper.select_list_cart_by_user_id(yh_id);
		return list_cart;
	}

}
