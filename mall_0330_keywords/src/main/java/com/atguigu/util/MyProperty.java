package com.atguigu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperty {

	public static String getMyProperty(String key) {

		Properties properties = new Properties();

		InputStream resourceAsStream = MyProperty.class.getClassLoader().getResourceAsStream("solr.properties");

		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String property = properties.getProperty(key);

		return property;
	}

}
