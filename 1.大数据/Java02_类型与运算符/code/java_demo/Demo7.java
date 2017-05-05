

public class Demo7{

	public static void main(String[] args){
		
		int a = 10;
		//++放在变量前，先对变量执行+1操作，然后再参与整个表达式的运算
		//++a -> 11 (a=11),然后将11赋值给b
		int b = ++a;
		System.out.println(a + " " + b);
		
		//++放在变量后面，先取变量的值，参与运算，然后变量执行+1操作
		//a++ ->11(a的值)，将11赋值给b，然后a执行+1操作
		b = a++;
		System.out.println(a + " " + b);
		
		a = 10;
		int c = a++ + ++a;
		//       10 + 12
		System.out.println(a + " " + c);

		double d = 12.5;
		d++;// ++d; //作为一条单独的语句，两种写法等价
		System.out.println(d);

	}
}



