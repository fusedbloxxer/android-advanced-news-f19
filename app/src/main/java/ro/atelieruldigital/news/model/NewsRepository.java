package ro.atelieruldigital.news.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.atelieruldigital.news.model.db.containers.CategoryArticle;
import ro.atelieruldigital.news.model.db.database.NewsDatabase;
import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.Source;
import ro.atelieruldigital.news.model.ws.NewsWebService;
import ro.atelieruldigital.news.model.ws.response.ArticlesResponse;
import ro.atelieruldigital.news.model.ws.response.SourcesResponse;
import timber.log.Timber;

public class NewsRepository {
    private NewsWebService newsWebService;
    private NewsDatabase newsDatabase;

    public NewsRepository(final Context context) {
        newsWebService = new NewsWebService();
        newsDatabase = NewsDatabase.getInstance(context);
    }

    public LiveData<List<CategoryArticle>> getCategoriesWithArticles() {
        return newsDatabase.articleDao().getCategoriesWithArticles();
    }

    public LiveData<List<Source>> querySourcesByCategory(String... queryTarget) {
        Call<SourcesResponse> responseCall = newsWebService.querySourcesByCategory(queryTarget);

        responseCall.enqueue(new Callback<SourcesResponse>() {
            @Override
            public void onResponse(Call<SourcesResponse> call, Response<SourcesResponse> response) {
                if (response.isSuccessful()) {
                    Timber.d(response.toString());
                    // TODO: PUNE IN BAZA DE DATE;
                    if (response.body() != null) {
                        NewsDatabase.getDatabaseWriteExecutor()
                                .execute(() -> newsDatabase.sourceDao().insertObjects(response.body().getSources()));
                    }
                } else {
                    Timber.d("Response wasn't successful.");
                }
            }

            @Override
            public void onFailure(Call<SourcesResponse> call, Throwable t) {
                Timber.d("Response wasn't successful.");
            }
        });

        return newsDatabase.sourceDao().getSourcesByCategories(queryTarget);
    }

    public LiveData<List<Article>> queryArticles(String... queryTarget) {
        Call<ArticlesResponse> responseCall = newsWebService.queryArticles(queryTarget);

        responseCall.enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(@NotNull Call<ArticlesResponse> call, @NotNull Response<ArticlesResponse> response) {
                // TODO: PUNE IN BAZA DE DATE;
                if (response.isSuccessful() && response.body() != null) {
                    NewsDatabase.getDatabaseWriteExecutor()
                            .execute(() -> newsDatabase.articleDao().insertObjects(response.body().getArticles()));
                }
                Timber.d("RESPONSE: %s", response.toString());
            }

            @Override
            public void onFailure(@NotNull Call<ArticlesResponse> call, @NotNull Throwable t) {
                // TODO: handle case
            }
        });

        return newsDatabase.articleDao().getAllArticles();
    }
}