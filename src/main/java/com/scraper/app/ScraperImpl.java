package com.scraper.app;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.scraper.service.HackerNewsClient;

@Component
public class ScraperImpl {
	Logger logger = LoggerFactory.getLogger(ScraperImpl.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/yyyy HH:mm:ss");
	
	@Autowired
	HackerNewsClient hackerNewsClient;
	
	@Scheduled(cron="${scraper.cron.expression}")
    public void doNewsScrapper() throws Exception {
		logger.info("Top News scraping daily job started: "+dateFormat.format(new Date()));
    	System.out.println("Start" + dateFormat.format(new Date()));
    	try {
    		//Calling the client method
    		hackerNewsClient.getTopNewsData();
    		logger.info("Top News scraping daily job ended: "+dateFormat.format(new Date()));
		} catch (Exception e) {
			throw e;
		}
    }
}
