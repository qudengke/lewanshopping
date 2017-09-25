package com.atguigu.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyDataSourceRouting extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return MyDataSourceSwitch.getKey();
	}

}
