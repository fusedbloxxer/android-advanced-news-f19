package ro.atelieruldigital.news.model.db.containers;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Article;

public class CategoryWithArticles {
    private String category_id;

    private List<Article> articles;

    public CategoryWithArticles(String category_id, List<Article> articles) {
        this.category_id = category_id;
        this.articles = articles;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
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
                "category_id='" + category_id + '\'' +
                ", articles=" + articles +
                '}';
    }
}
