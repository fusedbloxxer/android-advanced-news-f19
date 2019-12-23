package ro.atelieruldigital.news.model.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "releases",
        primaryKeys = {"article_id", "author_id"},
        foreignKeys = {
                @ForeignKey(entity = Article.class,
                        parentColumns = "article_id",
                        childColumns = "article_id"),
                @ForeignKey(entity = Author.class,
                        parentColumns = "author_id",
                        childColumns = "author_id")
        })
public class Release {
    @NonNull
    @ColumnInfo(name = "article_id")
    private String articleId;

    @NonNull
    @ColumnInfo(name = "author_id")
    private String authorId;

    public Release(@NonNull String articleId, @NonNull String authorId) {
        this.articleId = articleId;
        this.authorId = authorId;
    }

    @NonNull
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(@NonNull String articleId) {
        this.articleId = articleId;
    }

    @NonNull
    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(@NonNull String authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Release{" +
                "articleId='" + articleId + '\'' +
                ", authorId='" + authorId + '\'' +
                '}';
    }
}