package com.rr.fengzhuang;

public class Person {
	//使用缺省的修饰符
	String name;
	
	public int age;
	protected int height;
	private int weight;
	
	//使用缺省的访问修饰符
	void run(){
		name = "lisi";
		age = 10;
		height = 100;
		weight = 50;
	}
	
	public void eat(){
		
	}
	
	protected void drink(){
		sleep();
	}
	
	private void sleep(){
		
	}
	
}
