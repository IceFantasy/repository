package com.atguigu.tests.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String args[]) throws Exception {
		String ClientMessage;
		String ServerMessage = null;
		ServerSocket serversocket = new ServerSocket(5557);// 端口要和客户端对应
		System.out.println("正在监听 5555 端口");//
		while (true) {
			Socket collection = serversocket.accept();// 调用 accept()函数，建立 TCP 连接
			DataInputStream fromClient = new DataInputStream(collection.getInputStream());
			DataOutputStream toClient = new DataOutputStream(collection.getOutputStream());

			toClient.writeBytes(ServerMessage);// 写到服务器端
			System.out.println("成功建立 TCP 连接");
		}
	}
}
