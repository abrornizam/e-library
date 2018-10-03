package com.abror.demo;

/**
 * @author ANIZAM
 *
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.abror.demo")
//@EntityScan("com.abror.demo.model")
//@EnableJpaRepositories("com.abror.demo.repository")
//public class WebApplication {
public class WebApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }
	
	public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }
}
