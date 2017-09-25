package com.atguigu.bean;

import java.util.List;

public class OBJECT_T_MALL_SKU extends T_MALL_SKU {

	private T_MALL_PRODUCT spu;

	private T_MALL_TRADE_MARK tm;

	private List<T_MALL_SKU_ATTR_VALUE> list_av;

	public List<T_MALL_SKU_ATTR_VALUE> getList_av() {
		return list_av;
	}

	public void setList_av(List<T_MALL_SKU_ATTR_VALUE> list_av) {
		this.list_av = list_av;
	}

	public T_MALL_PRODUCT getSpu() {
		return spu;
	}

	public void setSpu(T_MALL_PRODUCT spu) {
		this.spu = spu;
	}

	public T_MALL_TRADE_MARK getTm() {
		return tm;
	}

	public void setTm(T_MALL_TRADE_MARK tm) {
		this.tm = tm;
	}

}
