package com.atguigu.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.mapper.TestMapper;

@Controller
public class IndexController {

	@RequestMapping("index")
	public String index(HttpServletRequest request, ModelMap map) {

		// // 从浏览器客户端取出cookie中的用户名，放入首页的jsp的域中
		// Cookie[] cookies = request.getCookies();
		// String yh_mch = "";
		// for (int i = 0; i < cookies.length; i++) {
		// if (cookies[i].getName().equals("yh_mch")) {
		// yh_mch = cookies[i].getValue();
		// }
		// }
		// map.put("yh_mch", yh_mch);
		return "sale_index";
	}

}
