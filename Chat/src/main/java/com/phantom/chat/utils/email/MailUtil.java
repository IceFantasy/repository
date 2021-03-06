package com.phantom.chat.utils.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
		
    public static boolean sendMail(String email,String title, String emailMsg) {
        String from = "phantom2587311267@163.com";                 // 邮件发送人的邮件地址
        String to = email;                                         // 邮件接收人的邮件地址
        final String username = "phantom2587311267@163.com";      //发件人的邮件帐户
        final String password = "wcc19980924";                       //发件人的邮件授权码
        
        //定义Properties对象,设置环境信息
        Properties props = System.getProperties();

        //设置邮件服务器的地址
        props.setProperty("mail.smtp.host", "smtp.163.com"); // 指定的smtp服务器
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");//设置发送邮件使用的协议
        //创建Session对象,session对象表示整个邮件的环境信息
        Session session = Session.getInstance(props);
        //设置输出调试信息
        session.setDebug(true);
        try {
            //Message的实例对象表示一封电子邮件
            MimeMessage message = new MimeMessage(session);
            //设置发件人的地址
            message.setFrom(new InternetAddress(from));
            //设置主题
            message.setSubject(title);
            //设置邮件的文本内容
            //message.setText("Welcome to JavaMail World!");
            message.setContent((emailMsg),"text/html;charset=utf-8");
            //从session的环境中获取发送邮件的对象
            Transport transport=session.getTransport();
            //连接邮件服务器
            transport.connect("smtp.163.com",25, username, password);
            //设置收件人地址,并发送消息
            transport.sendMessage(message,new Address[]{new InternetAddress(to)});
            transport.close();
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean sendEmail(String email) {
        String activeCode = "";
        for (int i = 0;i<6;i++){
            activeCode=(int)(Math.random()*10)+activeCode;
        }
        return MailUtil.sendMail(email,"博客园账户邮箱激活",
                "验证码为"+activeCode);
    }	
    
    /**
     * 发送 email 验证码 
     * @param email
     * lenovo ： 王淳诚  25887311267@qq.com 
     * 2020年6月3日  下午10:38:20
     * @return 
     */
	public static boolean sendMail(String email, String emailCode) {
        String from = "phantom2587311267@163.com";                 // 邮件发送人的邮件地址
        String to = email; 										    // 邮件接收人的邮件地址
        final String username = "phantom2587311267@163.com";      //发件人的邮件帐户
        final String password = "wcc19980924";                       //发件人的邮件授权码
        
        String title = "相逢即是缘分，欢迎来到 chat";
        String emailMsg = "验证码为：  " + emailCode;
        
        //定义Properties对象,设置环境信息
        Properties props = System.getProperties();

        //设置邮件服务器的地址
        props.setProperty("mail.smtp.host", "smtp.163.com"); // 指定的smtp服务器
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");//设置发送邮件使用的协议
        //创建Session对象,session对象表示整个邮件的环境信息
        Session session = Session.getInstance(props);
        //设置输出调试信息
        session.setDebug(true);
        try {
            //Message的实例对象表示一封电子邮件
            MimeMessage message = new MimeMessage(session);
            //设置发件人的地址
            message.setFrom(new InternetAddress(from));
			//设置主题
            message.setSubject(title);
			//设置邮件的文本内容
            //message.setText("Welcome to JavaMail World!");
            message.setContent((emailMsg),"text/html;charset=utf-8");
            //从session的环境中获取发送邮件的对象
            Transport transport=session.getTransport();
            //连接邮件服务器
            transport.connect("smtp.163.com",25, username, password);
            //设置收件人地址,并发送消息
            transport.sendMessage(message,new Address[]{new InternetAddress(to)});
            transport.close();
            System.out.println("MailUtil.sendMail  发送验证码成功！ 验证码： " + emailCode);
            return true;//
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("MailUtil.sendMail  发送验证码错误 ！ 验证码： " + emailCode);
            return false;
        }		
	}
    
}