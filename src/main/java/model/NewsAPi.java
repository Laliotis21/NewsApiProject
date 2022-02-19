package model;

import services.NewApiService;

public class NewsAPi {
	
	public static NewApiService getnewsapidb() {
		// API key needed. Register and generate API KEY
		return new NewApiService("https://newsapi.org", "bc8d17bfeeec42239f059a66e016ef40");
	}

}
