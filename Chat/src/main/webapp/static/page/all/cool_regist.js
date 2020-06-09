// 验证用户输入数据

function validate_add_form() {
	//1、拿到要校验的数据，使用正则表达式
	var empName = $("#empName_add_input").val();
	var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
	if (!regName.test(empName)) {
		//alert("用户名可以是2-5位中文或者6-16位英文和数字的组合");
		show_validate_msg("#empName_add_input", "error", "用户名可以是2-5位中文或者6-16位英文和数字的组合");
		return false;
	} else {
		show_validate_msg("#empName_add_input", "success", "");
	};
	
	//2、校验邮箱信息
	var email = $("#email_add_input").val();
	var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	if (!regEmail.test(email)) {
		//alert("邮箱格式不正确");
		//应该清空这个元素之前的样式
		show_validate_msg("#email_add_input", "error", "邮箱格式不正确");
		/* $("#email_add_input").parent().addClass("has-error");
		$("#email_add_input").next("span").text("邮箱格式不正确"); */
		return false;
	} else {
		show_validate_msg("#email_add_input", "success", "");
	}
	return true;
}

// nickName

// emailAccount

// emailCode

// password

// createDate
