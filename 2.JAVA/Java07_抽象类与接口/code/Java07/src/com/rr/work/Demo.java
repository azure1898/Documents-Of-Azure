package com.rr.work;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Card card = new Card("zhangsan", "zhiyou", "123456789", "23456");
		card.printInfo();
		card.tel = "13567890";
		card.printInfo();
		
		Car car = new Car(20);
		Road road = new Road();
		road.length = 1000;
		car.runOnRoad(road);
		
	}

}
