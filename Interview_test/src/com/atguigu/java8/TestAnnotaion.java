package com.atguigu.java8;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

public class TestAnnotaion {
	// 现在还没有内置 checker framwork 
//	private @NonNull Object obj = null;
	
	@Test
	public void test1() throws Exception {
		Class<TestAnnotaion> clazz = TestAnnotaion.class;
		Method m1 = clazz.getMethod("show");
		
		MyAnnotation[] ma = m1.getAnnotationsByType(MyAnnotation.class);
		
		Arrays.asList(ma).stream()
					.map((ma1) -> ma1.value())
					.forEach(System.out::println);
		
		
	}
	
	@MyAnnotation("hello")
	@MyAnnotation("world")
	public void show(@MyAnnotation("abc") String str) {// 注解类型 TYPE_PARAMETER
		
	}
	

}
