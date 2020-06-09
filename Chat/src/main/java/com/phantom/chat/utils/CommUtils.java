package com.phantom.chat.utils;

import java.util.UUID;

public class CommUtils {
	
	/**
	 * 获取随机 uuid 
	 * @return
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年6月2日  下午10:37:19
	 */
	public static String randomUUID() {
		return UUID.randomUUID().toString().substring(0, 15);
	}
	
	/**
	 * 生成 六位数验证码
	 * @return
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年6月3日  下午10:41:45
	 */
	public static String randomNum() {
		return String.valueOf((int)(Math.random() * 1000000 ));
	}
	
	
}
