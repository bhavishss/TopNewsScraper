package com.scraper.jpa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.scraper.bean.TopNews;
@Component
public interface TopNewsRepository extends MongoRepository<TopNews, Integer> {

}
