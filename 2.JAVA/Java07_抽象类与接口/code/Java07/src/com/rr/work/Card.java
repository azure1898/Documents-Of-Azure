package com.rr.work;

public class Card {
	String name;
	String tel;
	String company;
	String qq;
	
	public Card(String name, String company, String tel, String qq){
		this.name = name;
		this.company = company;
		this.tel = tel;
		this.qq = qq;
		//System.out.println("test");
	}
	
	void printInfo(){
		System.out.println(name + "," + company);
	}
}
