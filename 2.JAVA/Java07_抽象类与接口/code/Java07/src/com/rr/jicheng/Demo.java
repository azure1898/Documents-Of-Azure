package com.rr.jicheng;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bird bird = new Bird();
		bird.name = "Polly";
		bird.eat();
		bird.drink();
		bird.Sleep();
		//父类私有的方法，子类对象不能访问
		//bird.Play();
		bird.fly();
		
		
//		Animal animal = new Animal();
//		animal.eat();
		//父类的对象不能调用子类的方法
		//animal.fly();
		
		Animal.staticMethod();
		Bird.staticMethod();
		
		Father f = new Father();
		f.jump();
		Son s = new Son();
		s.jump();
		
		s.sleep();
		s.run();
	}

}
