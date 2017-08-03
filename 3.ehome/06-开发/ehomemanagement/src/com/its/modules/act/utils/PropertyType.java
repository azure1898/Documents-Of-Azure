/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.act.utils;

import java.util.Date;

/**
 * 属性数据类型
 * @author Jetty
 * @version 2013-11-03
 */
public enum PropertyType {
	
	S(String.class), 
	I(Integer.class), 
	L(Long.class), 
	F(Float.class), 
	N(Double.class),
	D(Date.class), 
	SD(java.sql.Date.class), 
	B(Boolean.class);

	private Class<?> clazz;

	private PropertyType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getValue() {
		return clazz;
	}
}