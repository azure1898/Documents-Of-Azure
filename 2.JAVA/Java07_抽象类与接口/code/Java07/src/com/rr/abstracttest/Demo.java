package com.rr.abstracttest;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bird b = new Bird();
		b.drink();
		b.eat();
		b.age = 10;
		
		//抽象类不能直接创建对象
		//Animal animal = new Animal();
	}

}
