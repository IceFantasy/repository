package com.phantom.chat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.phantom.chat.bean.ChatUser;
import com.phantom.chat.bean.ChatUserExample;

public interface ChatUserMapper {
    long countByExample(ChatUserExample example);

    int deleteByExample(ChatUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(ChatUser record);

    int insertSelective(ChatUser record);

    List<ChatUser> selectByExample(ChatUserExample example);

    ChatUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") ChatUser record, @Param("example") ChatUserExample example);

    int updateByExample(@Param("record") ChatUser record, @Param("example") ChatUserExample example);

    int updateByPrimaryKeySelective(ChatUser record);

    int updateByPrimaryKey(ChatUser record);
    
	int updateByEmailSelective(ChatUser user);
}