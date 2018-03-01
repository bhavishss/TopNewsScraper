package com.scraper.bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
@Component
@Document(collection = "topnews")
public class TopNews {
	@Id
    private String id;
	private String title;
	private String source;
	
	public TopNews()
	{
		
	}
	public TopNews(String title, String source) {
		this.title = title;
		this.source = source;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	   @Override
	    public String toString() {
	        return "TopNews{" +
	                ", Title='" + title + '\'' +
	                ", Source=" + source + 
	                '}';
	    }
}
