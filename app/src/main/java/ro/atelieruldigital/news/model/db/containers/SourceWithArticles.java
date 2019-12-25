package ro.atelieruldigital.news.model.db.containers;

import androidx.room.Embedded;
import androidx.room.Relation;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.Source;

public class SourceWithArticles {
    @Embedded
    private Source source;

    @Relation(
            parentColumn = "source_id",
            entityColumn = "source_id"
    )

    private List<Article> articles;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
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
        return "SourceWithArticles{" +
                "source=" + source +
                ", articles=" + articles +
                '}';
    }
}
