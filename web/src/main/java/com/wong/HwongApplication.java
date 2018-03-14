package com.wong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@EnableScheduling
@ServletComponentScan //扫描自定义filter和servlet、listener之类的
@EnableTransactionManagement
@MapperScan(basePackages = "com.wong.mapper")
@SpringBootApplication
public class HwongApplication extends SpringBootServletInitializer implements CommandLineRunner {
    /**
     * @param args
     */
    public static void main(String[] args) {
    	SpringApplication.run(HwongApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("darling~wongH, your springboot start success！");
    }
}
