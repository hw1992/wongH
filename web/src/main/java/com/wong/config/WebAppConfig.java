package com.wong.config;

import com.wong.HwongApplication;
import com.wong.aop.LogHandlerInterceptor;
import com.wong.aop.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan(basePackageClasses = HwongApplication.class, useDefaultFilters = true)
public class WebAppConfig extends WebMvcConfigurationSupport{

	@Bean
	public HandlerInterceptor getLoginInterceptor(){
		return new LoginInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(getLoginInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/error")
				.excludePathPatterns("/static/*");
	}
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/**");
    }
	
}
