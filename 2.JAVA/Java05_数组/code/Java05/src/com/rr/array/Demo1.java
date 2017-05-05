package com.rr.array;

public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a = {1, 2, 3};
		//引用传递
		test(a);
		System.out.println(a[0]);
		
		//值传递
		test1(10);
	}
	
	static void test(int[] arr){
		arr[0] = 10;
	}
	
	static void test1(int c){
		
	}

}
