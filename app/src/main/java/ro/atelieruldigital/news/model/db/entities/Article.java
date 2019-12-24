package ro.atelieruldigital.news.model.db.entities;

/*
FORMAT:

articles": [
-{
-"source": {
"id": null,
"name": "Lifehacker.com"
},
"author": "Lisa Rowan on Two Cents, shared by Lisa Rowan to Lifehacker",
"title": "Invest in Crypto Stocks Instead of Actual Cryptocurrency",
"description": "Just a few short years ago, buying up cryptocurrency was all the rage. In theory, you could spend a little on Bitcoin or one of the other popular cryptocurrencies, and cash out with an astonishing rate of return.",
"id": "https://twocents.lifehacker.com/invest-in-crypto-stocks-instead-of-actual-cryptocurrenc-1840264223",
"urlToImage": "https://i.kinja-img.com/gawker-media/image/upload/c_fill,f_auto,fl_progressive,g_center,h_675,pg_1,q_80,w_1200/valka9d65wlgqgm18cis.jpg",
"publishedAt": "2019-12-06T16:00:00Z",
"content": "Just a few short years ago, buying up cryptocurrency was all the rage. In theory, you could spend a little on Bitcoin or one of the other popular cryptocurrencies, and cash out with an astonishing rate of return. \r\nBut as you might have noticed, Bitcoin and s… [+3033 chars]"
},

*/

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

import ro.atelieruldigital.news.model.db.converters.DateConverter;
import ro.atelieruldigital.news.model.ws.response.SourceObject;

@Entity(tableName = "articles", foreignKeys =
@ForeignKey(entity = Source.class,
        parentColumns = "source_id",
        childColumns = "source_id"))
public class Article implements IUId<String> {

    public static final String DATE_FORMAT = "yyyy-MM-DD'T'HH:mm:ss'Z'";

    @NonNull
    @PrimaryKey
    @SerializedName("url")
    @ColumnInfo(name = "article_id")
    private String id;

    @Embedded(prefix = "source_")
    private SourceObject source;

    @ColumnInfo(name = "url_to_image")
    private String urlToImage;

    @ColumnInfo(name = "published_at")
    @TypeConverters(DateConverter.class)
    private Date publishedAt;

    private String author;

    private String title;

    private String description;

    private String content;

    public Article(SourceObject source, String urlToImage, Date publishedAt, String author, String title, String description, @NonNull String id, String content) {
        this.source = source;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.author = author;
        this.title = title;
        this.description = description;
        this.id = id;
        this.content = content;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public SourceObject getSource() {
        return source;
    }

    public void setSource(SourceObject source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NotNull
    @Override
    public String toString() {
        return "Article{" +
                "source=" + source +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt=" + publishedAt +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
