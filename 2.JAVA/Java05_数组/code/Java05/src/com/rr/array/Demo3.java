package com.rr.array;

public class Demo3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a = {23, 12, 45, 2, 43};
		//ц╟ещеепР
		for(int i = 0; i < 5 - 1; i++){
			
			for(int j = 0; j < 5 - 1 - i; j++){
				if(a[j] > a[j + 1]){
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		for (int i : a) {
			System.out.println(i);
		}
		
	}

}
