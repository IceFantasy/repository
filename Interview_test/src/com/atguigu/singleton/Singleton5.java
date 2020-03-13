package com.atguigu.singleton;

/**
 * 懒汉式： 延迟创建这个实例对象
 * 
 * @author lenovo 王淳诚 2587311267@qq.com
 */
public class Singleton5 {

	private static Singleton5 instance;

	private Singleton5() {
	}

	public static Singleton5 getInstance() {// 由于会产生多线程问题，使用方法锁，进行处理
		if (instance == null) {

			synchronized (Singleton5.class) {// 传入锁对象
				if (instance == null) {
					try {
						Thread.sleep(100);// 线程休眠100毫秒
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					instance = new Singleton5();
				}
			}
		}
		return instance;
	}

}
