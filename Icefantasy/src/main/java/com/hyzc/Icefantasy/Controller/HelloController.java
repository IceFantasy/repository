package com.hyzc.Icefantasy.Controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
	
@Controller
public class HelloController {
	
	/*
	 * @RequestMapping({"/","/index.html"}) public String index() { return "index";
	 * }
	 */	
    @ResponseBody
    @RequestMapping("/hello")
    public  String hello(@RequestParam("user") String user){
//        if(user.equals("aaa")){
//            throw new UserNotExistException();
//        }
    	System.out.println("user: " + user);
        return "Hello World";
    }
    
    /**
     * 封装数据在数据显示 
     * @param map
     * @return
     * lenovo ： 王淳诚  25887311267@qq.com 
     * 2019年11月23日  下午8:27:30
     */
    // 查出一些数据， 在页面展示
    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
    	map.put("hello", "<h1>你好！！</h1>");
    	map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu1", "zhaoliu" ));
    	return "success";
	}
    


}





























