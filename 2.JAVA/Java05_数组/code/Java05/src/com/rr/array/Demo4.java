package com.rr.array;

import java.util.Arrays;

public class Demo4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a = {12, 34, 2, 23, 9};
		
		//默认升序
		Arrays.sort(a);
		
		for (int v : a) {
			System.out.println(v);
		}
		
		System.out.println(a);
		//讲数组内容转换为字符串形式
		String str = Arrays.toString(a);
		System.out.println(str);
		
		//二分查找
		//第一个参数，待查找的数组，第二个参数，要查找的值
		int index = Arrays.binarySearch(a, 23);
		System.out.println(index);
	}

}
