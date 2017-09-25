package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

public interface UserMapper {

	T_MALL_USER_ACCOUNT select_user(T_MALL_USER_ACCOUNT user);

	void insert_user(T_MALL_USER_ACCOUNT user);

}
