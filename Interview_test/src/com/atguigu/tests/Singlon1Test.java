package com.atguigu.tests;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.atguigu.singleton.Singleton1;
import com.atguigu.singleton.Singleton3;
import com.atguigu.singleton.Singleton4;
import com.atguigu.singleton.Singleton5;

public class Singlon1Test {
	
	/**
	 * 测试懒汉式单例 : 多线程
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年2月21日  下午8:59:25
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@Test
	public void singeton4ThreadTest() throws InterruptedException, ExecutionException {
		Callable<Singleton5> c = new Callable<Singleton5>() {

			@Override
			public Singleton5 call() throws Exception {
				return Singleton5.getInstance();
			}
		};
		
		ExecutorService es = Executors.newFixedThreadPool(2);// 创建线程池 
		Future<Singleton5> f1 = es.submit(c);
		Future<Singleton5> f2 = es.submit(c);
		
		Singleton5 s1 = f1.get();
		Singleton5 s2 = f2.get();
		
		System.out.println("s1 == s2 : " + (s1 == s2)
				+ "\n s1: " + s1
				+ "\n s2: " + s2);
		
		es.shutdown();
		
	}
	
	/**
	 * 测试懒汉式单例 
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年2月21日  下午8:59:25
	 */
	@Test
	public void singeton4Test() {
		Singleton4 s1 = Singleton4.getInstance();
		Singleton4 s2 = Singleton4.getInstance();
		System.out.println("两个对象是否相同 : " + (s1 == s2) 
				+ "\ns1 : " + s1 + "\ns2 : " + s2);
		
		
	}
		
		
		
		
	@Test
	public void singeton3Test() {
		Singleton3 s = Singleton3.INSTANCE;
		System.out.println(s);
	}

	
	@Test
	public void singeton11Test() {
		
		Singleton1 s = Singleton1.INSTANCE;
		
		System.out.println(s);
		
	}

}
