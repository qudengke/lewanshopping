package com.atguigu.service;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.UserMapper;
import com.atguigu.util.MyDataSourceSwitch;
import com.google.gson.Gson;

public class UserServiceImp implements UserServiceInf {

	@Autowired
	UserMapper userMapper;

	@Override
	public String ping(String hello) {
		System.err.println("你好：" + hello);
		return "pong";
	}

	@Override
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {

		MyDataSourceSwitch.setKey("1");

		T_MALL_USER_ACCOUNT login = userMapper.select_user(user);
		return login;
	}

	@Override
	@GET
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@Path("loginTest")
	public String loginTest(@BeanParam T_MALL_USER_ACCOUNT user) {

		MyDataSourceSwitch.setKey("2");

		T_MALL_USER_ACCOUNT login = userMapper.select_user(user);
		Gson g = new Gson();
		return g.toJson(login);
	}

}
