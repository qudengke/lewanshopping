package com.atguigu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_FLOW;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.OrderMapper;
import com.atguigu.util.MyDateUtil;

@Service
public class OrderServiceImp implements OrderServiceInf {

	@Autowired
	OrderMapper orderMapper;

	public void save_order(OBJECT_T_MALL_ORDER order, T_MALL_USER_ACCOUNT user, T_MALL_ADDRESS address) {
		List<Integer> list_cart_id = new ArrayList<Integer>();
		// 保存订单，生成订id，和单号
		order.setDzh_id(address.getId());
		order.setShhr(address.getShjr());
		order.setDzh_mch(address.getYh_dz());
		orderMapper.insert_order(order);

		// 根据订单号，生成物流信息包裹，生成物流id，不生成物流单号
		for (int i = 0; i < order.getList_flow().size(); i++) {
			order.getList_flow().get(i).setPsfsh("硅谷快递");
			order.getList_flow().get(i).setYh_id(user.getId());
			order.getList_flow().get(i).setDd_id(order.getId());
			order.getList_flow().get(i).setMqdd("尚未出库");
			order.getList_flow().get(i).setMdd(address.getYh_dz());
			orderMapper.insert_flow(order.getList_flow().get(i));

			// 根据物流id，批量插入订单信息表
			for (int j = 0; j < order.getList_flow().get(i).getList_info().size(); j++) {
				order.getList_flow().get(i).getList_info().get(j).setFlow_id(order.getList_flow().get(i).getId());
				order.getList_flow().get(i).getList_info().get(j).setDd_id(order.getId());
				list_cart_id.add(order.getList_flow().get(i).getList_info().get(j).getGwch_id());
			}
			orderMapper.insert_order_infos(order.getList_flow().get(i).getList_info());

		}

		// 删除已经提交订单的购物车信息
		orderMapper.delete_shoppingCars(list_cart_id);

	}

	public void order_pay(OBJECT_T_MALL_ORDER order) {

		// 修改订单状态
		order.setYjsdshj(MyDateUtil.getMyFlowDate(3));
		order.setJdh(2);
		orderMapper.update_order(order);

		// 修改物流信息
		for (int i = 0; i < order.getList_flow().size(); i++) {
			order.getList_flow().get(i).setPsmsh("硅谷快递");
			order.getList_flow().get(i).setPsshj(MyDateUtil.getMyFlowDate(1));
			order.getList_flow().get(i).setYwy("老佟");
			order.getList_flow().get(i).setLxfsh("1234123412");

			orderMapper.update_flow(order.getList_flow().get(i));

			for (int j = 0; j < order.getList_flow().get(i).getList_info().size(); j++) {
				// 确认库存数量
				boolean b = if_can_by(order.getList_flow().get(i).getList_info().get(i));

				if (b) {
					// 修改库存和销量信息
					// orderMapper = null;
					orderMapper.update_kc(order.getList_flow().get(i).getList_info().get(i));
				}
			}
		}

	}

	private boolean if_can_by(T_MALL_ORDER_INFO t_MALL_ORDER_INFO) {

		boolean b = false;

		int select_kc = orderMapper.select_kc(t_MALL_ORDER_INFO);

		if (select_kc >= 0) {
			b = true;
		}

		return b;
	}

}
