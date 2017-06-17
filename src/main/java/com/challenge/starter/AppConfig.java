package com.challenge.starter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.challenge.starter.interceptor.ParameterValidatorInterceptor;

@Configuration
@ComponentScan("com.challenge.starter")
@EnableWebMvc 
public class AppConfig extends WebMvcConfigurerAdapter  {  

    @Override
    public void addInterceptors(InterceptorRegistry  registry) {
       registry.addInterceptor(new ParameterValidatorInterceptor()).addPathPatterns("/**");
    }


}
