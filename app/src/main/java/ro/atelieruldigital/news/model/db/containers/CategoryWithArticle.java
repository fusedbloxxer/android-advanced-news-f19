package ro.atelieruldigital.news.model.db.containers;

import androidx.room.Embedded;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import ro.atelieruldigital.news.model.db.entities.Article;

public class CategoryWithArticle {
    private String category_id;

    @Embedded
    private Article article;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public static List<CategoryWithArticles> transform(List<CategoryWithArticle> categoryWithArticles) {
        Map<String, List<Article>> map = new HashMap<>();

        List<CategoryWithArticles> articlesList = new ArrayList<>();

        for (CategoryWithArticle e : categoryWithArticles) {
            if (map.containsKey(e.getCategory_id())) {
                Objects
                        .requireNonNull(map.get(e.getCategory_id()))
                        .add(e.getArticle());
            } else {
                map.put(e.getCategory_id(), new ArrayList<>());
                Objects
                        .requireNonNull(map.get(e.getCategory_id()))
                        .add(e.getArticle());
            }
        }

        for (Map.Entry<String, List<Article>> e : map.entrySet()) {
            articlesList.add(new CategoryWithArticles(capitalize(e), e.getValue()));
        }

        return articlesList;
    }

    @NotNull
    private static String capitalize(Map.Entry<String, List<Article>> e) {
        return e.getKey().substring(0, 1).toUpperCase() + e.getKey().substring(1);
    }

    @NotNull
    @Override
    public String toString() {
        return "CategoryWithArticle{" +
                "category_id='" + category_id + '\'' +
                ", article=" + article +
                '}';
    }
}
