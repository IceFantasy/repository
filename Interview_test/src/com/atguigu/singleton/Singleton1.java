package com.atguigu.singleton;

 
/**
 * 1. 保证有一个实力 2.自行创建这个实例 4.向系统提供这个实例 hello world >>>    >>>>>> 
 * 构造器私有化    hot fix .....    测试提交单个文件 成功
 * @author lenovo
 * 王淳诚 2587311267@qq.com
 */
public class Singleton1 {
	
	public static final Singleton1 INSTANCE = new Singleton1();
	
	private Singleton1() {
		super();
	}
	
	
	
	
}
