package com.filemaker.icefantasy.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

public class CommUtils {

	/**
	 * multipartFile --> file 的方法
	 * 
	 * @param multipartFile
	 * @return lenovo ： 王淳诚 25887311267@qq.com 2020年5月30日 上午11:02:49
	 */
	public static File mulipartTofile(MultipartFile multipartFile) {
		String path = "C:\\temp";
		try {
			path = CommUtils.getClassPath("confing/temp");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File file = new File(path);
		try {
			// 使用文件中转的方式进行转换文件类型
			FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;

	}

	public static String getClassPath(String fileName) throws FileNotFoundException {
		return ResourceUtils.getFile("classpath:" + fileName).getPath();
	}

	// %E5%9B%BE%E7%89%87
	// ie=UTF-8&wd=图片
	// 把中文字符转换为带百分号的浏览器编码
	// @param word 中文字符
	// @param encoding 字符编码
	public static String toBrowserCode(String word, String encoding) {
		boolean isEnglist = isEnglish(word);
		if (isEnglist) {
			return word;
		}
		byte[] textByte;
		try {
			textByte = word.getBytes(encoding);
			StringBuilder strBuilder = new StringBuilder();

			for (int j = 0; j < textByte.length; j++) {
				// 转换为16进制字符
				String hexStr = Integer.toHexString(textByte[j] & 0xff);
				strBuilder.append("%" + hexStr.toUpperCase());
			}
			return strBuilder.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 判断是不是英文字母
	public static boolean isEnglish(String charaString) {
		return charaString.matches("^[a-zA-Z0-9]*");
	}
	
	//根据中文unicode范围判断u4e00 ~ u9fa5不全
	 public static String isChinese(String str) {
	  String regEx1 = "[\\u4e00-\\u9fa5]+";
	  String regEx2 = "[\\uFF00-\\uFFEF]+";
	  String regEx3 = "[\\u2E80-\\u2EFF]+";
	  String regEx4 = "[\\u3000-\\u303F]+";
	  String regEx5 = "[\\u31C0-\\u31EF]+";
	  Pattern p1 = Pattern.compile(regEx1);
	  Pattern p2 = Pattern.compile(regEx2);
	  Pattern p3 = Pattern.compile(regEx3);
	  Pattern p4 = Pattern.compile(regEx4);
	  Pattern p5 = Pattern.compile(regEx5);
	  Matcher m1 = p1.matcher(str);
	  Matcher m2 = p2.matcher(str);
	  Matcher m3 = p3.matcher(str);
	  Matcher m4 = p4.matcher(str);
	  Matcher m5 = p5.matcher(str);
	  if (m1.find() || m2.find() || m3.find() || m4.find() || m5.find())
	   return "中文";
	  else
	   return "英文";
	 }

}
