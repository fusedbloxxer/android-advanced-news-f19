package ro.atelieruldigital.news.model.db.containers;

import androidx.room.Embedded;

import org.jetbrains.annotations.NotNull;

import ro.atelieruldigital.news.model.db.entities.Article;

public class CategoryArticle {
    private String category;

    @Embedded
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NotNull
    @Override
    public String toString() {
        return "CategoryArticle{" +
                "category='" + category + '\'' +
                ", article=" + article +
                '}';
    }
}
