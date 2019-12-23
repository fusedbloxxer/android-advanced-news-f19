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
"url": "https://twocents.lifehacker.com/invest-in-crypto-stocks-instead-of-actual-cryptocurrenc-1840264223",
"urlToImage": "https://i.kinja-img.com/gawker-media/image/upload/c_fill,f_auto,fl_progressive,g_center,h_675,pg_1,q_80,w_1200/valka9d65wlgqgm18cis.jpg",
"publishedAt": "2019-12-06T16:00:00Z",
"content": "Just a few short years ago, buying up cryptocurrency was all the rage. In theory, you could spend a little on Bitcoin or one of the other popular cryptocurrencies, and cash out with an astonishing rate of return. \r\nBut as you might have noticed, Bitcoin and s… [+3033 chars]"
},

*/

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ro.atelieruldigital.news.model.db.converters.DateConverter;

@Entity(tableName = "articles")
public class Article {

    public static final DateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-DD'T'HH:mm:ss'Z'", Locale.ENGLISH);

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "article_id")
    @SerializedName("serverId")
    private int id;


    @ColumnInfo(name = "published_at")
    @TypeConverters(DateConverter.class)
    private Date published;

    @Ignore
    private Source source;

    @Ignore
    private String author;

    private String title;

    private String description;

    private String url;

    private String urlToImage;

    @Ignore
    private String publishedAt;

    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", published=" + published +
                ", source=" + source +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
