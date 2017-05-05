package com.rr.jicheng;

public class Father {
	
	public Father() {
		// TODO Auto-generated constructor stub
	}
	
	public Father(int age) {
		// TODO Auto-generated constructor stub
	}
	
	public void jump(){
		System.out.println("跳了17米");
	}
	
	void sleep(){
		System.out.println("睡了可香");
	}
	
	protected void drink(){
		
	}
	//私有的方法不能重写
	private void eat(){
		
	}
	
	//用final修饰的方法，子类不能重写
	public final void run(){
		
	}
}
