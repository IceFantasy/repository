package passvalue_code;

import java.util.Arrays;
/**
 * 比较难的代码才有挑战性  : 
 * 1. 传参 2. 包装类的不可变性 
 * @author lenovo
 * 王淳诚 2587311267@qq.com
 */
public class Exam4 {

	
	public static void main(String[] args) {
		int i = 1;
		String str = "hello";
		Integer num = 200;
		int[] arr = {1,2,3,4,5};
		MyData my = new MyData();
		
		change(i, str, num, arr, my);
		
		System.out.println("i = " + i);
		System.out.println("str = " + str);
		System.out.println("num = " + num);
		System.out.println("arr = " + Arrays.toString(arr));
		System.out.println("my.a = " + my.a);
		
	}
	
	public static void change(int j, String s, Integer n, int[] a, MyData m) {
		j += 1;
		s += "world";
		n += 1;
		a[0] = 1;
		m.a += 1;
	}

}
