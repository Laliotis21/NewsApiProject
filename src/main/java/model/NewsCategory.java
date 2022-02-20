package model;

import java.util.List;

import newsapidb.Article;

public enum NewsCategory {

    business("Business"),
    entertainment("Entertainment"),
    general("General"),
    health("Healt"),
    science("Science"),
    sports("Sports"),
    technology("techonology");
    
    
	   private String value;
	public List<NewsCategory> getCountries() {
	        return getCountries();
	    }
    
    NewsCategory(String value){
    	this.value = value;
    }
    public String getValue() {
    	return value;
    }
    
    @Override 
    public String toString() { 
        return this.value; 
    }
    }

