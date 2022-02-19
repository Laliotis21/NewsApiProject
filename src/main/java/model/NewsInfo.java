package model;

import newsapidb.Article;

public class NewsInfo {
	 private String title;
	    private String description;
	    private String url;
	    private String publishedAt;
	    
	    public NewsInfo(String title, String description, String url, String publishedAt) {
	        this.title = title;
	        this.description = description;
	        this.url = url;
	        this.publishedAt = publishedAt;
	    }
	    
	    public NewsInfo(Article theResult) {
	        this.title = theResult.getTitle();
	        this.description = (String) theResult.getDescription();
	        this.url = theResult.getUrl();
	        this.publishedAt = theResult.getPublishedAt();
	    }
//	    Get New Title
	    public String getTitle() {
	        return title;
	    }
	/*    Set New Title */
	    public void setTitle(String title) {
	        this.title = title;
	    }
	/* Get Article Description */
	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getUrl() {
	        return url;
	    }

	    public String getPublishedAt() {
	        return publishedAt;
	    }

	    public void setPublishedAt(String value) {
	        this.publishedAt = value;
	    }
	    @Override
	    public String toString() {
	        return "MovieInfo{" +
	                "title='" + title + "'\n" +
	                ", description='" + description + "'\n" +
	                ", url='" + url + "'\n" +
	                ", publishedAt='" + publishedAt + "'\n" +
	                '}';
	    }
}
