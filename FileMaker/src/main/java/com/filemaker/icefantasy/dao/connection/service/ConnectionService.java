package com.filemaker.icefantasy.dao.connection.service;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.filemaker.icefantasy.dao.connection.FileMakerDriverManager;
import com.filemaker.icefantasy.dao.connection.bean.FileMakerConnection;
import com.filemaker.icefantasy.util.HMSJSONUtil;

import net.sf.json.JSONObject;
/**
 * 处理连接信息的 service 
 * 
 * 注： 由当前类管理 connection,在使用时直接获取 
 * @author lenovo
 * 王淳诚 2587311267@qq.com
 */
@Service
@EnableScheduling
public class ConnectionService {
	
	//  设置 connection 为单例  
	// 1. 设置定时任务   15 * 0.75  分执行一次 675000
	@Scheduled(fixedRate = 675000, initialDelay = 10)
	public void flushConnection() {
		
		FileMakerConnection conn = FileMakerConnection.getInstance();
		
		// a. 从配置filemaker-confing.json中获取配置信息
		JSONObject confingJson = HMSJSONUtil.getConfingJson();
		JSONObject databasesJson = confingJson.getJSONObject("databases").getJSONObject(conn.getDatabaseId());// "0" : 默认使用0 号数据库
			
		String url = databasesJson.getString("url");
		String username = databasesJson.getString("username");
		String password = databasesJson.getString("password");
		try {
			// b. 更新connection filemaker 连接信息 
			conn = FileMakerDriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//	FileMakerConnection.getConnection().setDatabaseId("2");
		System.out.println(new Date() + "-- 定时任务 15 * 0.75 （分）  执行，更新 connection  " + conn);
	}
	
	
}
