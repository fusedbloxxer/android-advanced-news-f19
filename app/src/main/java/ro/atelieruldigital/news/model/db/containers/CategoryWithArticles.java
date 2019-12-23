package ro.atelieruldigital.news.model.db.containers;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.Category;

public class CategoryWithArticles {

    private Category category;
    private List<Article> articles;

    public CategoryWithArticles(Category category, List<Article> articles) {
        this.category = category;
        this.articles = articles;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @NotNull
    @Override
    public String toString() {
        return "CategoryWithArticles{" +
                "category=" + category +
                ", articles=" + articles +
                '}';
    }
}
