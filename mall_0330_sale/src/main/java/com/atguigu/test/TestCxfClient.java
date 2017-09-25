package com.atguigu.test;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.UserServiceInf;

public class TestCxfClient {

	public static void main(String[] args) {

		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();

		jaxWsProxyFactoryBean.setAddress("http://localhost:28080/mall_0330_user_service_teacher/user?wsdl");

		jaxWsProxyFactoryBean.setServiceClass(UserServiceInf.class);

		UserServiceInf create = (UserServiceInf) jaxWsProxyFactoryBean.create();

		T_MALL_USER_ACCOUNT user = new T_MALL_USER_ACCOUNT();
		user.setYh_mch("lilei");
		user.setYh_mm("123");
		T_MALL_USER_ACCOUNT login = create.login(user);

		System.out.println(login);
	}

	private static void T_MALL_USER_ACCOUNT() {
		// TODO Auto-generated method stub

	}

}
