

public class Demo2{

	public static void main(String[] args){
		
		byte b = 12;
		System.out.println(b);

		// 注释  //用来注释某一行  /* 语句 */注释多行
		/*
		编译报错，值超过了byte的表示范围
		byte b1 = 300;
		System.out.println(b1);
		*/


		short s = 100;
		long l = 100L;//100l
		
		//浮点型
		float f = 12.3f;
		double d = 23.4;
		System.out.println(f);
		System.out.println(d);

		//字符型
		char c = 'a';
		System.out.println(c);
		char c1 = '中';//中文字符
		System.out.println(c1);
		
		char c2 = 97;
		System.out.println(c2);

		byte b2 = 'a';
		System.out.println(b2);

		//布尔型
		boolean ret = true;//false;
		System.out.println(ret);
	}
}





