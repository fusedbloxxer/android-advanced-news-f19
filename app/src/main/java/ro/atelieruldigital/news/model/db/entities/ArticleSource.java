package ro.atelieruldigital.news.model.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "articles_sources",
        primaryKeys = {"article_id", "source_id"},
        foreignKeys = {
                @ForeignKey(entity = Article.class,
                        parentColumns = "article_id",
                        childColumns = "article_id"),
                @ForeignKey(entity = Source.class,
                        parentColumns = "source_id",
                        childColumns = "source_id")
        })
public class ArticleSource {
    @NonNull
    @ColumnInfo(name = "article_id")
    private String articleId;

    @NonNull
    @ColumnInfo(name = "source_id")
    private String sourceId;

    public ArticleSource(@NonNull String articleId, @NonNull String sourceId) {
        this.articleId = articleId;
        this.sourceId = sourceId;
    }

    @NonNull
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(@NonNull String articleId) {
        this.articleId = articleId;
    }

    @NonNull
    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(@NonNull String sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public String toString() {
        return "ArticleSource{" +
                "articleId='" + articleId + '\'' +
                ", sourceId='" + sourceId + '\'' +
                '}';
    }
}