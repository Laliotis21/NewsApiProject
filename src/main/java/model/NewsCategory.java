package model;

public enum NewsCategory {

    business("Business"),
    entertainment("Entertainment"),
    general("General"),
    health("Healt"),
    science("Science"),
    sports("Sports"),
    technology("techonology");
    
    
    private String value;
    
    NewsCategory(String value){
    	this.value = value;
    }
    public String getValue() {
    	return value;
    }
    }

