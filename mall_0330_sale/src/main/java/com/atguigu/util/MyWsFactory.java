package com.atguigu.util;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.atguigu.service.UserServiceInf;

public class MyWsFactory {

	public static <T> T getMyWsInf(String url, Class<T> t) {

		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();

		jaxWsProxyFactoryBean.setAddress(url);

		jaxWsProxyFactoryBean.setServiceClass(t);

		T create = (T) jaxWsProxyFactoryBean.create();
		return create;
	}

}
