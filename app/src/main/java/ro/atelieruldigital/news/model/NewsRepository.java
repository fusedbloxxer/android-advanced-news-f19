package ro.atelieruldigital.news.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.atelieruldigital.news.model.ws.NewsWebService;
import timber.log.Timber;

public class NewsRepository {
    // TODO: Add functionality according to API
    private NewsWebService newsWebService;

    private MutableLiveData<List<Article>> listMutableLiveData;

    public NewsRepository() {
        newsWebService = new NewsWebService();
        listMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Article>> queryArticles(String queryTarget) {
        Call<NewsListResponse> responseCall = newsWebService.queryArticles(queryTarget);

        responseCall.enqueue(new Callback<NewsListResponse>() {
            @Override
            public void onResponse(@NotNull Call<NewsListResponse> call, @NotNull Response<NewsListResponse> response) {
                if (response.isSuccessful()) {
                    Timber.d(response.toString());
                    // TODO: PUNE IN BAZA DE DATE;
                    if (response.body() != null) {
                        listMutableLiveData.setValue(response.body().getArticles());
                    }
                } else {
                    Timber.d("Response wasn't successful.");
                }
            }

            @Override
            public void onFailure(@NotNull Call<NewsListResponse> call, @NotNull Throwable t) {
                // TODO: handle case
            }
        });

        return listMutableLiveData;
    }
}
