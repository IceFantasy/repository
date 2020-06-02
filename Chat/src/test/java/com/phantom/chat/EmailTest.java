package com.phantom.chat;

import org.junit.Test;

import com.phantom.chat.utils.email.MailUtil;


public class EmailTest {
	
	// https://www.cnblogs.com/liu1275271818/p/11778978.html 发送验证码 
	@Test
	public void tsetemail() {
//		String content = "Hello,This is a test email!!!! \\n 验证密： 2986";
//		// 参数分别为接收者邮箱、title、内容body
//		EmailHelper.sendEmail("2587311267@qq.com", "欢迎来到 chat", content);
		
		MailUtil.sendEmail("2587311267@qq.com");
		
		System.out.println("邮件发送成功 ！");
	}
	
	
	
}
