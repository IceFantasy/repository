package com.atguigu.tests.treasure.singleton;

/**
 * 单例模式  : 双重查锁模式
 * @author lenovo
 * 王淳诚 2587311267@qq.com
 */
public class DoubleCheckLock {
	
	private static DoubleCheckLock instance = null;
	
	private DoubleCheckLock() {
	}
	
	public static DoubleCheckLock getInstance() {
		if (instance == null) {
			synchronized (DoubleCheckLock.class) {
				if (instance == null) {
					instance = new DoubleCheckLock();
				}
			}
		}
		return instance;
	}
	

}
