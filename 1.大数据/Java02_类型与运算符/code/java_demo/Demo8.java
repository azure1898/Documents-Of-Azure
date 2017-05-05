
public class Demo8{

	public static void main(String[] args){
		// 关系表达式，返回的值为true或者false
		System.out.println(10 > 3);
		System.out.println(10 < 3);
		System.out.println(10 == 3);
		System.out.println(10 != 3);

		//写个表达式，表示a是否大于0，小于100
		int a = 1000;
		// 0 < a < 100
		//0 < a得到true，比较变为 true < 100
		//System.out.println(0 < a < 100);

		//逻辑表达式，返回结果为tru或者false
		System.out.println(a > 0 || a < 100);

		System.out.println(!(a > 0 && a < 100));


		// 判断是否闰年
		//能被4整除，但是不能被100整除，或者能被400整除
		int year = 2016; 
		System.out.println( (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 );
		


		//求两个值中的最大值
		int a1 = 10;
		int b = 20;
		int max = a1 > b ? a1 : b;
		System.out.println(max);
	}
}




