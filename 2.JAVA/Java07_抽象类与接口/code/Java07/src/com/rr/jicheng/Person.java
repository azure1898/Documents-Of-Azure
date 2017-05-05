package com.rr.jicheng;

//final修饰的类不能被继承
public final class Person {
	//final修饰的变量，不能修改，相当于常量
	final int age = 10;
	final double PI = 3.14;
	//如果final修饰的常量没有赋初值，就必须在构造方法中赋初值
	final int weight;
	
	//
	public static final int HEIGHT = 100;
	
	public Person() {
		// TODO Auto-generated constructor stub
		weight = 10;
	}
	
	public Person(int weight){
		this.weight = weight;
	}
	
	void test(){
		int a = ConstValue.MAX_SCOPE - 1;
		//age = 20;
	}
	//final修饰的形参，在方法中不能修改值
	void test1(final int v){
		//v = 100;
	}
}
