package com.rr.work;

public class Car {
	int speed;
	
	public Car(int speed){
		this.speed = speed;
	}
	//汽车行驶在路上
	void runOnRoad(Road road){
		
		int time = road.length / speed;
		System.out.println(time);
	}
}
