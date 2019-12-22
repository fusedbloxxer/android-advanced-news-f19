package ro.atelieruldigital.news.model.ws;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ro.atelieruldigital.news.model.ws.response.ArticlesResponse;
import ro.atelieruldigital.news.model.ws.response.SourcesResponse;

public class NewsWebService {
    private static final String API_KEY = "00d2ab26016f475eac3512ab6e0a9528";
    private NewsApi newsApi;

    public NewsWebService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsApi = retrofit.create(NewsApi.class);
    }

    public Call<ArticlesResponse> queryArticles(String... searchString) {
        return newsApi.queryArticles(API_KEY, searchString);
    }

    public Call<ArticlesResponse> queryArticlesBySource(String... sources) {
        return newsApi.queryArticlesBySource(API_KEY, sources);
    }

    public Call<ArticlesResponse> queryArticlesByLanguage(String... languages) {
        return newsApi.queryArticlesByLanguage(API_KEY, languages);
    }

    public Call<SourcesResponse> querySources() {
        return newsApi.querySources(API_KEY);
    }

    public Call<SourcesResponse> querySourcesByLanguage(String... languages) {
        return newsApi.querySourcesByLanguage(API_KEY, languages);
    }

    public Call<SourcesResponse> querySourcesByCountry(String... countries) {
        return newsApi.querySourcesByCountry(API_KEY, countries);
    }

    public Call<SourcesResponse> querySourcesByCategory(String... categories) {
        return newsApi.querySourcesByCategory(API_KEY, categories);
    }

    private interface NewsApi {
        @GET("/v2/everything")
        Call<ArticlesResponse> queryArticles(@Query("apiKey") String apiKey, @Query("q") String... searchString);

        @GET("/v2/everything")
        Call<ArticlesResponse> queryArticlesBySource(@Query("apiKey") String apiKey, @Query("source") String... sources);

        @GET("/v2/everything")
        Call<ArticlesResponse> queryArticlesByLanguage(@Query("apiKey") String apiKey, @Query("language") String... languages);

        @GET("/v2/sources")
        Call<SourcesResponse> querySources(@Query("apiKey") String apiKey);

        @GET("/v2/sources")
        Call<SourcesResponse> querySourcesByCountry(@Query("apiKey") String apiKey, @Query("country") String... countries);

        @GET("/v2/sources")
        Call<SourcesResponse> querySourcesByLanguage(@Query("apiKey") String apiKey, @Query("language") String... languages);

        @GET("/v2/sources")
        Call<SourcesResponse> querySourcesByCategory(@Query("apiKey") String apiKey, @Query("category") String... categories);
    }
}
