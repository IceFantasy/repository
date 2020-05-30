package com.filemaker.icefantasy.service;

import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONObject;

public interface FileMakerService {
	
	String add(JSONObject paramJson);
	
	String query(String layouts, String recordId);
	
	String update(JSONObject paramJson);
	
	String delete(String layouts, String recordId);
	
	String fileUpload(MultipartFile file, JSONObject paramJson);
	
	String findParam(String layouts, String complexParam);
	
	
	
}
