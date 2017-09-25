package com.atguigu.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.SearchMapper;

@Service
public class SearchServiceImp implements SearchServiceInf {

	@Autowired
	SearchMapper searchMapper;

	public List<Integer> get_value_by_attr_id(int shxm_id) {
		List<Integer> list_id = searchMapper.select_value_by_attr_id(shxm_id);
		return list_id;
	}

	public List<OBJECT_T_MALL_SKU> get_sku_by_attr(String order, int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_av) {

		StringBuffer sql = new StringBuffer("");

		if (list_av != null && ArrayUtils.isNotEmpty(list_av.toArray())) {
			sql.append(" and sku.id in ");
			sql.append(" ( ");
			sql.append(" select sku_0.sku_id from ");

			for (int i = 0; i < list_av.size(); i++) {
				sql.append(" (select sku_id from t_mall_sku_attr_value where shxzh_id = " + list_av.get(i).getShxzh_id()
						+ " and shxm_id = " + list_av.get(i).getShxm_id() + ") sku_" + i + " ");

				if (i < (list_av.size() - 1)) {
					sql.append(" , ");
				}

			}

			if (list_av.size() > 1) {
				sql.append(" where ");

				for (int i = 0; i < list_av.size(); i++) {
					if (i < (list_av.size() - 1)) {
						sql.append(" sku_" + i + ".sku_id=sku_" + (i + 1) + ".sku_id ");
					}
					if (i < (list_av.size() - 2)) {
						sql.append(" and ");
					}
				}
			}

			sql.append(" ) ");
		}

		System.out.println(sql);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("class_2_id", class_2_id);
		hashMap.put("SQL", sql.toString());
		hashMap.put("order", order);
		List<OBJECT_T_MALL_SKU> list_sku = searchMapper.select_sku_by_attr(hashMap);
		return list_sku;
	}

	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2(int class_2_id) {
		List<OBJECT_T_MALL_SKU> select_sku_by_class_2 = searchMapper.select_sku_by_class_2(class_2_id);
		return select_sku_by_class_2;
	}

	public List<T_MALL_SKU> get_sku_by_spu(int spu_id) {
		List<T_MALL_SKU> list_sku = searchMapper.select_sku_by_spu(spu_id);
		return list_sku;
	}

}
