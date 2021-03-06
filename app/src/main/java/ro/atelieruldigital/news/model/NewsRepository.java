package ro.atelieruldigital.news.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.atelieruldigital.news.model.db.containers.CategoryWithArticle;
import ro.atelieruldigital.news.model.db.containers.SourceWithArticles;
import ro.atelieruldigital.news.model.db.database.NewsDatabase;
import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.Category;
import ro.atelieruldigital.news.model.db.entities.Country;
import ro.atelieruldigital.news.model.db.entities.Language;
import ro.atelieruldigital.news.model.db.entities.Source;
import ro.atelieruldigital.news.model.ws.NewsWebService;
import ro.atelieruldigital.news.model.ws.response.ArticlesResponse;
import ro.atelieruldigital.news.model.ws.response.SourcesResponse;

public class NewsRepository {
    private NewsWebService newsWebService;
    private NewsDatabase newsDatabase;

    public NewsRepository(final Context context) {
        newsWebService = new NewsWebService();
        newsDatabase = NewsDatabase.getInstance(context);
    }

    public LiveData<List<Category>> getAllCategories() {
        return newsDatabase.categoryDao().getAllCategories();
    }

    public LiveData<List<Language>> getAllLanguages() {
        return newsDatabase.languageDao().getAllLanguages();
    }

    public LiveData<List<Country>> getAllCountries() {
        return newsDatabase.countryDao().getAllCountries();
    }

    public LiveData<List<CategoryWithArticle>> getCategoriesWithArticles() {
        return newsDatabase.articleDao().getCategoriesWithArticles();
    }

    public LiveData<List<Article>> queryArticlesByCountries(String... countries) {
        return newsDatabase.articleDao().getArticlesByCountries(countries);// TODO: povestea ...
    }

    public LiveData<List<SourceWithArticles>> getSourcesWithArticles() {
        return newsDatabase.sourceDao().getSourcesWithArticles();
    }

    public LiveData<List<Article>> queryArticleByLanguages(String... languages) {
        newsWebService.queryArticlesByLanguages(languages).enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(@NotNull Call<ArticlesResponse> call, @NotNull Response<ArticlesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NewsDatabase.getDatabaseWriteExecutor()
                            .execute(() -> newsDatabase.articleDao().insertObjects(response.body().getArticles()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArticlesResponse> call, @NotNull Throwable t) {

            }
        });

        return newsDatabase.articleDao().getArticlesByLanguages(languages);
    }

    public LiveData<List<Article>> queryArticlesBySources(String... sources) {
        newsWebService.queryArticlesBySources(sources).enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(@NotNull Call<ArticlesResponse> call, @NotNull Response<ArticlesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NewsDatabase.getDatabaseWriteExecutor()
                            .execute(() -> newsDatabase.articleDao().insertObjects(response.body().getArticles()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArticlesResponse> call, @NotNull Throwable t) {

            }
        });

        return newsDatabase.articleDao().getArticlesBySources(sources);
    }

    public LiveData<List<Source>> getAllSources() {
        newsWebService.querySources().enqueue(new Callback<SourcesResponse>() {
            @Override
            public void onResponse(@NotNull Call<SourcesResponse> call, @NotNull Response<SourcesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NewsDatabase.getDatabaseWriteExecutor()
                            .execute(() -> newsDatabase.sourceDao().insertObjects(response.body().getSources()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<SourcesResponse> call, @NotNull Throwable t) {
            }
        });

        return newsDatabase.sourceDao().getAllSources();
    }

    public LiveData<List<Source>> querySourcesByCountries(String... countries) {
        newsWebService.querySourcesByCountries(countries).enqueue(new Callback<SourcesResponse>() {
            @Override
            public void onResponse(@NotNull Call<SourcesResponse> call, @NotNull Response<SourcesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NewsDatabase.getDatabaseWriteExecutor()
                            .execute(() -> newsDatabase.sourceDao().insertObjects(response.body().getSources()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<SourcesResponse> call, @NotNull Throwable t) {

            }
        });

        return newsDatabase.sourceDao().getSourcesByCountries(countries);
    }

    public LiveData<List<Article>> getArticlesByFilters(String[] languages, String[] categories, String[] countries, String[] sources) {
        newsWebService.querySourcesByFilters(languages, categories, countries).enqueue(new Callback<SourcesResponse>() {
            @Override
            public void onResponse(@NotNull Call<SourcesResponse> call, @NotNull Response<SourcesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NewsDatabase.getDatabaseWriteExecutor().execute(() -> {
                        List<Source> sourceList = response.body().getSources();
                        for (int i = 0; i < sourceList.size(); i++) {
                            for (String source : sources) {
                                if (!sourceList.get(i).equals(source)) {
                                    sourceList.remove(i);
                                    i--; break;
                                }
                            }
                        }
                        newsDatabase.sourceDao().insertObjects(sourceList);
                        newsWebService.queryArticlesBySources(sources).enqueue(new Callback<ArticlesResponse>() {
                            @Override
                            public void onResponse(@NotNull Call<ArticlesResponse> call, @NotNull Response<ArticlesResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    NewsDatabase.getDatabaseWriteExecutor().execute(() -> {
                                        newsDatabase.articleDao().insertObjects(response.body().getArticles());
                                    });
                                }
                            }

                            @Override
                            public void onFailure(@NotNull Call<ArticlesResponse> call, @NotNull Throwable t) {

                            }
                        });
                    });
                }
            }

            @Override
            public void onFailure(@NotNull Call<SourcesResponse> call, @NotNull Throwable t) {

            }
        });

        return newsDatabase.articleDao().getArticlesByFilters(languages, categories, countries, sources);
    }

    public LiveData<List<Source>> querySourcesByCategories(String... categories) {
        newsWebService.querySourcesByCategories(categories).enqueue(new Callback<SourcesResponse>() {
            @Override
            public void onResponse(@NotNull Call<SourcesResponse> call, @NotNull Response<SourcesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NewsDatabase.getDatabaseWriteExecutor()
                            .execute(() -> newsDatabase.sourceDao().insertObjects(response.body().getSources()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<SourcesResponse> call, @NotNull Throwable t) {
            }
        });

        return newsDatabase.sourceDao().getSourcesByCategories(categories);
    }

    public LiveData<List<Article>> queryArticles(String keyword) {
        newsWebService.queryArticles(keyword).enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(@NotNull Call<ArticlesResponse> call, @NotNull Response<ArticlesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NewsDatabase.getDatabaseWriteExecutor()
                            .execute(() -> newsDatabase.articleDao().insertObjects(response.body().getArticles()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArticlesResponse> call, @NotNull Throwable t) {
            }

        });

        return newsDatabase.articleDao().getArticlesByKeyword(keyword);
    }
}