package ro.atelieruldigital.news.model.ws;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ro.atelieruldigital.news.model.NewsListResponse;

public class NewsWebService {
    // TODO: Add functionality according to API

    // TODO: Load Token
    private static final String API_KEY = "00d2ab26016f475eac3512ab6e0a9528";
    private NewsApi newsApi;

    public NewsWebService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsApi = retrofit.create(NewsApi.class);
    }

    public Call<NewsListResponse> queryArticles(String searchString) {
        return newsApi.queryArticles(searchString, API_KEY);
    }

    private interface NewsApi {
        // TODO: Add functionality according to API
        // TODO: To be used as Retrofit's API

        @GET("/v2/everything")
        Call<NewsListResponse> queryArticles(@Query("q") String searchString,
                                             @Query("apiKey") String apiKey);
    }
}
