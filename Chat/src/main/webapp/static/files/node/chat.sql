
DROP TABLE chat_user;

CREATE TABLE chat_user (
	user_id VARCHAR(20) PRIMARY KEY,
	nick_name VARCHAR(10),
	email_account VARCHAR(50) UNIQUE,
	email_code  VARCHAR(10), -- 验证码
	PASSWORD  VARCHAR(20),
	create_date DATE
);

CREATE TABLE login_info (
	id VARCHAR(20) PRIMARY KEY,
	login_date  DATE,
	email_account VARCHAR(50)	
);
