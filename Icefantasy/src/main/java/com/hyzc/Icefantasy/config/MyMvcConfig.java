package com.hyzc.Icefantasy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hyzc.Icefantasy.component.LoginHandlerInterceptor;
import com.hyzc.Icefantasy.component.MyLocaleResolver;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		super.addViewControllers(registry);
		// 浏览器返送 /atguigu 请求来到 success 
		registry.addViewController("/atguigu").setViewName("success");
	}
	
	/**
	 * 所有的 webMvcCOnfigurerAdaper 组件，会一起起作用 
	 * @return
	 * lenovo ： 王淳诚  25887311267@qq.com 
	 * 2019年11月23日  下午9:34:49
	 */
	@Bean
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		
		WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addViewController("/").setViewName("login");
				registry.addViewController("/index.html").setViewName("login");
				registry.addViewController("/main.html").setViewName("dashboard");
			}
			// 注册拦截器 
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(new LoginHandlerInterceptor())
					.addPathPatterns("/**")
					.excludePathPatterns("/index.html", "/", "/user/login");
			}
			
		};
		return webMvcConfigurerAdapter;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new MyLocaleResolver();
	}

}


















