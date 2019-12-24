package ro.atelieruldigital.news.model.db.entities;

/*
* {
"id": "time",
"name": "Time",
"description": "Breaking news and analysis from TIME.com. Politics, world news, photos, video, tech reviews, health, science and entertainment news.",
"url": "http://time.com",
"category": "general",
"language": "en",
"country": "us"
}
*
* */

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "sources", foreignKeys = {
        @ForeignKey(entity = Category.class,
                parentColumns = "category_id",
                childColumns = "category_id"),
        @ForeignKey(entity = Language.class,
                parentColumns = "language_id",
                childColumns = "language_id"),
        @ForeignKey(entity = Country.class,
                parentColumns = "country_id",
                childColumns = "country_id")
})
public class Source implements IUId<String> {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "source_id")
    private String id;

    private String name;

    private String description;

    private String url;

    @ColumnInfo(name = "category_id")
    private String category;

    @ColumnInfo(name = "language_id")
    private String language;

    @ColumnInfo(name = "country_id")
    private String country;

    public Source(@NonNull String id, String name, String description, String url, String category, String language, String country) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @NotNull
    @Override
    public String toString() {
        return "Source{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", category='" + category + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                '}' + '\n';
    }
}
