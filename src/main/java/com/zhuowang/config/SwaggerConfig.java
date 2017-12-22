package com.zhuowang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	//针对RestController注解的类和ResponseBody注解的方法才生成Swaager的API，并且排除了特定的类
	 @Bean
	    public Docket createRestApi() {
	        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
	            @Override
	            public boolean apply(RequestHandler input) {
	                Class<?> declaringClass = input.declaringClass();
//	                if (declaringClass == BasicErrorController.class)// 排除
//	                    return false;
	                if(declaringClass.isAnnotationPresent(RestController.class)) // 被注解的类
	                    return true;
	                if(input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
	                    return true;
	                return false;
	            }
	        };
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .useDefaultResponseMessages(false)
	                .select()
	                .apis(predicate)
	                .build();
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	            .title("接口服务")//大标题
	            .version("1.0")//版本
	            .build();
	    }

	/**
     * 可以定义多个组，比如本类中定义把test和demo区分开了
     * （访问页面就可以看到效果了） 
     *
     */
	//api组配置
//    @Bean
//    public Docket testApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("test")
//                .genericModelSubstitutes(DeferredResult.class)
////                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
//                .select()
//                .paths(or(regex("/api/.*")))//过滤的接口
//                .build()
//                .apiInfo(testApiInfo());
//    }
//    
//    private ApiInfo testApiInfo() {
//        return new ApiInfoBuilder()
//            .title("Electronic Health Record(EHR) Platform API")//大标题
//            .description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")//详细描述
//            .version("1.0")//版本
//            .termsOfServiceUrl("NO terms of service")
//            .contact(new Contact("yzx", "http://blog.csdn.net/catoop", "demo@qq.com"))//作者
//            .license("The Apache License, Version 2.0")
//            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//            .build();
//    }
//    
//    //demo组配置
//    @Bean
//    public Docket demoApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("demo")
//                .genericModelSubstitutes(DeferredResult.class)
////              .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(false)
//                .pathMapping("/")
//                .select()
//                .paths(or(regex("/demo/.*")))//过滤的接口
//                .build()
//                .apiInfo(demoApiInfo());
//    }
//    
//    private ApiInfo demoApiInfo() {
//        return new ApiInfoBuilder()
//            .title("Electronic Health Record(EHR) Platform API")//大标题
//            .description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")//详细描述
//            .version("1.0")//版本
//            .termsOfServiceUrl("NO terms of service")
//            .contact(new Contact("yzx", "http://blog.csdn.net/catoop", "api@qq.com"))//作者
//            .license("The Apache License, Version 2.0")
//            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//            .build();
//    }
}
