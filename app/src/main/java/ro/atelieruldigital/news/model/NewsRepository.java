package ro.atelieruldigital.news.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.Source;
import ro.atelieruldigital.news.model.ws.NewsWebService;
import ro.atelieruldigital.news.model.ws.response.ArticlesResponse;
import ro.atelieruldigital.news.model.ws.response.SourcesResponse;
import timber.log.Timber;

public class NewsRepository {
    // TODO: Add functionality according to API
    private NewsWebService newsWebService;

    private MutableLiveData<List<Article>> articlesMutableLiveData;
    private MutableLiveData<List<Source>> sourcesMutableLiveData;

    public NewsRepository() {
        newsWebService = new NewsWebService();
        articlesMutableLiveData = new MutableLiveData<>();
        sourcesMutableLiveData = new MutableLiveData<>();
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
                        sourcesMutableLiveData.setValue(response.body().getSources());
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

        return sourcesMutableLiveData;
    }

    public LiveData<List<Article>> queryArticles(String... queryTarget) {
        Call<ArticlesResponse> responseCall = newsWebService.queryArticles();

        responseCall.enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(@NotNull Call<ArticlesResponse> call, @NotNull Response<ArticlesResponse> response) {
                if (response.isSuccessful()) {
                    Timber.d(response.toString());
                    // TODO: PUNE IN BAZA DE DATE;
                    if (response.body() != null) {
                        articlesMutableLiveData.setValue(response.body().getArticles());
                    }
                } else {
                    Timber.d("Response wasn't successful.");
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArticlesResponse> call, @NotNull Throwable t) {
                // TODO: handle case
            }
        });

        return articlesMutableLiveData;
    }
}
