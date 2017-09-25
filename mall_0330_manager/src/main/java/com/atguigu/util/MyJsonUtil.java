package com.atguigu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONArray;

public class MyJsonUtil {

	public static <T> List<T> json_to_list(String json, Class<T> t) {

		if (StringUtils.isNotBlank(json)) {
			try {
				json = URLDecoder.decode(json, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONArray fromObject4 = JSONArray.fromObject(json);

			List<T> collection = (List<T>) JSONArray.toCollection(fromObject4, t);

			return collection;
		} else {
			return null;
		}

	}

	public static <T> String list_to_json(T t) {

		Gson gson = new Gson();

		String json = gson.toJson(t);

		String encode = "";
		try {
			encode = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encode;

	}

	public static String get_json(OBJECT_T_MALL_SKU object_T_MALL_SKU) {
		String json = new Gson().toJson(object_T_MALL_SKU);
		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

}
