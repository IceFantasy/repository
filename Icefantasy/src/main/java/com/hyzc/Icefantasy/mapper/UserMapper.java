package com.hyzc.Icefantasy.mapper;

import org.springframework.stereotype.Repository;

import com.hyzc.Icefantasy.entity.User;

/**
 * @Author:0xOO
 * @Date: 2018/9/26 0026
 * @Time: 15:20
 */
@Repository
public interface UserMapper {

    User Sel(int id);
}
