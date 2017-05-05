
public class Demo6{

	public static void main(String[] args){
		
		// "/"两边都是整数，相当于取整
		int a = 10 / 3;
		System.out.println(a);

		double d = 10 / 3.0;
		System.out.println(d);

		int a1 = 20;
		int a2 = 30;
		double d1 = a1 / (double)a2;
		// a1 / a2 * 1.0
		System.out.println(d1);
		
		// %取余
		int a3 = 10 % 3;
		System.out.println(a3);
		double d2 = 10.5 % 2;
		System.out.println(d2);//0.5

		//+
		int a4 = 10 + 4;

		//字符串 “”括起来的内容
		// + 字符串的组合（拼接）
		System.out.println("hello" + "world");
		System.out.println("a4=" + a4);
		System.out.println(12 + "" + 4);
		System.out.println(12 + 4 + "");
		System.out.println(12 + 4);

		a4 += 10;//a4 = a4 + 10;
		System.out.println(a4);


	}

}