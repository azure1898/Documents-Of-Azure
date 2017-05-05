package com.rr.jicheng;

//默认情况下，一个类默认继承自Object类
public class Animal {
	String name;
	int age;
	
	{
		System.out.println("Animal的构造代码块");
	}
	
	static{
		System.out.println("Animal的静态代码块");
	}
	
	//父类的构造方法不能被子类继承
	public Animal(){
		System.out.println("Animal的构造方法");
	}
	
	void eat(){
		
	}
	
	public void drink(){
		
	}
	
	protected void Sleep(){
		
	}
	
	private void Play(){
		
	}
	
	//静态方法
	public static void staticMethod(){
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
