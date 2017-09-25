package com.atguigu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();

		c.add(Calendar.DATE, -3);

		System.out.println(c.getTime());

	}

	public static Date getMyFlowDate(int i) {
		
		Calendar c = Calendar.getInstance();

		c.add(Calendar.DATE, i);

		return c.getTime();
	}

	public static String getPasswordDate() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");

		String format = simpleDateFormat.format(new Date());

		return format;
	}

}
