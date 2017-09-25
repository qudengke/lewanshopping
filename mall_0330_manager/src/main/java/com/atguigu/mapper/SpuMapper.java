package com.atguigu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.bean.T_MALL_PRODUCT;

public interface SpuMapper {

	List<T_MALL_CLASS_1> select_class_1();

	void insert_spu(T_MALL_PRODUCT spu);

	void insert_images(@Param("id") int id, @Param("list_image") List<String> list_image);
}
