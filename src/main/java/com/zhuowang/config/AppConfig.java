package com.zhuowang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * spring boot项目里,全局初始化ObjectMapper
 * @author Administrator
 *
 */
@Configuration
public class AppConfig {

	@Bean
	  public ObjectMapper ObjectMapper(){
	   ObjectMapper objectMapper=new ObjectMapper();
	   return objectMapper;
	  }
}
