package com.sec.prac.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;



@Component
public class PropertyPlaceholderConfigure {
	private static final Logger logger = LoggerFactory.getLogger(PropertyPlaceholderConfigure.class);
	

	private String str;
	
	@Autowired
	private Environment env;
	
	@PostConstruct
	void initting() {
		logger.debug("env key 확인 jdbc.driver: "+env.getProperty("jdbc.driver"));
		//logger.debug("env ket 확인 value 로 확인: "+str);
		logger.debug("env key 확인 dev: "+env.getProperty("dev"));
		
	}
}
