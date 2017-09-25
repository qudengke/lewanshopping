package com.atguigu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONArray;

public class MyJsonUtil {

	public static void main(String[] args) throws UnsupportedEncodingException {
		List<T_MALL_SHOPPINGCAR> list_car = new ArrayList<T_MALL_SHOPPINGCAR>();

		for (int i = 0; i < 3; i++) {
			T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = new T_MALL_SHOPPINGCAR();
			t_MALL_SHOPPINGCAR.setSku_mch("商品" + i);
			t_MALL_SHOPPINGCAR.setSku_jg(i * 1000);
			list_car.add(t_MALL_SHOPPINGCAR);
		}

		// 1
		Gson gson = new Gson();
		String json = gson.toJson(list_car);
		json = URLEncoder.encode(json, "utf-8");
		String decode = URLDecoder.decode(json, "utf-8");
		TypeToken<List<T_MALL_SHOPPINGCAR>> typeToken = new TypeToken<List<T_MALL_SHOPPINGCAR>>() {
		};
		List<T_MALL_SHOPPINGCAR> list = (List<T_MALL_SHOPPINGCAR>) gson.fromJson(decode, typeToken.getType());

		List<T_MALL_SHOPPINGCAR> json_to_list = json_to_list(json, T_MALL_SHOPPINGCAR.class);

		// 2
		JSONArray fromObject = JSONArray.fromObject(list_car);
		String string = fromObject.toString();
		System.err.println(string);
		JSONArray fromObject2 = JSONArray.fromObject(string);
		List<T_MALL_SHOPPINGCAR> list_array = (List<T_MALL_SHOPPINGCAR>) JSONArray.toCollection(fromObject2,
				T_MALL_SHOPPINGCAR.class);
		System.out.println(list_array);

	}

	public static <T> T json_to_object(String json, Class<T> t) {

		try {
			json = URLDecoder.decode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();

		T fromJson = gson.fromJson(json, t);

		return fromJson;
	}

	public static <T> List<T> json_to_list(String json, Class<T> t) {
		String decode = "";
		try {
			decode = URLDecoder.decode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray fromObject2 = JSONArray.fromObject(decode);

		List<T> list_array = (List<T>) JSONArray.toCollection(fromObject2, t);

		return list_array;
	}

	public static <T> String list_to_json(List<T> list) {

		Gson gson = new Gson();
		String json = gson.toJson(list);

		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;

	}

}
