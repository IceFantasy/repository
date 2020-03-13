package com.atguigu.tests;

/**
 * 赋值操作最后算 
 * @author lenovo
 * 王淳诚 2587311267@qq.com
 */
public class SelfIncrementTest {
	
	public static void main(String[] args) {
		int i = 1;// 局部变量表 操作数栈 
		i = i++;// i：1--> 2 --> 1 
		int j = i++;// i: 2, j:1
		int k = i + ++i * i++;// i:3 -> 4  j:2 k:
		System.out.println("i : " + i);
		System.out.println("j : " + j);
		System.out.println("k : " + k);
		
	}

}
