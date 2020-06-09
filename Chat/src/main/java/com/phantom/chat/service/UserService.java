package com.phantom.chat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phantom.chat.bean.ChatUser;
import com.phantom.chat.bean.ChatUserExample;
import com.phantom.chat.bean.ChatUserExample.Criteria;
import com.phantom.chat.dao.ChatUserMapper;
import com.phantom.chat.utils.CommUtils;
import com.phantom.chat.utils.email.MailUtil;

@Service
public class UserService {
	
	@Autowired
	private ChatUserMapper chatUserMapper;
	
	public void saveUser(ChatUser user) {
		user.setUserId(CommUtils.randomUUID());// 封装对象
		user.setCreateDate(new Date());
		chatUserMapper.updateByEmailSelective(user);
	}
	
	/**
	 * 检验用户名是否可用
	 * 
	 * @param nickName
	 * @return true：代表当前姓名可用 fasle：不可用
	 * 
	 * lenovo ： 王淳诚 25887311267@qq.com 2020年6月3日 下午8:43:19
	 */
	public boolean checkUser(String nickName) {
		ChatUserExample example = new ChatUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andNickNameEqualTo(nickName);
		long count = chatUserMapper.countByExample(example);
		return count == 0;
	}
		
	public boolean checkEmail(String email) {
		ChatUserExample example = new ChatUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmailAccountEqualTo(email);
		long count = chatUserMapper.countByExample(example);
		return count == 0;
	}
	
	public boolean sendCode(String email) {
		String emailCode = CommUtils.randomNum();
		// 1. 发送验证码
		boolean b = MailUtil.sendMail(email, emailCode);
		// 2. 保存验证码
		chatUserMapper.insert(new ChatUser(CommUtils.randomUUID(), //
				null, email, emailCode, null, new Date()));
		return b;
	}
		
	public boolean checkMailcode(String checkcode) {
		ChatUserExample example = new ChatUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmailCodeEqualTo(checkcode);
		long count = chatUserMapper.countByExample(example);
		return count != 0;
	}
		
	public ChatUser getChatuser(ChatUser user) {
		ChatUserExample example = new ChatUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmailAccountEqualTo(user.getEmailAccount());
		criteria.andPasswordEqualTo(user.getPassword());
		List<ChatUser> chatUserList = chatUserMapper.selectByExample(example);
		if (chatUserList != null && chatUserList.size() > 0) {
			return chatUserList.get(0);
		}
		return null;
	}

}
