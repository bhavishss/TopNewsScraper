package com.scraper.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan({"com.scraper"})
public class HackerNewsScraperApp extends  SpringBootServletInitializer {

		protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
			return application.sources(HackerNewsScraperApp.class );
		}
		
}