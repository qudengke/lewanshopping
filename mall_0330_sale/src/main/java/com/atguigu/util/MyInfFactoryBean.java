package com.atguigu.util;

import java.util.HashMap;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.FactoryBean;

public class MyInfFactoryBean<T> implements FactoryBean<T> {

	private Class<T> t;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Class<T> getT() {
		return t;
	}

	public void setT(Class<T> t) {
		this.t = t;
	}

	@Override
	public T getObject() throws Exception {

		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();

		jaxWsProxyFactoryBean.setAddress(url);

		jaxWsProxyFactoryBean.setServiceClass(t);

		if (t.getSimpleName().equals("xxxInf")) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
			hashMap.put(WSHandlerConstants.USER, "user");
			hashMap.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
			hashMap.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyCallBackClient.class.getName());
			WSS4JOutInterceptor wss4jOutInterceptor = new WSS4JOutInterceptor(hashMap);
			jaxWsProxyFactoryBean.getOutInterceptors().add(wss4jOutInterceptor);
		}

		T create = (T) jaxWsProxyFactoryBean.create();

		return create;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return this.t;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

}
