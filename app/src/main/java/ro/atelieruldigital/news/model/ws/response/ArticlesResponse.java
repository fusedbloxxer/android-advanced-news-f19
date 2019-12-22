package ro.atelieruldigital.news.model.ws.response;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Article;

public class ArticlesResponse extends Response {
    private int totalResults;
    private List<Article> articles;

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    @NotNull
    @Override
    public String toString() {
        return "ArticlesResponse{" + super.toString() +
                ", totalResults=" + totalResults +
                ", articles=" + articles +
                '}';
    }
}
