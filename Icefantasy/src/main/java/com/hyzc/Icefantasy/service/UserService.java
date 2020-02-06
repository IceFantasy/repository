package com.hyzc.Icefantasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyzc.Icefantasy.entity.User;
import com.hyzc.Icefantasy.mapper.UserMapper;

/**
 * @Author:0xOO
 * @Date: 2018/9/26 0026
 * @Time: 15:23
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User Sel(int id){
    	
    	System.out.println("2" + userMapper);
        return userMapper.Sel(id);
    }
}
