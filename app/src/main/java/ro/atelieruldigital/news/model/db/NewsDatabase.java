package ro.atelieruldigital.news.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.Category;
import ro.atelieruldigital.news.model.db.entities.Country;
import ro.atelieruldigital.news.model.db.entities.Language;
import ro.atelieruldigital.news.model.db.entities.Source;

@Database(entities = {Article.class, Source.class, Language.class, Category.class, Country.class},
        version = 1)
public abstract class NewsDatabase extends RoomDatabase {
}