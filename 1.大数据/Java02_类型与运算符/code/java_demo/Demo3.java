
public class Demo3{

	public static void main(String[] args){
		
		//d是浮点型变量，占8字节
		//100是整型(int)数据,占4字节
		//表示范围小的数据，赋值给表示范围大的变量，隐式类型转换
		double d = 100;
		System.out.println(d);

		//将范围大的数据赋值给范围小的变量，需要进行强制类型转换
		// (要转换的类型)数据
		int i = (int)12.3;
		System.out.println(i);


		double d1 = 12.6;
		i = (int)(d1 + 0.5);
		System.out.println(i);

		byte b = 97;
		System.out.println((char)b);

	}
}