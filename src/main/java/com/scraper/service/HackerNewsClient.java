package com.scraper.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.scraper.bean.TopNews;
import com.scraper.constants.ScraperConstants;
import com.scraper.jpa.TopNewsRepository;

@Component
@EnableMongoRepositories("com.scraper.jpa")
@EnableTransactionManagement
public class HackerNewsClient {
	
	private static final Logger log = LoggerFactory.getLogger(HackerNewsClient.class);
	
	@Autowired
	TopNewsRepository topNewsRepository;
	
	@Autowired
	TopNews topNews;
	
	public void getTopNewsData() throws Exception {
		try {
			RestTemplate restTemplate = new RestTemplate();
			//Hitting the page for scraping the data
			log.info("Hitting url "+ScraperConstants.CLIENT_URl);
			String result = restTemplate.getForObject(ScraperConstants.CLIENT_URl, String.class);
			Document doc =  Jsoup.parse(result);  
			Elements links = doc.getElementsByClass(ScraperConstants.STORY_LNK);  
			links.forEach(link -> topNewsRepository.save(new TopNews(link.text(), link.attr(ScraperConstants.SOURCE))));
			log.info("Top News from" +ScraperConstants.CLIENT_URl+" are sucessfully persited and count is "+links.size());
		} catch (Exception e) {
			log.info("Error:" + e.getMessage());
			throw e;
		}
	}
}
