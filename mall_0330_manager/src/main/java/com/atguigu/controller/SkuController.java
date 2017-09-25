package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.service.AttrServiceImp;
import com.atguigu.service.SkuServiceImp;

@Controller
public class SkuController {

	@Autowired
	SkuServiceImp skuServiceImp;

	@Autowired
	AttrServiceImp attrServiceImp;

	@RequestMapping("save_sku")
	public ModelAndView save_sku(OBJECT_T_MALL_PRODUCT spu, MODEL_T_MALL_SKU_ATTR_VALUE list_av, T_MALL_SKU sku) {
		skuServiceImp.save_sku(spu, list_av, sku);

		ModelAndView modelAndView = new ModelAndView("redirect:/index.do");
		modelAndView.addObject("success", "恭喜");
		modelAndView.addObject("url", "goto_sku.do");
		modelAndView.addObject("title", "库存信息");

		return modelAndView;
	}

	@RequestMapping("get_sku_attr_list_by_class_2")
	public String get_sku_attr_list_by_class_2(int class_2_id, ModelMap map) {
		List<OBJECT_T_MALL_ATTR> list_attr = attrServiceImp.get_attr_by_class_2(class_2_id);
		map.put("list_attr", list_attr);
		return "manager_sku_attr_inner";
	}

	@ResponseBody
	@RequestMapping("get_spu_by_tm")
	public List<T_MALL_PRODUCT> get_spu_by_tm(int class_1_id, int class_2_id, int pp_id) {

		List<T_MALL_PRODUCT> list_spu = skuServiceImp.get_spu_by_tm(class_1_id, class_2_id, pp_id);
		return list_spu;
	}

	@RequestMapping("goto_sku")
	public String goto_sku() {
		return "manager_sku";
	}

}
