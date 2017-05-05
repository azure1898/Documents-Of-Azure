
//int m = 10;变量声明在类外，错误
public class Demo1{
	//成员变量
	int m = 100;
	public static void main(String[] args){
		//变量的声明
		//格式：类型 变量名
		//声明了一个整型变量a
		//根据类型，在内存中开辟一个空间，比如，int类型占四个字节
		//声明int类型变量后，在内存中开辟4个字节的空间，用于存放数据
		int a;
		//变量的赋值
		a = 10;
		//变量的输出
		System.out.println(a);
		a = 250;
		System.out.println(a);

		//变量的初始化
		//声明变量的时候直接赋值
		int b = 10;

		System.out.println(b);

		//变量（局部变量）必须赋值或者初始化后才能使用
		int c;
		//System.out.println(c);//编译报错

		int age = 10;

		int Age = 10；
	}
}





