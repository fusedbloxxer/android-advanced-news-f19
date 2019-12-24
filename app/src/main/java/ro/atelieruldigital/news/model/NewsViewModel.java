package ro.atelieruldigital.news.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ro.atelieruldigital.news.model.db.containers.CategoryWithArticle;
import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.Category;
import ro.atelieruldigital.news.model.db.entities.Country;
import ro.atelieruldigital.news.model.db.entities.Language;
import ro.atelieruldigital.news.model.db.entities.Source;

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository newsRepository;

    public NewsViewModel(@NonNull final Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
    }

    public LiveData<List<Article>> queryArticles(String... q) {
        return newsRepository.queryArticles(q);
    }

    public LiveData<List<Language>> getAllLanguages() {
        return newsRepository.getAllLanguages();
    }

    public LiveData<List<Country>> getAllCountries() {
        return newsRepository.getAllCountries();
    }

    public LiveData<List<Category>> getAllCategories() {
        return newsRepository.getAllCategories();
    }

    public LiveData<List<Source>> querySourcesByCategories(String... categories) {
        return newsRepository.querySourcesByCategories(categories);
    }

    public LiveData<List<CategoryWithArticle>> getCategoriesWithArticles() {
        return newsRepository.getCategoriesWithArticles();
    }

    public LiveData<List<Article>> getArticlesBySources(String... sources) {
        return newsRepository.queryArticlesBySources(sources);
    }

    public LiveData<List<Source>> querySources() {
        return newsRepository.querySources();
    }
}