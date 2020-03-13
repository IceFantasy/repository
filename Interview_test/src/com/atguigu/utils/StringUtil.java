package com.atguigu.utils;

public class StringUtil {

	/**
	 * 编写一个工具类 StringUtil, 提供方法 int compare(char[] v1 ,char[] v2)方法，比较字符串
		v1,v2 ,如果按照字符顺序 v1>v2 则 return 1 ,v1=v2 则 return 0, v1<v2 则 return -1。
	 * @param c1
	 * @param c2
	 * @return
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年3月3日  下午3:28:02
	 */
	public static int compare(char[] c1, char[] c2) {
		String s1 = new String(c1);
		String s2 = new String(c2);
		int result = s1.compareTo(s2);
		return result == 0 ? 0 : (result > 0 ? 1 : -1 );
	}
	
	

}
