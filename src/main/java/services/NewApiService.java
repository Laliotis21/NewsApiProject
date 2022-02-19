package services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import model.NewsInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

import exception.NewsApiException;
import newsapidb.Article;
import newsapidb.ErrorResponse;
import newsapidb.NewsResults;

public class NewApiService {
    private final String API_URL;
    private final String API_KEY;


    public NewApiService(String aPI_URL, String aPI_KEY) {
        API_URL = aPI_URL;
        API_KEY = aPI_KEY;
    }


    public List<NewsInfo> searchFortopHeadlines(String country, String category) throws NewsApiException {
        NewsResults result = getAPIData("top-headlines",category, country, null,null, null, null, null,API_URL, API_KEY);
        List<NewsInfo> newsInfoList = new ArrayList<>(result.getArticles().size());
        for (Article theResult : result.getArticles()) {
            newsInfoList.add(new NewsInfo(theResult));
        }
        return newsInfoList ;
    }

    public List<NewsInfo> searchForeverything(String query,String source, String searchingLanguage ,String fromSearch,String toSearch) throws NewsApiException {
        NewsResults result = getAPIData("everything", null, null, query,source, searchingLanguage, fromSearch, toSearch,API_URL, API_KEY);
        List<NewsInfo> newsInfoList = new ArrayList<>(result.getArticles().size());
        for (Article theResult : result.getArticles()) {
            newsInfoList.add(new NewsInfo(theResult));
        }
        return newsInfoList ;
    }


    private NewsResults getAPIData(String apiFunction, String category, String country, String query,String source, String searchingLanguage ,String fromSearch,String toSearch, String API_URL, String API_KEY)
            throws NewsApiException {
        try {
            final URIBuilder uriBuilder = new URIBuilder(API_URL)
                    .setPathSegments("v2", apiFunction)
                    .addParameter("apikey", API_KEY);
//            if (country != null && !country.isBlank() ||
//            		category != null && !category.isBlank() ||
//                    query != null && !query.isBlank() ||
//                    source != null && !source.isBlank() ||
//                    searchingLanguage != null && !searchingLanguage.isBlank() ||
//                    fromSearch != null && !fromSearch.isBlank() ||
//                    toSearch != null && !toSearch.isBlank())
//                {
                switch (apiFunction) {
                    case "top-headlines":
                        uriBuilder.addParameter("country", country);
                        uriBuilder.addParameter("category", category);
                        break;
                    case "everything":
                        uriBuilder.addParameter("q", query);
                        uriBuilder.addParameter("sources", source);
                        uriBuilder.addParameter("language", searchingLanguage);
                        uriBuilder.addParameter("from", fromSearch);
                        uriBuilder.addParameter("to", toSearch);
                        break;
                }
//        }

            final URI uri = uriBuilder.build();

            final HttpGet getRequest = new HttpGet(uri);
            final CloseableHttpClient httpclient = HttpClients.createDefault();
            try (CloseableHttpResponse response = httpclient.execute(getRequest)) {
                final HttpEntity entity = response.getEntity();
                final ObjectMapper mapper = new ObjectMapper();

                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    ErrorResponse errorResponse = mapper.readValue(entity.getContent(), ErrorResponse.class);
                    if (errorResponse.getStatusMessage() != null)
                        throw new NewsApiException("Error occurred on API call: " + errorResponse.getStatusMessage());
                }

                return mapper.readValue(entity.getContent(), NewsResults.class);
            } catch (IOException e) {
                throw new NewsApiException("Error requesting data from the NewsAPI.", e);
            }
        } catch (URISyntaxException e) {
            throw new NewsApiException("Unable to create request URI.", e);
        }
    }
}

