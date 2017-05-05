
public class Demo9{

	public static void main(String[] args){
		
		System.out.println(1 & 1);
		System.out.println(1 & 0);
		System.out.println(0 | 0);
		System.out.println(1 | 0);
		System.out.println(1 ^ 1);
		System.out.println(1 ^ 0);
		System.out.println(~1);
		
		int a = 10;//明文
		int k = 6;//密匙
		int b = a ^ k;//加密后数据
		System.out.println(b);
		System.out.println(b ^ k);//解密

		//右移1位相当于除以2
		System.out.println(10 >> 1);
		//左移1位相当于乘以2
		System.out.println(10 << 1);

	}

}