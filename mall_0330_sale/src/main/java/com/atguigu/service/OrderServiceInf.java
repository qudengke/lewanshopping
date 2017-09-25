package com.atguigu.service;

import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

public interface OrderServiceInf {
	public void save_order(OBJECT_T_MALL_ORDER order, T_MALL_USER_ACCOUNT user, T_MALL_ADDRESS address);

	public void order_pay(OBJECT_T_MALL_ORDER order);

}
