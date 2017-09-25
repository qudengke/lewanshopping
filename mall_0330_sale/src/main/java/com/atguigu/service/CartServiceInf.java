package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;

public interface CartServiceInf {
	public List<T_MALL_SHOPPINGCAR> get_list_cart_by_user_id(int yh_id);

	public void add_cart(T_MALL_SHOPPINGCAR cart);

	public void update_cart(T_MALL_SHOPPINGCAR cart);
}
