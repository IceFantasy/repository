package com.phantom.chat.bean;

import java.util.Date;

public class ChatUser {
    private String userId;

    private String nickName;

    private String emailAccount;

    private String emailCode;

    private String password;

    private Date createDate;

    public ChatUser(String userId, String nickName, String emailAccount, String emailCode, String password,
			Date createDate) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.emailAccount = emailAccount;
		this.emailCode = emailCode;
		this.password = password;
		this.createDate = createDate;
	}
    
	public ChatUser() {
		super();
	}
	
	@Override
	public String toString() {
		return "ChatUser [userId=" + userId + ", nickName=" + nickName + ", emailAccount=" + emailAccount
				+ ", emailCode=" + emailCode + ", password=" + password + ", createDate=" + createDate + "]";
	}
	
	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount == null ? null : emailAccount.trim();
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode == null ? null : emailCode.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}