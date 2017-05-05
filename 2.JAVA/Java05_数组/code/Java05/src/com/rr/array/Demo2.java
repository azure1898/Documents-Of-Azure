package com.rr.array;

public class Demo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a = {23, 12, 34, 2, 56};
		//选择排序
		for(int i = 0; i < 5 - 1; i++ ) {
			
			for(int j = i + 1; j < 5; j++){
				//if(a[i] > a[j]){//大于，交换数据，最终得到一个升序的结果
				if(a[i] < a[j]){//小于，交换数据，最终得到一个降序的结果
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		//输入foreach 然后 alt + /
		for (int v : a) {
			System.out.println(v);
		}
		
	}

}
