package com.filemaker.icefantasy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.filemaker.icefantasy.dao.FileMakerDao;
import com.filemaker.icefantasy.service.FileMakerService;
import com.filemaker.icefantasy.util.CommUtils;

import net.sf.json.JSONObject;

@Service
public class FileMakerServiceImpl implements FileMakerService{
	
	
	@Autowired
	private FileMakerDao fileMakerDao;
	
	
	/**
	 * 添加数据的操作 
	 */
	public String add(JSONObject paramJson) {
		// 1. 获取请求参数 layouts addData
		String layouts = paramJson.getString("layouts");
		String addData = paramJson.getString("addData");// json 
		
		// 2. 执行添加业务 
		String result = fileMakerDao.insertByLayouts(layouts, addData);
		
		// 写入图片 a.result[recordId]  b.
		JSONObject fileUpload = paramJson.getJSONObject("fileUpload");
		boolean isFileupload = (fileUpload != null);
		if (isFileupload) {
			String recordId = JSONObject.fromObject(result).getJSONObject("response").getString("recordId");
			String field = CommUtils.toBrowserCode(fileUpload.getString("field"), "utf-8");// 禁止使用中文
			String filePath = fileUpload.getString("filePath");// 避免特殊字符 \ / 
			
			fileUpload(null, JSONObject.fromObject("{\"layouts\": \""+layouts+"\",\"recordId\": \""+recordId+"\""//
					+ ",\"field\": \""+field+"\",\"filePath\": \""+filePath+"\"}"));
		}
		// 返回数据再次封装
		return result;
	}
	
	/**
	 * 查询数据的方法： 当指定recordId查询指定数据，不指定查询前100条
	 */
	public String query(String layouts, String recordId) {
		return fileMakerDao.get(layouts, recordId);
	}
	
	/**
	 * 更新数据
	 */
	public String update(JSONObject paramJson) {
		// 1. 获取请求参数 layouts addData
		String layouts = paramJson.getString("layouts");
		String recordId = paramJson.getString("recordId");
		String updateData = paramJson.getString("updateData");// json 
		// 2. 执行修改功能 
		String result = fileMakerDao.updateByRecordId(layouts,recordId ,updateData);
		
		// 写入图片 a.result[recordId]  b.
		JSONObject fileUpload = paramJson.getJSONObject("fileUpload");
		boolean isFileupload = (fileUpload != null);
		if (isFileupload) {
			String field = CommUtils.toBrowserCode(fileUpload.getString("field"), "utf-8");// 禁止使用中文
			String filePath = fileUpload.getString("filePath");// 避免特殊字符 \ / 
			
			fileUpload(null, JSONObject.fromObject("{\"layouts\": \""+layouts+"\",\"recordId\": \""+recordId+"\""//
					+ ",\"field\": \""+field+"\",\"filePath\": \""+filePath+"\"}"));
		}
		
		return result;
	}
	
	/**
	 * 根据 recordid 进行删除
	 */
	public String delete(String layouts, String recordId) {
		return fileMakerDao.delete(layouts, recordId);
	}
	
	/**
	 * 文件上传的方法
	 */
	public String fileUpload(MultipartFile file, JSONObject paramJson) {
		String layouts = paramJson.getString("layouts");
		String recordId = paramJson.getString("recordId");
		String field = paramJson.getString("field");
		String filePath = paramJson.getString("filePath");
		return fileMakerDao.fileUpload(layouts, recordId, field, file, filePath);
	}
	/**
	 * 复杂的查询接口
	 */
	public String findParam(String layouts, String complexParam) {
		return fileMakerDao.find(layouts, complexParam);
	}

	
	

}
