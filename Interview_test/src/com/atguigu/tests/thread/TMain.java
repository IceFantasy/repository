package com.atguigu.tests.thread;

/**
 * 写一个多线程程序，四个线程对一个int变量，2个加1，2个减1，输出
 * @author lenovo
 * 王淳诚 2587311267@qq.com
 */
public class TMain {
	
	int j = 1;
	
	public synchronized void inc() {
		j++;
		System.out.println(Thread.currentThread().getName() + "-inc:" + j);
	}
	
	
	// 内部类 
	class T1 implements Runnable {
		@Override
		public void run() {
			inc();
		}
	}
	
	public synchronized void dec() {
		j--;
		System.out.println(Thread.currentThread().getName() + "-dec:" + j);
	}
	class T11 implements Runnable {
		
		@Override
		public void run() {
			dec();
		}
	}
	
	
	public static void main(String[] args) {
		TMain t = new TMain();
		T1 t1 = t.new T1();
		T11 t11 = t.new T11();
		
		for (int i = 0; i < 2; i++) {
			Thread thread = new Thread(t1);
			thread.start();
			
			Thread thread1 = new Thread(t11);
			thread1.start();
		}
		
	}

}





























