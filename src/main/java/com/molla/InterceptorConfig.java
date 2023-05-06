package com.molla;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.molla.config.CategoryInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	@Autowired
	CategoryInterceptor categoryInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(categoryInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/rest/**","/admin/**","/assets/**"); //loại trừ
	}
}
