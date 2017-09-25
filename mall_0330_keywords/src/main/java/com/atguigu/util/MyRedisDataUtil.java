package com.atguigu.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MyRedisDataUtil {

	public static <T> List<T> get_list_by_redis(Collection<String> string, Class<T> t) {

		List<T> list = new ArrayList<T>();

		Iterator<String> iterator = string.iterator();

		while (iterator.hasNext()) {
			String next = iterator.next();

			T json_to_object = MyJsonUtil.json_to_object(next, t);

			list.add(json_to_object);
		}

		return list;
	}

}
