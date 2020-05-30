package com.filemaker.icefantasy.dao.connection.bean;

import org.json.JSONObject;

/**
 * 数据库连接信息管理类
 * 
 * @author lenovo 王淳诚 2587311267@qq.com
 */
public class FileMakerConnection {

	private String token; // toker fm connection

	private JSONObject messages; // 返回的连接信息

	private String databaseId = "0"; // 0、1、2 数据库号

	private static FileMakerConnection singleton = null;

	/**
	 * 获取单例的方法
	 * 
	 * @return 计会芳 ： 王淳诚 25887311267@qq.com 2020年5月27日 上午8:54:40
	 */
	public static FileMakerConnection getInstance() {
		if (singleton == null) {
			singleton = new FileMakerConnection();
		}
		return singleton;
	}

	private FileMakerConnection() {
		super();
	}

	private FileMakerConnection(String token, JSONObject messages) {
		super();
		this.token = token;
		this.messages = messages;
	}

	private FileMakerConnection(String token, JSONObject messages, String databaseId) {
		super();
		this.token = token;
		this.messages = messages;
		this.databaseId = databaseId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JSONObject getMessages() {
		return messages;
	}

	public void setMessages(JSONObject messages) {
		this.messages = messages;
	}

	public String getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(String databaseId) {
		this.databaseId = databaseId;
	}

	@Override
	public String toString() {
		return "FileMakerConnection [token=" + token + ", messages=" + messages + ", databaseId=" + databaseId + "]";
	}

}
