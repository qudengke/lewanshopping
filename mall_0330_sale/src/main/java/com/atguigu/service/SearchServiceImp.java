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
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.AttrMapper;
import com.atguigu.mapper.SearchMapper;

@Service
public class SearchServiceImp implements SearchServiceInf {

	@Autowired
	SearchMapper searchMapper;

	@Autowired
	AttrMapper attrMapper;

	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2(int class_2_id) {
		List<OBJECT_T_MALL_SKU> select_sku_by_class_2 = searchMapper.select_sku_by_class_2(class_2_id);
		return select_sku_by_class_2;
	}

	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2(int class_2_id) {
		List<OBJECT_T_MALL_ATTR> list_attr = attrMapper.select_attr_by_class_2(class_2_id);
		return list_attr;
	}

	@Override
	public List<OBJECT_T_MALL_SKU> get_sku_by_attr(String order, int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_av) {
		StringBuffer sql = new StringBuffer("");

		if (list_av != null && list_av.size() > 0) {
			// 分类属性查询动态拼接sql语句
			sql.append(" and sku.id in ");
			sql.append(" (select sku_0.sku_id from ");

			for (int i = 0; i < list_av.size(); i++) {
				sql.append(" (select sku_id from t_mall_sku_attr_value where shxm_id = " + list_av.get(i).getShxm_id()
						+ " and shxzh_id = " + list_av.get(i).getShxzh_id() + ") sku_" + i + " ");

				if (i < (list_av.size() - 1)) {
					sql.append(" , ");
				}
			}

			if (list_av.size() > 1) {
				sql.append(" where ");
				for (int i = 0; i < (list_av.size() - 1); i++) {
					sql.append(" sku_" + i + ".sku_id = sku_" + (i + 1) + ".sku_id ");
					if (list_av.size() > 2 && i < (list_av.size() - 2)) {
						sql.append(" and ");
					}
				}
			}

			sql.append(" ) ");
		}

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("class_2_id", class_2_id);
		map.put("sql", sql);
		if (StringUtils.isNotBlank(order)) {
			map.put("order", order);
		}
		List<OBJECT_T_MALL_SKU> list_sku = searchMapper.select_sku_by_attr(map);

		return list_sku;
	}

	public DETAIL_T_MALL_SKU get_sku_detail_by_id(int sku_id) {
		DETAIL_T_MALL_SKU obj_sku = searchMapper.select_sku_detail_by_id(sku_id);

		return obj_sku;
	}

	public List<OBJECT_T_MALL_SKU> get_sku_by_spu(int spu_id) {
		List<OBJECT_T_MALL_SKU> list_sku = searchMapper.select_sku_by_spu(spu_id);
		return list_sku;
	}

}
