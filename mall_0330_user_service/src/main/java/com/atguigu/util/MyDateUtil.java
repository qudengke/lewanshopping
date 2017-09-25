package com.atguigu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateUtil {

	public static void main(String[] args) {
		getPasswordDate();
	}

	public static String getPasswordDate() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");

		String format = simpleDateFormat.format(new Date());

		return format;
	}

}
