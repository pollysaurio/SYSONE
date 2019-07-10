package com.sysone.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfigurationEnviroment {
	
	private static ConfigurationEnviroment instance = null;
	private static ClassPathXmlApplicationContext context = null;

	private ConfigurationEnviroment() {
		getContext();
	}
	
	public static ConfigurationEnviroment getInstance(){
		if (instance == null) {
			instance = new ConfigurationEnviroment();
		}
		return instance;
	}

	public ClassPathXmlApplicationContext getContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("spring.xml");
		}
		return context;
	}
	
}
