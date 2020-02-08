package ro.atelieruldigital.news.model.ws;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ro.atelieruldigital.news.model.ws.response.ArticlesResponse;
import ro.atelieruldigital.news.model.ws.response.SourcesResponse;

public class NewsWebService {
    private static final String API_KEY = "YOUR_API_KEY_FROM_NEWS_API";
    public static final String MAX_PAGE_SIZE = "100";
    public static final int MAX_SOURCES = 20;

    private NewsApi newsApi;

    public NewsWebService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsApi = retrofit.create(NewsApi.class);
    }

    public Call<ArticlesResponse> queryArticles(String... searchString) {
        return newsApi.queryArticles(API_KEY, MAX_PAGE_SIZE, searchString);
    }

    public Call<ArticlesResponse> queryArticlesBySources(String... sources) {
        return newsApi.queryArticlesBySources(API_KEY, MAX_PAGE_SIZE, sources);
    }

    public Call<ArticlesResponse> queryArticlesByLanguages(String... languages) {
        return newsApi.queryArticlesByLanguages(API_KEY, MAX_PAGE_SIZE, languages);
    }

    public Call<SourcesResponse> querySources() {
        return newsApi.querySources(API_KEY);
    }

    public Call<SourcesResponse> querySourcesByLanguages(String... languages) {
        return newsApi.querySourcesByLanguages(API_KEY, languages);
    }

    public Call<SourcesResponse> querySourcesByCountries(String... countries) {
        return newsApi.querySourcesByCountries(API_KEY, countries);
    }

    public Call<SourcesResponse> querySourcesByCategories(String... categories) {
        return newsApi.querySourcesByCategories(API_KEY, categories);
    }

    public Call<SourcesResponse> querySourcesByFilters(String[] languages, String[] categories, String[] countries) {
        return newsApi.querySourcesByFilters(API_KEY, languages, categories, countries);
    }

    private interface NewsApi {
        @GET("/v2/everything")
        Call<ArticlesResponse> queryArticles(@Query("apiKey") String apiKey, @Query("pageSize") String pageSize, @Query("q") String... searchString);

        @GET("/v2/everything")
        Call<ArticlesResponse> queryArticlesBySources(@Query("apiKey") String apiKey, @Query("pageSize") String pageSize, @Query("sources") String... sources);

        @GET("/v2/everything")
        Call<ArticlesResponse> queryArticlesByLanguages(@Query("apiKey") String apiKey, @Query("pageSize") String pageSize, @Query("language") String... languages);

        @GET("/v2/sources")
        Call<SourcesResponse> querySources(@Query("apiKey") String apiKey);

        @GET("/v2/sources")
        Call<SourcesResponse> querySourcesByCountries(@Query("apiKey") String apiKey, @Query("country") String... countries);

        @GET("/v2/sources")
        Call<SourcesResponse> querySourcesByLanguages(@Query("apiKey") String apiKey, @Query("language") String... languages);

        @GET("/v2/sources")
        Call<SourcesResponse> querySourcesByCategories(@Query("apiKey") String apiKey, @Query("category") String... categories);

        @GET("/v2/sources")
        Call<SourcesResponse> querySourcesByFilters(@Query("apiKey") String apiKey, @Query("language") String[] languages, @Query("category") String[] categories, @Query("country") String[] countries);
    }
}
