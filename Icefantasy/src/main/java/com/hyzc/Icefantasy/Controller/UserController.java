package com.hyzc.Icefantasy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyzc.Icefantasy.service.UserService;

/**
 * @Author:0xOO
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */
@RestController
@RequestMapping("/testBoot")
public class UserController {
	
    @Autowired
    private UserService userService;
    
    @ResponseBody
    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id){
    	
    	String user = userService.Sel(id).toString();
    	
        return user;
    }
}
