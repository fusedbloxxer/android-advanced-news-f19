package ro.atelieruldigital.news.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ro.atelieruldigital.news.model.db.containers.CategoryArticle;
import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.Source;

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository newsRepository;

    public NewsViewModel(@NonNull final Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
    }

    public LiveData<List<Article>> queryArticles(String... queryTarget) {
        return newsRepository.queryArticles(queryTarget);
    }

    public LiveData<List<Source>> querySourcesByCategory(String... queryTarget) {
        return newsRepository.querySourcesByCategory(queryTarget);
    }

    public LiveData<List<CategoryArticle>> getCategoriesWithArticles() {
        return newsRepository.getCategoriesWithArticles();
    }
}