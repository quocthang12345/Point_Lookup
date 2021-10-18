package com.PointLookup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableWebMvc
@ComponentScan (basePackages = {"org.PointLookup"} ,includeFilters = {@Filter (type = FilterType.ANNOTATION, value = Configuration.class)})

public class PointLookupApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointLookupApplication.class, args);
	}

}
