package com.scraper.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:scraper-config.properties")

public class ScraperPropertyUtil {
	@Autowired
	private Environment env;
	private static Environment environment;
	@PostConstruct
	 public void init(){
		environment = env;
	}
}
