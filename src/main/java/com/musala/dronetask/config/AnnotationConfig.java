package com.musala.dronetask.config;

import com.musala.dronetask.interfaceimpl.BatteryServiceImpl;
import com.musala.dronetask.interfaces.BatteryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.musala.dronetask")
public class AnnotationConfig {
	
	@Bean
	public BatteryService batteryService() {
		return new BatteryServiceImpl();
	}
	
	
}
