package com.atguigu.tests.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	
	public static void main(String args[]) throws Exception {
		String clientMessage;// 来自用户输入的的信息
		String serverMessage; // 服务器端的信息
		Socket ClientSocket = new Socket("127.0.0.0", 5557);// 参数是本机地址和端口,客户端套接字，发起 TCP 连接
		BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));// 字符读取流， 获取从键盘输入的字符
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));// 获取从服务器端的流，建立套接字输入流
		DataOutputStream toServer = new DataOutputStream(ClientSocket.getOutputStream());// 建立套接 字输出流
		clientMessage = fromUser.readLine();// 读取从用户的输入
		toServer.writeBytes(clientMessage);// 写到服务器端
		serverMessage = fromServer.readLine();// 从服务器端读取
		ClientSocket.close();// 关闭套接字连接
	}
}
