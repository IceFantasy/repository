package com.phantom.chat;

import java.io.File;

import org.junit.Test;

public class FileTest {
	
	@Test
	public void renameFileName() throws Exception {
		// 1. 读取文件信息
		String urlFile = "F:\\systemprogrammer\\APP\\Git\\git\\repository\\Chat\\src\\main\\webapp\\static\\page\\登录界面_files - 副本";
		File file = new File(urlFile);
		// 2. 更改文件名 
		File[] listFiles = file.listFiles();
		for (File childfile : listFiles) {
			String fileName = childfile.getName();
			boolean isFileDownload = fileName.contains(".下载");
			if (isFileDownload) {// 当名字有 "下载" 进行替换
				childfile.renameTo(new File(urlFile + "\\" + fileName.replace(".下载", "")));
				System.out.println(childfile.getName());
			}
		}
	}
	
	
}
