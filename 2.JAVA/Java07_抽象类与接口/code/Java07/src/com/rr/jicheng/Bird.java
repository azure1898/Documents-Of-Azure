package com.rr.jicheng;

public class Bird extends Animal{
	{
		System.out.println("Bird的构造代码块");
	}
	
	static{
		System.out.println("Bird的静态代码块");
	}
	
	public Bird(){
		System.out.println("Bird的构造方法");
	}
	
	//子类还可以拥有自己的属性和方法
	void fly(){
		//子类内部可以调用父类缺省的、public、protected的方法
		this.eat();
		drink();
		Sleep();
		//子类继承了父类私有的方法，但是在子类内部不能调用
		//Play();
		System.out.println("fly");
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
