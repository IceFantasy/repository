package com.phantom.chat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.phantom.chat.bean.ChatUser;
import com.phantom.chat.bean.Employee;
import com.phantom.chat.bean.Msg;
import com.phantom.chat.service.UserService;

/**
 * 处理登录注册的请求
 * 
 * @author lfy
 * 
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 单个批量二合一
	 * 批量删除：1-2-3
	 * 单个删除：1
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/chatUser/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmp(@PathVariable("ids")String ids){
		//批量删除
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			//组装id的集合
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
//			userService.deleteBatch(del_ids);
		}else{
			Integer id = Integer.parseInt(ids);
//			userService.deleteEmp(id);
		}
		return Msg.success();
	}
	
	
	
	/**
	 * 如果直接发送ajax=PUT形式的请求
	 * 封装的数据
	 * Employee 
	 * [empId=1014, empName=null, gender=null, email=null, dId=null]
	 * 
	 * 问题：
	 * 请求体中有数据；
	 * 但是Employee对象封装不上；
	 * update tbl_emp  where emp_id = 1014;
	 * 
	 * 原因：
	 * Tomcat：
	 * 		1、将请求体中的数据，封装一个map。
	 * 		2、request.getParameter("empName")就会从这个map中取值。
	 * 		3、SpringMVC封装POJO对象的时候。
	 * 				会把POJO中每个属性的值，request.getParamter("email");
	 * AJAX发送PUT请求引发的血案：
	 * 		PUT请求，请求体中的数据，request.getParameter("empName")拿不到
	 * 		Tomcat一看是PUT不会封装请求体中的数据为map，只有POST形式的请求才封装请求体为map
	 * org.apache.catalina.connector.Request--parseParameters() (3111);
	 * 
	 * protected String parseBodyMethods = "POST";
	 * if( !getConnector().isParseBodyMethod(getMethod()) ) {
                success = true;
                return;
            }
	 * 
	 * 
	 * 解决方案；
	 * 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
	 * 1、配置上HttpPutFormContentFilter；
	 * 2、他的作用；将请求体中的数据解析包装成一个map。
	 * 3、request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据
	 * 员工更新方法
	 * @param employee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/chatUser/{empId}",method=RequestMethod.PUT)
	public Msg saveEmp(Employee employee,HttpServletRequest request){
		System.out.println("请求体中的值："+request.getParameter("gender"));
		System.out.println("将要更新的员工数据："+employee);
//		userService.updateEmp(employee);
		return Msg.success();
	}
	
	/**
	 * 根据id查询员工
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/chatUser/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id){
		
//		Employee employee = userService.getEmp(id);
		return Msg.success().add("emp", null);
	}
	
	
	/**
	 * 检查用户名是否可用
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkChatUser")
	public Msg checkChatUser(@RequestParam("empName")String empName){
		//先判断用户名是否是合法的表达式;
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regx)){
			return Msg.fail().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
		}
		//数据库用户名重复校验
		boolean b = userService.checkUser(empName);//userService.checkUser(empName);
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg", "用户名不可用");
		}
	}
	
	/**
	 * 检查用户名是否可用
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkmailcode/{checkcode}")
	public Msg checkmailcode(@PathVariable("checkcode") String checkcode){
		//先判断验证码是否是合法的表达式;
		if (StringUtil.isEmpty(checkcode)) {
			return Msg.fail().add("va_msg", "验证码为空");
		}
		//数据库验证码是否正确
		boolean b = userService.checkMailcode(checkcode);//验证成功返回true
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg", "验证失败");
		}
	}
	
	/**
	 * 检查邮箱是否可用
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkemail")
	public Msg checkEmail(@RequestParam("email")String email){
		//先判断用户名是否是合法的表达式;
		String regx = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
		if(!email.matches(regx)){
			return Msg.fail().add("va_msg", "邮箱必须是6-16位数字和字母的组合");
		}
		//数据库用户名重复校验
		boolean b = userService.checkEmail(email);
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg", "邮箱不可用");
		}
	}
	
	/**
	 * 发送邮件
	 * @param email
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sendemailcode")
	public Msg sendemailcode(@RequestParam("email")String email){
		//先判断用户名是否是合法的表达式;
		String regx = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
		if(!email.matches(regx)){
			return Msg.fail().add("va_msg", "邮箱必须是6-16位数字和字母的组合");
		}
		//数据库用户名重复校验
		boolean b = userService.sendCode(email);
		if(b){
			return Msg.success();
		}else{
			return Msg.fail().add("va_msg", "验证码发送失败！");
		}
	}
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2020年6月9日  下午10:13:40
	 */
	@RequestMapping(value="/loginchar",method=RequestMethod.POST)
	public String queryChatUser(@Valid ChatUser user, Model model, HttpServletRequest request){
		user = userService.getChatuser(user);
		if (user != null) {
			model.addAttribute(user);
            request.getSession(true).setAttribute("user",user);
			return "redirect:/static/page/proposal.jsp";
		} else {
			model.addAttribute("message","登录名或密码错误！");
			return "redirect:/";
		}
	}
	/**
	 * 员工保存
	 * 1、支持JSR303校验
	 * 2、导入Hibernate-Validator
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value="/chatUser",method=RequestMethod.POST)
	public String saveChatUser(@Valid ChatUser user,BindingResult result){
		Map<String, Object> map = new HashMap<>();
		if(result.hasErrors()){
			//校验失败，应该返回失败，在模态框中显示校验失败的错误信息
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			map.put("msg", Msg.fail().add("errorFields", map));
			return "redirect:/static/page/cool_regist.jsp";
		}else{
			userService.saveUser(user);
			map.put("msg", Msg.success());
			return "redirect:/";
		}
		
	}
		
	

	/**
	 * 导入jackson包。
	 * @param pn
	 * @return
	 */
	@RequestMapping("/emps1")
	@ResponseBody
	public Msg getEmpsWithJson(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 这不是一个分页查询
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
//		List<Employee> emps = userService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
//		PageInfo page = new PageInfo(emps, 5);
		return Msg.success().add("pageInfo", null);
	}
	
	/**
	 * 查询员工数据（分页查询）
	 * 
	 * @return
	 */
	// @RequestMapping("/emps")
	public String getEmps(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model) {
		// 这不是一个分页查询；
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps = null;//userService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page = new PageInfo(emps, 5);
		model.addAttribute("pageInfo", page);

		return "list";
	}

}
