package com.wong.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfiguration {
		@Bean
	    public ServletRegistrationBean druidServlet() {
			ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
			servletRegistrationBean.setServlet(new StatViewServlet());
			servletRegistrationBean.addUrlMappings("/druid/*");
			Map<String, String> initParameters = new HashMap<>();
			initParameters.put("resetEnable", "false"); //禁用HTML页面上的“Rest All”功能
			initParameters.put("allow", "192.168.1.72,127.0.0.1");  //ip白名单（没有配置或者为空，则允许所有访问）
			initParameters.put("deny", "10.8.9.116"); //ip黑名单
			initParameters.put("loginUsername", "admin");  //++监控页面登录用户名
			initParameters.put("loginPassword", "123456");  //++监控页面登录用户密码
			//如果某个ip同时存在，deny优先于allow
			servletRegistrationBean.setInitParameters(initParameters);
			return servletRegistrationBean;
			//return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
	    }
	    @Bean
	    public DataSource druidDataSource(
	                                      @Value("${spring.datasource.url}") String url,
	                                      @Value("${spring.datasource.username}") String username,
	                                      @Value("${spring.datasource.password}") String password) {
	        DruidDataSource druidDataSource = new DruidDataSource();
	        druidDataSource.setUrl(url);
	        druidDataSource.setUsername(username);
	        druidDataSource.setPassword(password);
	        try {
	            druidDataSource.setFilters("stat");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return druidDataSource;
	    }

	    @Bean
	    public FilterRegistrationBean filterRegistrationBean() {
	        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	        filterRegistrationBean.setFilter(new WebStatFilter());
	        filterRegistrationBean.addUrlPatterns("/*");
	        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
	        return filterRegistrationBean;
	    }
}
