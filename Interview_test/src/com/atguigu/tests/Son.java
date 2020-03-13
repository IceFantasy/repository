package com.atguigu.tests;

/**
 * 类加载问题
 * 不可以被重写的方法 ： final方法 静态方法 private等子类中不可见的方法 
 * 
 * @author lenovo
 * 王淳诚 2587311267@qq.com
 */
public class Son extends Father{
	
	private int i = test();
	
	private static int j = Method();
	
	static {
		System.out.print("(6)");
	}
	
	Son () {
//		super(); // 写或者不写，在子类中一定会存在，调用父类构造器 
		System.out.print("(7)");
	}
	
	{
		System.out.print("(8)");
	}
	
	

	public int test() {
		System.out.print("(9)");
		return 1;
	}

	public static int Method() {
		System.out.print("(10)");
		return 2;
	}
	
	public static void main(String[] args) {
		
		Son s1 = new Son();
		System.out.println();
		Son s2 = new Son();
		
		
	}
	
	
	
	
}



















