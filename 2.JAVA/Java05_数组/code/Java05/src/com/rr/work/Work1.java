package com.rr.work;

public class Work1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 10;
		int b = 20;
		String c = "-";
		System.out.println(cal(a, b, c));
	}
	
	static int cal(int a, int b, String c){
		int v = 0;
		switch(c){
		case "+":
			v = add(a, b);
			break;
		case "-":
			v = sub(a, b);
			break;
		case "*":
			v = mul(a, b);
			break;
		case "/":
			v = div(a, b);
			break;
		}
		
		return v;
	}
	
	static int add(int a, int b){
		return a + b;
	}
	
	static int sub(int a, int b){
		return a - b;
	}
	
	static int mul(int a, int b){
		return a * b;
	}
	
	static int div(int a, int b){
		if(b == 0){
			throw new RuntimeException("除数不能为0");
		}
		return a / b;
	}

}
