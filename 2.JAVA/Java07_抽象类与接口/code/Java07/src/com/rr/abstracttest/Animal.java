package com.rr.abstracttest;

//抽象类中不一定有抽象方法
//如果类中有抽象方法，那么这个类必须是抽象类
public abstract class Animal {
	String name;
	int age;
	
	//构造方法不能是抽象方法
	//public abstract Animal();
	
	void eat(){}
	
	void drink(){
		System.out.println("drink");
	}
	
	//抽象方法,只有方法的声明，没有方法的实现
	//子类必须实现抽象类中的抽象方法
	public abstract void run();
}
