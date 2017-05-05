package com.rr.jicheng;

public class Son extends Father{

	//子类的构造方法，默认会自动调用父类无参的构造方法
	//如果父类没有无参的构造方法，报错
	public Son() {
		// TODO Auto-generated constructor stub
		//调用父类的构造方法
		super(10);
		System.out.println("sub");
		//super(10);//报错，必须放在其他逻辑前面调用
	}
	
	
	//子类中重新实现了父类的方法，重写
	//重写的方法，访问修饰权限不能小于父类方法的访问权限
	
	//@Override 注解 表示方法是个重写的方法
	@Override
	public void jump() {
		System.out.println("跳了2米");
	}
	
	@Override
	void sleep() {
		// TODO Auto-generated method stub
		
		//通过super关键字可以调用父类的方法
		super.sleep();
		System.out.println("喜欢说梦话，磨牙");
	}
	
	@Override
	protected void drink() {
		// TODO Auto-generated method stub
		super.drink();
	}
		
	
	void test(){
		super.sleep();
	}
}
