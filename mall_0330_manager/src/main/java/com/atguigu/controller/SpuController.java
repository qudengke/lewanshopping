package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.service.SpuServiceImp;
import com.atguigu.util.MyUploadFileUtil;

@Controller
public class SpuController {

	@Autowired
	SpuServiceImp spuServiceImp;

	@RequestMapping("goto_spu")
	public String goto_spu(String success, ModelMap map) {
		map.put("success", success);
		return "manager_spu";
	}

	@RequestMapping("save_spu")
	public ModelAndView save_spu(T_MALL_PRODUCT spu, @RequestParam("files") MultipartFile[] files) {

		// 图片上传到服务器
		List<String> list_image = MyUploadFileUtil.upload_image(files);

		// 将spu信息和图片信息保存到数据库
		spuServiceImp.save_spu(spu, list_image);

		ModelAndView modelAndView = new ModelAndView("redirect:/index.do");
		modelAndView.addObject("success", "恭喜");
		modelAndView.addObject("url", "goto_spu.do");
		modelAndView.addObject("title", "spu信息");

		return modelAndView;// "redirect:/goto_spu.do?success=success";
	}

}
