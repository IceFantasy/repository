package passvalue_code;

import org.junit.Test;

public class SubjectTest {
	
	/**
	 * 类的继承重载
	 * 
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年3月20日  下午3:32:46
	 */
	@Test
	public void test2() {
		Base b = new Derived(); 
		b.methodOne();
	}

	/**
	 * 快手笔试题
	 * 
	 * lenovo ： 王淳诚 25887311267@qq.com 2020年3月20日 下午1:56:58
	 */
	@Test
	public void test1() {
		System.out.println(a(666));// 最好的方法是运行一下
	}

	int a(int tab) {
		int n = tab - 1;
		n |= n >> 1;
		n |= n >> 2;
		n |= n >> 4;
		n |= n >> 8;
		n |= n >> 16;
		return n;
	}
	
	
	
	
	

}
