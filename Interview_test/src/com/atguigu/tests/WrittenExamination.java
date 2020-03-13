package com.atguigu.tests;

import org.junit.Test;

import com.atguigu.tests.entity.A;
import com.atguigu.utils.StringUtil;

public class WrittenExamination {
	
	
	@Test
	public void stringUtilTest() {
		char[] c1 = {'1', '2'};
		char[] c2 = {'1', '1'};
		int i = StringUtil.compare(c1, c2);
		System.out.println(i);// 0 相等 
		
	}
	
	/**
	 * 1.5 S 市 A ，B 共有两个区，人口比例为 3：5 ，据历史统计 A 区的犯罪率为 0.01% ，
B 区为 0.015% ，现有一起新案件发生在 S 市，那么案件发生在 A 区的可能性有多大？
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年3月2日  下午3:07:41
	 */
	@Test
	public void P472_2() {// 做题的思路一定要想对 
		// 这道题首先得了解犯罪率是什么？犯罪率就是犯罪人数与总人口数的比。因此可以直接得出公式：( 3 0.01% ) / ( 3 
				// 0.01% + 5 * 0.015% ) = 28.6%
		System.out.println(( 0.3001 ) / ( 3 * 0.0001 + 5 * 0.00015 ));//  = 28.6%
		// A犯罪人数/ A + B 区的犯罪人数 
		
		
	}
	
	@Test
	public void P472() {
		System.out.println(foo(5));
	}
	
	int foo(int n) {
		if (n < 2)
			return n;
		return foo(n - 1) + foo(n - 2);
	}

	private void test2(A a) {
		a.age = 20;
		System.out.println("test1 方法中的 age=" + a.age);
	}

	private void test1(int a) {
		a = 5;
		System.out.println("test1 方法中的 a=" + a);
	}

	@Test
	public void P644() {
		int a = 3;
		this.test1(a);// 传递后，test1 方法对变量值的改变不影响这里的 a
		System.out.println("main 方法占用的 a=" + 1);
		A ao = new A();
		ao.age = 10;
		this.test2(ao);
		System.out.println("main 方法中的age=" + ao.age);

	}

	/**
	 * 下面程序的运行结果是? 为什么？
	 * 
	 * lenovo ： 王淳诚 25887311267@qq.com 2020年3月2日 下午2:07:20
	 */
	@Test
	public void P643Wstest() {
		String str1 = "hello";
		String str2 = "he" + new String("llo");
		String str3 = "he" + "llo";
		System.err.println(str1 == str2);
		System.err.println(str1 == str3);
	}

	/**
	 * 一个数如果恰好等于它的因子之和, 这个数就称为"完数". 例如 6 = 1+2+3。编程找出 1000 以内的所有完数。
	 * 
	 * lenovo ： 王淳诚 25887311267@qq.com 2020年3月2日 下午2:05:11
	 */
	@Test
	public void P642Wstest() {
		for (int m = 2; m < 1000; m++) {
			int s = 0;
			for (int i = 1; i < m; i++) {
				if ((m % i) == 0)
					s += i;
			}
			if (s == m) {
				System.out.print(m + " its factors are:");
				for (int j = 1; j < m; j++) {
					if ((m % j) == 0) {
						System.out.print(j);
						System.out.print(" ");
					}
				}
				System.out.println();
			}
		}

	}

}
