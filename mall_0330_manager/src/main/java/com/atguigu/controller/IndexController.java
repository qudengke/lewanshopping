package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.mapper.TestMapper;

@Controller
public class IndexController {

	@RequestMapping("index")
	public String index(String url, String title, ModelMap map) {
		map.put("url", url);
		map.put("title", title);
		return "manager_index";
	}

}
