package com.atguigu.singleton;

/**
 * 在内部类被加载初始化时，才创建 INSTANCE 实例对象
 * @author lenovo
 * 王淳诚 2587311267@qq.com
 */
public class Singleton6 {
	
	
	private Singleton6() {
		
	}
	
	private static class Inner {
		private static final Singleton6 INSTANCE = new Singleton6();
	}
	
	public static Singleton6 getInstance() {
		return Inner.INSTANCE;
	}

}
