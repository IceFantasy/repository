package com.atguigu.tests.treasure;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.junit.Test;

import com.atguigu.tests.treasure.singleton.DoubleCheckLock;

/**
 *  创建线程的方式 线程的方法休眠声明周期  servlet的声明周期 mvc 框架 sql 
 * @author lenovo
 * 王淳诚 2587311267@qq.com
 */
public class TreasureTest {
	//请编写一socket的程序，客户端向服务器端发送字符串，服务器端在返回相同的字符串。（类似echo功能）
	
	@SuppressWarnings("resource")
	@Test
	public void testP540Client() throws Exception {
		// 与服务器建立连接
		Socket socket = new Socket("localhost", 8888);
		
		// 要发送给服务器端的信息
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		pw.write("\n\n----\n你好我是客户端 \n----\n\n");
		pw.flush();
		
		socket.shutdownOutput();
		
		// 从服务端接收信息
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String info = null;
		while ((info = br.readLine()) != null) {
			System.out.println("我是客户端，服务器返回的信息：" + info);
		}
		br.close();
		is.close();
		pw.close();
		os.close();
		socket.close();
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testP540Server() throws Exception {
		// 创建服务端socket
		ServerSocket serverSocket = new ServerSocket(8888);
		// 创建客户端socket
		Socket socket = new Socket();
		// 循环监听等待客户端的连接
		while (true) {
			// 监听客户端
			socket = serverSocket.accept();
			// 使用匿名内部类 
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String info = null;
			
			while ((info = br.readLine()) != null) {
				System.out.println("我是服务器，客户端说： " + info);
			}
			
			socket.shutdownInput();
			
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("我是服务器欢迎访问！！");
			pw.flush();
		}
		
		
	}
	
	
	/**
	 * echo
	 * 
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年2月26日  上午10:01:54
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	@SuppressWarnings("resource")
	@Test
	public void testP540SocketClient() throws UnknownHostException, IOException {// 客户端
		String clientMessage;// 来自用户输入的信息
		String serverMessage;// 服务器端的信息
		// 1. 和服务器创建连接
		Socket clientSocket = new Socket("localhost", 5557);// 参数是本机地址和端口，客户端套接字，发起TCP连接
		
		// 字符读取流获取从键盘输入的字符
		BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
		
		// 获取从服务器端的流，建立套接字输入流
		BufferedReader fromServer = new BufferedReader(// 
				new InputStreamReader(clientSocket.getInputStream()));
		
		// 建立套接字输入流
		DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
		clientMessage = fromUser.readLine();// 读取从用户的输入
		toServer.writeBytes(clientMessage);// 写到服务器端
		serverMessage = fromServer.readLine();// 从服务端读取
		clientSocket.close();// 关闭套接字连接 
	}
	
	// 服务端
	@SuppressWarnings("resource")
	@Test
	public void testP540SocketServer() throws Exception {// 客户端
		String clientMessage;
		String serverMessage = null;
		
		ServerSocket serverSocket = new ServerSocket(5557);// 端口要和客户端对应
		System.out.println("正在监听5557端口");//
		while (true) {
			Socket collection = serverSocket.accept();// 调用accept()函数，建立TCP连接
			DataInputStream fromClient = new DataInputStream(collection.getInputStream());
			DataOutputStream toClient = new DataOutputStream(collection.getOutputStream());
			clientMessage = fromClient.readUTF();// 接收来自客户端的信心
			toClient.writeBytes(serverMessage);// 写到服务器端
			System.out.println("成功建立TCP连接");
		}
		
	}
	
	// 使用原生的方式连接数据库  
	@Test
	public void testP538() {
		Connection conn = null;
		String sql;
		// MySQL的JDBC URL编写方式： jdbc:mysql://主机名称：连接端口/数据库的名称？参数=值
		// 避免中文乱码要指定 userUnicode 和 characterEndoding 
		String url = "jdbc:mysql://localhost:3306/icefantasy"
				+ "?user=root"
				+ "&password=root"
				+ "&useUnicode=true"
				+ "&characterEncoding=UTF8";
		// oralce: url:jdbc:Oracle:thin:@localhost:1521:orcl
		// 			driver = oracle.jdbc.driver.OracleDriver
		ResultSet rs = null;
		Statement stmt = null;
		// 使用下面的语句加载mysql驱动，可以通过 class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种方式
		try {
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			// or:
//			Driver driver = new com.mysql.jdbc.Driver();
			System.out.println("成功加载MySQL驱动程序");
			// 一个Connection 代表一个数据库连接
			conn = DriverManager.getConnection(url);
			// statement 里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
			stmt = conn.createStatement();
			sql = "select * from user";
			rs = stmt.executeQuery(sql);// 执行功能的jdbc接口 
			System.out.println(rs);
			while (rs.next()) {// 遍历 user 表中的所有数据
				String name = rs.getString("userName");
				System.out.println("姓名是 ： " + name);// 假如user表中的自段为name
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
				try {
					if (rs != null)
						rs.close();// 关闭结果集
					if (stmt != null) 
						stmt.close();// 关闭执行环境
					if (conn != null)
						conn.close();// 关闭数据库连接
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
	}
	
	/**
	 * 创建线程的方式 线程的方法休眠声明周期  servlet的声明周期 mvc 框架 sql 
	 * 
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年2月25日  上午8:04:52
	 */
	@Test
	public void testP517() {
		DoubleCheckLock instance1 =  DoubleCheckLock.getInstance();
		DoubleCheckLock instance2 =  DoubleCheckLock.getInstance();
		System.out.println(instance1 == instance2);
		
	}
	
	
	/**
	 * 利润与奖金
	 * 
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年2月25日  上午7:41:31
	 */
	@SuppressWarnings("resource")
	@Test
	public void testP516() {
		float jiangjin = 0;
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入利润 ：____     ");
		int num = scan.nextInt();
		if (num <= 100000) {
			jiangjin = (float) (num * 0.1);
		}else if (num <= 200000) {
			jiangjin = (float) ((num - 100000) * 0.075 + 100000 * 0.1);
		}else if (num <= 400000) {
			jiangjin = (float) ((num - 200000) * 0.5 + 100000 * 0.175);
		}else if (num <= 600000) {
			jiangjin = (float) ((num - 400000) * 0.3 + 100000 * 0.175 + 200000 * 0.5);
		}else if (num <= 1000000) {
			jiangjin = (float) ((num - 600000) * 0.015 + 100000 * 0.175 + 200000 * 0.5 + 200000 * 0.3);
		}else {
			jiangjin = (float) ((num - 1000000) * 0.01 + 100000 * 0.175
					+ 200000 * 0.5 + 200000 * 0.3 + 400000 * 0.015);
		}
		System.out.println("奖金 ： " + jiangjin);
	}
	
	/**
	 * 根据指定时间，输出是当前一年中的第几天 
	 * 
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年2月25日  上午7:21:42
	 */
	@Test
	public void testP515() {
		// 定义存储
		int year = 0, month = 0, day = 0;
		// 提示用户输出 
		System.out.print("请输入年月日（比如 1990-1-1）： ");
		// 接收用户输入，切记，scanf 中 "" 内的格式是什么 ，输入格式必须一致
		Scanner input = new Scanner(System.in);
		System.out.println("请输入年份 比如 （1990）___  : ");
		year = input.nextInt();
		System.out.print("请输入月份 比如 （1）___  : ");
		month = input.nextInt();
		System.out.print("请输入日比如 （21）___  : ");
		day = input.nextInt();
		// 定义一个数组存放每个月的天数
		int[] dayOfMonth = {31, 28, 31, 
				30, 31, 30, 
				31, 31, 30, 
				31, 30, 31};
		// 判断是否不是闰年
		boolean flag = (year % 400 == 0) ||// 比较长的语句可以切开 
				(year % 4 == 0 && year % 100 != 0);
		if (flag) {
			// 闰年二月29 天
			dayOfMonth[1] = 29;
		}
		// 定义变量记录是第几天
		int whichDay = 0;
		// 例如 3月20日是一年的第几天，计算方法1月的天数 + 2 月的天数 + 20 
		for (int i = 0; i < month - 1; i++) {
			whichDay += dayOfMonth[i];
		}
		whichDay += day;
		System.out.println(" 是一年的第" + whichDay + "天 。 ");
	}
	
	/**
	 * 50瓶饮料 
	 * 
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年2月25日  上午7:13:20
	 */
	@Test
	public void testP514() {
		int n = 50;// 初始化饮料总数 
		int i = 0;// 兑换次数
		while (true) {
			n -= 3;// 喝了三瓶
			n++;// 瓶换一瓶 
			i++;// 兑换次数 +1
			if (n < 3) {
				System.out.println("共喝了" + (50 + i) + " 瓶 ");
				break;
			}
		}
	}
	
	@Test
	public void testP496() {
		int i = 0;
		for (foo('A'); foo('B') && (i < 2); foo('C')) {
			i++;
			foo('D');
		}
	}
	private static boolean foo(char c) {
		System.out.print(c);
		return true;
	}

}
