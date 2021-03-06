package com.hyzc.Icefantasy.Controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

//@RestController
@Controller
public class LoginController {
	
//	@Autowired
//	private UserService userService;
//	
//    @ResponseBody
//    @RequestMapping("getUser/{id}")
//    public String GetUser(@PathVariable String id){
//    	String user = userService.Sel(id).toString();
//        return user;
//    }

	
//	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@PostMapping(value = "/user/login")
	public String login(@RequestParam("username") String username, 
			@RequestParam("password") String password,
			Map<String, Object> map,
			HttpSession session) {
		
		if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
			//防止表单重复提交 ，可以重定向到主页 
			session.setAttribute("loginUser", username);
			return "redirect:/main.html";// 登录成功
		}else {
			map.put("msg", "用户名密码错误");
			return "login";// 登录失败 
		}
		
	}


}





























