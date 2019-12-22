package ro.atelieruldigital.news.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.Source;

public class NewsViewModel extends ViewModel {
    private NewsRepository newsRepository;

    public NewsViewModel() {
        newsRepository = new NewsRepository();
    }

    public LiveData<List<Article>> queryArticles(String... queryTarget) {
        return newsRepository.queryArticles(queryTarget);
    }

    public LiveData<List<Source>> querySourcesByCategory(String... queryTarget) {
        return newsRepository.querySourcesByCategory(queryTarget);
    }
}
