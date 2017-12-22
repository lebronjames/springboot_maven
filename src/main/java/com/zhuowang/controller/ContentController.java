package com.zhuowang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {

	private static final Logger logger = LoggerFactory.getLogger(ContentController.class);
	
	@Value("${application.hello:Hello inner hello}")
    private String hello = "Hello Shanhy";
	
	@Value("${application.info}")
	private String info;
	
	@GetMapping("/logTest")
	public void logTest(){
		logger.debug("日志输出测试 Debug"+this.hello);
	    logger.trace("日志输出测试 Trace");
	    logger.info("日志输出测试 Info"+this.hello);
	    logger.error("日志输出测试 error："+this.info);
	}
}
