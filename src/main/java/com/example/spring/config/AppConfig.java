package com.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.spring.models.MyBean;

@Configuration

// Component scanning is a feature in Spring for automatic detection and instantiation of classes annotated with Stereotype annotations
// including @Component, @Service, @Repository, and @Controller
// These detected classes are automatically registered as beans in the Spring application context. This means you don't have to explicitly
// define these beans in your configuration.
// To use component scanning, you need to specify the packages to scan. You can do this using the @ComponentScan annotation or the 
// <context:component-scan> element in XML.

// @ComponentScan(basePackages = "com.example")
public class AppConfig {
    // Configuration code

	// This can be used instead of ComponentScan to define the object, aka Java Based Configuration.
	@Bean
	public MyBean bean() {
		return new MyBean();
	}
}