package com.filemaker.icefantasy.dao.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

import org.json.JSONObject;

import com.filemaker.icefantasy.dao.connection.bean.FileMakerConnection;
import com.filemaker.icefantasy.util.Base64Object;
import com.github.pagehelper.util.StringUtil;

public class FileMakerDriverManager {
	
	/**
	 * get file marker connection method
	 * 
	 * @param url
	 * @param info
	 * @return
	 * @throws SQLException lenovo ： 王淳诚 25887311267@qq.com 2020年5月25日 下午9:47:33
	 */
	public static FileMakerConnection getConnection(String url, String username, String password) throws SQLException {
		// 1. 空值判断
		boolean isEmpty = StringUtil.isEmpty(url) || StringUtil.isEmpty(username) || StringUtil.isEmpty(password);
		if (isEmpty) {
			throw new SQLException("The [url , username, password] cannot be null", "08001");
		}
		// 2. 发送请求获取 filemarker 连接信息
		String userpass = Base64Object.stringToBase64(username+":"+password);
		//  {"response":{"token":"2454fa3e4dac5a76e00c75637dcf1be9d9ac4ec3a34fa68328"},"messages":[{"code":"0","message":"OK"}]}
		String response = sendPost(url, userpass);
		
		// 3. 封装为 FileMakerConnection 
		JSONObject responseMes = new JSONObject(response);
		String token = responseMes.getJSONObject("response").getString("token");
		JSONObject messages = new JSONObject(responseMes.getJSONArray("messages").get(0).toString());
		
		// 4. 获取FileMakerConnection 封装token messages 等，file maker 连接信息
		FileMakerConnection conn = FileMakerConnection.getInstance();
		conn.setToken(token);
		conn.setMessages(messages);
		
		return conn;
	}
	// 发送post 请求，获取connection 信息 
	private static String sendPost(String url, String userpass) {
		String result = "";
		BufferedReader bufferedReader = null;
		PrintWriter out = null;
		try {
			//1、2、读取并将url转变为URL类对象
			URL realUrl = new URL(url);
			//3、打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			//4、设置通用的请求属性 https://aliapi.goodluckpacking.com/fmi/data/v1/databases/test/sessions
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("Content-Type", "application/json");     
			connection.setRequestProperty("Authorization","Basic " +userpass);/// 参照文档 https://fmhelp.filemaker.com/docs/18/en/dataapi/#fmdapi-architecture
			// 发送POST请求必须设置如下两行 
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			//5、建立实际的连接
			//connection.connect();
			//获取URLConnection对象对应的输出流
			out = new PrintWriter(connection.getOutputStream());
			//发送请求参数
//			out.print(param);
			//flush输出流的缓冲
			out.flush();
			//
			//6、定义BufferedReader输入流来读取URL的响应内容
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			System.out.println(out);
			String line;
			while(null != (line = bufferedReader.readLine())) {
				result += line;
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("发送POST请求获取connection出现异常！！！"  + e);
			e.printStackTrace();
		}finally {        //使用finally块来关闭输出流、输入流 
			try {
				if(null != out) {
					out.close();
				}
				if(null != bufferedReader) {
					bufferedReader.close();
				}
			}catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return result;
	}

}
