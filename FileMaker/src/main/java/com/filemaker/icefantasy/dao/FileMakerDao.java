package com.filemaker.icefantasy.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.filemaker.icefantasy.dao.connection.FileMakerDriverManager;
import com.filemaker.icefantasy.dao.connection.bean.FileMakerConnection;
import com.filemaker.icefantasy.dao.utils.FileMakerRequestUtil;

@Component
public class FileMakerDao {
	
	private String token;// token 
	
	{
		
		try {
			FileMakerConnection conn = FileMakerDriverManager.getConnection("https://aliapi.goodluckpacking.com/fmi/data/v1/databases/test/sessions"
					, "test", "test");
			token = conn.getToken();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 在连接中获取token 
		token = FileMakerConnection.getInstance().getToken();
		System.out.println("token : " + token);  // 有问题待优化，在当前类加载后 connection 才进行更新 
	}
	
	public String insertByLayouts(String layouts, String addData) {
		return FileMakerRequestUtil.insertPost(token, layouts, addData);
	}
	
	
	public String get(String layouts, String recordId) {
		return FileMakerRequestUtil.selectGet(token, layouts, recordId);
	}
	
	public String updateByRecordId(String layouts, String recordId, String updateData) {
		return FileMakerRequestUtil.updatetPost(token, layouts, recordId, updateData);
	}

		
	public String delete(String layouts, String recordId) {
		return FileMakerRequestUtil.deleteGet(token, layouts, recordId);
	}
	
	// {"layouts":"layouts","recordId":"recordId", "field":"field","filePath":"filePath"}
	public String fileUpload(String layouts, String recordId, String field,//
			MultipartFile file, String filePath) {//
		return FileMakerRequestUtil.fileUploadPost(token,//
				layouts, recordId, field,//
				file,//
				filePath);//
	}
	
	/**
	 * 复杂的查询数据方法
	 * @param layouts
	 * @param complexParam
	 * @return
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年5月30日  下午5:52:21
	 */
	public String find(String layouts, String complexParam) {
		return FileMakerRequestUtil.findGet(token, layouts, complexParam);
	}
	
}
























