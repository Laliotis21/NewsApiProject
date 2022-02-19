package services;

import exception.NewsApiException;
import model.NewsAPi;
import model.NewsInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;



public class NewApiServiceTest {

    @Test
    public void getClient() {
    }

    @Test
   public void searchForeverything() throws NewsApiException {
        final NewApiService newsSearchService = NewsAPi.getnewsapidb();
        final List<NewsInfo> results = newsSearchService.searchForeverything("bitcoin", "the-verge", "en", "2022-02-18", "2022-02-19");
        Assert.assertFalse(results.isEmpty());
       results.forEach(System.out::println);
   }

    @Test
    public void getTopHeadlines() throws NewsApiException {
            final NewApiService newsSearchService = NewsAPi.getnewsapidb();
           final List<NewsInfo> results = newsSearchService.searchFortopHeadlines("gb","sports");
           Assert.assertFalse(results.isEmpty());
           results.forEach(System.out::println);
        }

    }

