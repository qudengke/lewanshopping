package com.atguigu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.CartServiceImp;
import com.atguigu.service.CartServiceInf;
import com.atguigu.util.MyJsonUtil;

@Controller
public class CartController {

	@Autowired
	CartServiceInf cartServiceImp;

	@RequestMapping("goto_cart_list")
	public String goto_cart_list(ModelMap map, HttpSession session,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie_parm) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		if (user == null) {
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie_parm, T_MALL_SHOPPINGCAR.class);
		} else {
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		}
		map.put("sum", 7000);
		map.put("list_cart", list_cart);
		return "sale_cart_list";
	}

	@RequestMapping("change_cart")
	public String change_cart(ModelMap map, String shfxz, int tjshl, int sku_id) {

		BigDecimal sum = new BigDecimal("0");

		return "sale_cart_list_inner";
	}

	@RequestMapping("get_miniCart")
	public String get_miniCart(ModelMap map, HttpSession session,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie_parm) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		if (user == null) {
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie_parm, T_MALL_SHOPPINGCAR.class);
		} else {
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		}
		map.put("list_cart", list_cart);
		return "sale_miniCart_cart_list_inner";
	}

	@RequestMapping("cart_success")
	public String cart_success() {

		return "sale_cart_success";
	}

	@RequestMapping("add_cart")
	public String add_cart(T_MALL_SHOPPINGCAR cart,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie_parm,
			HttpSession session, HttpServletResponse response, ModelMap map) {

		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");

		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		if (user == null) {
			// 用户未登录
			if (StringUtils.isBlank(list_cart_cookie_parm)) {
				// cookie无数据
				list_cart.add(cart);
			} else {
				// cookie有数据
				list_cart = MyJsonUtil.json_to_list(list_cart_cookie_parm, T_MALL_SHOPPINGCAR.class);
				boolean b = if_new_cart(list_cart, cart);
				if (b) {
					// 新数据
					list_cart.add(cart);
				} else {
					// 老数据
					for (int i = 0; i < list_cart.size(); i++) {
						if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
							// 更新数量和合计
							list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
							list_cart.get(i).setHj(list_cart.get(i).getHj() + cart.getHj());
						}
					}
				}
			}
			Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);
		} else {

			// 用户已登录，操作db
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
			cart.setYh_id(user.getId());
			if (list_cart == null || list_cart.size() == 0) {
				// db和sessin中没有购物车数据
				list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
				// 添加db
				cartServiceImp.add_cart(cart);
				list_cart.add(cart);
				// 向session中插入一条购物车集合数据
				session.setAttribute("list_cart_session", list_cart);
			} else {
				boolean b = if_new_cart(list_cart, cart);
				if (b) {
					cartServiceImp.add_cart(cart);
					list_cart.add(cart);// 直接更新session中的购物车数据

				} else {
					// 更新添加数量和购物车合计
					for (int i = 0; i < list_cart.size(); i++) {
						if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
							list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
							list_cart.get(i).setHj(list_cart.get(i).getHj() + cart.getHj());
							cartServiceImp.update_cart(list_cart.get(i));

						}
					}
				}
			}
		}

		return "redirect:/cart_success.do";
	}

	private boolean if_new_cart(List<T_MALL_SHOPPINGCAR> list_cart_cookie, T_MALL_SHOPPINGCAR cart) {
		boolean b = true;
		for (int i = 0; i < list_cart_cookie.size(); i++) {
			if (list_cart_cookie.get(i).getSku_id() == cart.getSku_id()) {
				b = false;
				break;
			}
		}
		return b;
	}

}
