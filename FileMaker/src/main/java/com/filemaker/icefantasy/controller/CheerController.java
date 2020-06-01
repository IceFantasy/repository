package com.filemaker.icefantasy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

/**
 * 接收 对方回应的前端
 * @author lenovo
 * 王淳诚 2587311267@qq.com
 */
@Controller
public class CheerController {
	
	
	@ResponseBody
	@RequestMapping("/cheer/{flag}")
	public JSONObject read(@PathVariable("flag") String flag) {
		if ("1".equals(flag)) {// 注意对象比较要用 equals
			System.out.println("对方已同意 ");
		}
		return null;
	}
	

}
