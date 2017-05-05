package com.rr.car;

public class Car {
	String name;
	//Car由很多组件组成，比如包括Engine
	Engine engine;
	
//	String tyreName;
//	String tyreStyle;
	
	void run(){
		//引擎先启动
		engine.start();
		
		System.out.println("汽车开始行驶");
	}
}
