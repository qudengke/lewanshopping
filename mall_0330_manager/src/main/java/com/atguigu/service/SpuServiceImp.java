package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.mapper.SpuMapper;

@Service
public class SpuServiceImp implements SpuServiceInf {

	@Autowired
	SpuMapper spuMapper;

	public void save_spu(T_MALL_PRODUCT spu, List<String> list_image) {

		spu.setShp_tp(list_image.get(0));
		spuMapper.insert_spu(spu);

		spuMapper.insert_images(spu.getId(), list_image);

	}

}
