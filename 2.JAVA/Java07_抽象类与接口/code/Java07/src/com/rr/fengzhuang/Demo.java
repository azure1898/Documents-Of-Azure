package com.rr.fengzhuang;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p = new Person();
		p.name = "zhangsan";
		p.age = 12;
		p.height = 120;
		//类外不能访问私有的成员变量
		//p.weight = 100;
		
		p.run();
		p.eat();
		p.drink();
		//类外不能访问私有的方法
		//p.sleep();
		
		
		Dog d = new Dog();
		d.setName("wangcai");
		d.setAge(2);
		int age = d.getAge();
		System.out.println(age);
	}

}
