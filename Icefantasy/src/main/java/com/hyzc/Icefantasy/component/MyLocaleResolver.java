package com.hyzc.Icefantasy.component;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

/**
 * 可以在链接上携带区域信息 
 * @author lenovo
 * 王淳诚 2587311267@qq.com
 */
public class MyLocaleResolver implements LocaleResolver{

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String l = request.getParameter("l");
		Locale locale = Locale.getDefault();
		if (!StringUtils.isEmpty(l)) {
			String[] split = l.split("_");
			locale = new Locale(split[0], split[1]);
		}
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		
	}
	
	

}
