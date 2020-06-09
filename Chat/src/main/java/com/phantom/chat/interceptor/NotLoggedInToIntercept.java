package com.phantom.chat.interceptor;

import javax.servlet.http.HttpServletRequest;
/**
 * 描述 未登录认证访问拦截
 *
 * @author 
 * @date 2018/11/8 
 */
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class NotLoggedInToIntercept implements HandlerInterceptor {
	
	//登录页地址
    private static final String loginUrl = "/";
 	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
    	HttpSession session = request.getSession(false);
        if (session == null || !request.isRequestedSessionIdValid()) {
            response.sendRedirect(request.getContextPath() + loginUrl);
            return false;
        }
        //获取登录用户信息
        Object object = session.getAttribute("user");//session.getAttribute(Constants.SESSION_LOG_KEY);
        if (object == null) {
            response.sendRedirect(request.getContextPath() + loginUrl);
            return false;
        }
 
        return true;
    }
 
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
 
    }


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

}