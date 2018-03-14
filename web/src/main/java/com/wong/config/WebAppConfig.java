package com.wong.config;

import com.wong.HwongApplication;
import com.wong.aop.LogHandlerInterceptor;
import com.wong.aop.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan(basePackageClasses = HwongApplication.class, useDefaultFilters = true)
public class WebAppConfig extends WebMvcConfigurationSupport{

	@Autowired
	private LogHandlerInterceptor logHandlerInterceptor;//自己定义的拦截器类

	@Autowired
	private LoginInterceptor loginInterceptor;//自己定义的拦截器类

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logHandlerInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/error");

		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/error");
	}
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/**");
    }
	
}
