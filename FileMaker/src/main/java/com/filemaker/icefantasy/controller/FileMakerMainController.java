package com.filemaker.icefantasy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.filemaker.icefantasy.service.FileMakerService;

import net.sf.json.JSONObject;


/***
 * 对 file maker 进行封装的接口  
 * @author 
 * 王淳诚 2587311267@qq.com
 */
@Controller
public class FileMakerMainController {
	
	@Autowired
	private FileMakerService fileMakerService;
	
//	http://localhost:8080/HMSFilemaker/add/
	// 新增数据的接口 
	@ResponseBody
	@PostMapping("/add")
	public JSONObject add(@RequestBody JSONObject paramJson) {//
		// 传入一下格式的数据
		//body raw { "add": { "layouts": "fuzeren", "addData": { "fieldData": { "名字": "zs", "姓氏": "w", "称谓": "xiansheng", "公司": "haomeishi", "群组": "aaa", "电话": "sss", "电子邮件": "1394926@163.com" } } } }
		// 传入图片路径
		String result = fileMakerService.add(paramJson);
		
		return JSONObject.fromObject(result);
	}
	
	
//	http://localhost:8080/FileMaker/read?layouts=fuzeren&recordId=75
	// 查询数据接口
	@ResponseBody
	@RequestMapping("/read")
	public JSONObject read(String layouts, String recordId) {
		// 查询布局的，
		// 存在recordid 查询指定数据，不存在查询layouts 前100 条
		// 1. 调用dao getAll 方法，获取数据
		String result = fileMakerService.query(layouts, recordId);
		return JSONObject.fromObject(result);
	}
	
	// https://aliapi.goodluckpacking.com/fmi/data/v1/databases/test/layouts/fuzeren/?_offset=96&_limit=6
	// 查询数据接口
	@ResponseBody
	@PostMapping("/find")
	public JSONObject find(@RequestBody JSONObject paramJson) {
		//1. 获取查询条件
		String layouts = paramJson.getString("layouts");
		String complexParam = paramJson.getString("complexParam");
		System.out.println(complexParam);
		//2.根据条件进行查询
		String result = fileMakerService.findParam(layouts, complexParam);
		return JSONObject.fromObject(result);
	}
	
	
//	http://localhost:8080/FileMaker/update
	// 更改数据的接口
	@ResponseBody
	@PostMapping("/update")
	public JSONObject update(@RequestBody JSONObject paramJson) {
		// { "update": {"layouts": "fuzeren","recordId": "75","updateData": {"fieldData": {"名字": "zs","电子邮件": "李四@163.com","照片": "可以是http路径上传"}}}
		
		String result = fileMakerService.update(paramJson);
		
		return JSONObject.fromObject(result);
	}
	
//	http://localhost:8080/FileMaker/delete?layouts=fuzeren&recordId=95
	// 删除数据的接口
	@ResponseBody
	@RequestMapping("/delete")
	public JSONObject delete(String layouts, String recordId) {
		// 删除指定recordId数据 
		String result = fileMakerService.delete(layouts, recordId);
		return JSONObject.fromObject(result);
	}
	
	
	// 上传文件（图片）的接口  , 文件大小有限制
	//paramJson: {"layouts":"layouts","recordId":"recordId","field":"field","filePath":"filePath"}  upload:file
	@ResponseBody
	@PostMapping(value = "/fileUpload")
	public JSONObject fileUpload(@RequestParam(value = "file") MultipartFile file, @RequestBody JSONObject paramJson, Model model,
			HttpServletRequest request) {
		
		String result = fileMakerService.fileUpload(file, paramJson);// 提交文件/提交路径
		
		return JSONObject.fromObject(result);
	}
	
	
	
	

}
