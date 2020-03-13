package com.atguigu.tests;

/**
 * 类加载问题
 * 
 * (5) (1) (10) (6) (4)      (3) (2) (9) (8) (7)      (4) (3) (2) (9) (8) (7) 
 * 
 * @author lenovo
 * 王淳诚 2587311267@qq.com
 */
public class Father {
	
	private int i = test();
	
	private static int j = Method();
	
	static {
		System.out.print("(1)");
	}
	
	Father () {
		System.out.print("(2)");
	}
	
	{
		System.out.print("(3)");
	}
	
	

	public int test() {
		System.out.print("(4)");
		return 1;
	}

	public static int Method() {
		System.out.print("(5)");
		return 2;
	}
	
	
	
	
}



















