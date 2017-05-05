package com.rr.fengzhuang;

public class Dog {
	private String name;
	private int age;
	
	//set方法，通过set方法对成员变量（属性）进行赋值
	//方法名必须以set开头，set后面跟着设置的变量的名字，所有单词首字母大写
	public void setName(String name){
		this.name = name;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	//get方法，用于获取某个成员变量的值
	//方法名以get开头，后面跟着获取的变量的名字，所有单词首字母大写
	public String getName(){
		return name;
	}
	
	public int getAge(){
		return age;
	}
	
	public void bark(){
		System.out.println("~~~wow~~~");
	}
	
}
