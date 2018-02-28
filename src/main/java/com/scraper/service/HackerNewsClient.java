package com.scraper.service;


import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.scraper.bean.TopNews;
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
		RestTemplate restTemplate = new RestTemplate();
		//Hitting the page for scraping the data
		String result = restTemplate.getForObject("https://news.ycombinator.com", String.class);
		Document doc =  Jsoup.parse(result);  
		log.info("title is: " + doc.title());  
		Elements links = doc.getElementsByClass("storylink");  
		links.forEach(link -> topNewsRepository.save(new TopNews(link.text(), link.attr("href"))));
	}
}
