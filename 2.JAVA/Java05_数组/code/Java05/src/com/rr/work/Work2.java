package com.rr.work;

public class Work2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		printGraph('C');
	}

	
	static void printGraph(char c){
		
		for(int i = 1; i <= c - 'A' + 1; i++){
			
			for(int j = 1; j <= i; j++){
				
				System.out.print((char)('A' + j - 1));
			}
			System.out.println();
			
		}
		
	}
	
}
