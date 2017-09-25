package com.atguigu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.atguigu.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.AddressServiceInf;
import com.atguigu.service.CartServiceInf;
import com.atguigu.service.OrderServiceInf;

@Controller
@SessionAttributes("order")
public class OrderController {

	@Autowired
	OrderServiceInf orderServiceImp;

	@Autowired
	CartServiceInf cartServiceImp;

	@Autowired
	AddressServiceInf addressServiceInf;

	@RequestMapping("sale_pay_success")
	public String sale_pay_success() {
		// 跳转到支付服务
		return "sale_pay_success";
	}

	@RequestMapping("order_pay")
	public String order_pay(@ModelAttribute("order") OBJECT_T_MALL_ORDER order) {
		// 支付时减库存
		orderServiceImp.order_pay(order);

		return "redirect:/sale_pay_success.do";
	}

	@RequestMapping("sale_pay")
	public String sale_pay() {
		// 跳转到支付服务
		return "sale_pay";
	}

	@RequestMapping("save_order")
	public String save_order(int address_id, @ModelAttribute("order") OBJECT_T_MALL_ORDER order, HttpSession session,
			ModelMap map) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");

		T_MALL_ADDRESS address = addressServiceInf.get_addresses_by_id(address_id);

		// 调用提交订单的业务层，将订单信息持久化
		orderServiceImp.save_order(order, user, address);

		// 清理session中订单对象
		session.setAttribute("list_cart_session", cartServiceImp.get_list_cart_by_user_id(user.getId()));

		return "redirect:/sale_pay.do";
	}

	@RequestMapping("goto_order")
	public String goto_order(HttpSession session, ModelMap map) {

		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();

		list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");

		List<T_MALL_ADDRESS> list_adress = addressServiceInf.get_addresses_by_user_id(user);

		OBJECT_T_MALL_ORDER t_MALL_ORDER = new OBJECT_T_MALL_ORDER();
		List<OBJECT_T_MALL_FLOW> list_flow = new ArrayList<OBJECT_T_MALL_FLOW>();

		BigDecimal zje = new BigDecimal("0");
		// 拆单,根据库存地址

		// 先获得购物车中有多少个商品的库存地址
		Set<String> list_kcdz = new HashSet<String>();
		for (int i = 0; i < list_cart.size(); i++) {
			list_kcdz.add(list_cart.get(i).getKcdz());
		}

		// 循环库存地址，按照库存地址生成物流单号
		Iterator<String> iterator = list_kcdz.iterator();
		while (iterator.hasNext()) {
			String kcdz = iterator.next();
			OBJECT_T_MALL_FLOW t_MALL_FLOW = new OBJECT_T_MALL_FLOW();

			List<T_MALL_ORDER_INFO> list_info = new ArrayList<T_MALL_ORDER_INFO>();
			for (int i = 0; i < list_cart.size(); i++) {

				if (list_cart.get(i).getKcdz().equals(kcdz) && list_cart.get(i).getShfxz().equals("1")) {

					zje = zje.add(new BigDecimal(list_cart.get(i).getHj() + ""));

					T_MALL_ORDER_INFO t_MALL_ORDER_INFO = new T_MALL_ORDER_INFO();
					t_MALL_ORDER_INFO.setGwch_id(list_cart.get(i).getId());
					t_MALL_ORDER_INFO.setShp_tp(list_cart.get(i).getShp_tp());
					t_MALL_ORDER_INFO.setSku_id(list_cart.get(i).getSku_id());
					t_MALL_ORDER_INFO.setSku_jg(list_cart.get(i).getSku_jg());
					t_MALL_ORDER_INFO.setSku_kcdz(list_cart.get(i).getKcdz());
					t_MALL_ORDER_INFO.setSku_mch(list_cart.get(i).getSku_mch());
					t_MALL_ORDER_INFO.setSku_shl(list_cart.get(i).getTjshl());
					list_info.add(t_MALL_ORDER_INFO);
				}
			}
			t_MALL_FLOW.setList_info(list_info);
			t_MALL_FLOW.setPsfsh("硅谷快递");
			t_MALL_FLOW.setYh_id(user.getId());
			t_MALL_FLOW.setMqdd(kcdz + ":商品未出库");
			list_flow.add(t_MALL_FLOW);

		}
		t_MALL_ORDER.setJdh(1);
		t_MALL_ORDER.setYh_id(user.getId());
		t_MALL_ORDER.setZje(zje);
		t_MALL_ORDER.setList_flow(list_flow);

		map.put("order", t_MALL_ORDER);
		map.put("list_address", list_adress);
		return "sale_order";
	}

}
