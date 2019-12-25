package ro.atelieruldigital.news.model.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import ro.atelieruldigital.news.model.db.containers.SourceWithArticles;
import ro.atelieruldigital.news.model.db.entities.Source;

@Dao
public interface SourceDao extends IBaseDao<Source> {
    @Query("SELECT * " +
            "FROM sources " +
            "ORDER BY source_id ASC")
    LiveData<List<Source>> getAllSources();

    @Query("SELECT * " +
            "FROM sources " +
            "WHERE category_id IN (:category)" +
            "ORDER BY source_id ASC")
    LiveData<List<Source>> getSourcesByCategories(String... category);

    @Query("SELECT * " +
            "FROM sources " +
            "WHERE language_id IN (:language) " +
            "ORDER BY source_id ASC")
    LiveData<List<Source>> getSourcesByLanguages(String... language);

    @Query("SELECT * " +
            "FROM sources " +
            "WHERE country_id IN (:country)" +
            "ORDER BY source_id ASC")
    LiveData<List<Source>> getSourcesByCountries(String... country);

    @Transaction
    @Query("SELECT * FROM sources")
    LiveData<List<SourceWithArticles>> getSourcesWithArticles();
}
