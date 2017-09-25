package com.atguigu.test;

import java.math.BigDecimal;

public class TestBigDecimal {

	public static void main(String[] args) {
		// 1 初始化问题
		BigDecimal sum1 = new BigDecimal("6");
		BigDecimal sum2 = new BigDecimal("7");
		System.out.println(sum1);
		System.out.println(sum2);

		// 2 比较
		int compareTo = sum1.compareTo(sum2);
		System.out.println(compareTo);

		// 3 运算
		BigDecimal add = sum1.add(sum2);
		System.out.println(add);
		BigDecimal subtract = sum1.subtract(sum2);
		System.out.println(subtract);
		BigDecimal multiply = sum1.multiply(sum2);
		System.out.println(multiply);

		// 4 除不尽
		BigDecimal divide2 = sum1.divide(sum2, 10, BigDecimal.ROUND_HALF_DOWN);
		System.out.println(divide2);

	}

}
