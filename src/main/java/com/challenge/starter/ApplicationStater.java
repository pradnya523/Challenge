package com.challenge.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


@SpringBootApplication

public class ApplicationStater {
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStater.class, args);

	}
	
	 @Bean
	    public MethodValidationPostProcessor getMethodValidationPostProcessor(){
		 return new MethodValidationPostProcessor();
	    }
	 


}
