package ro.atelieruldigital.news.model.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Source;

@Dao
public interface SourceDao extends BaseDao<Source> {
    @Query("SELECT * " +
            "FROM sources")
    LiveData<List<Source>> getAllSources();

    @Query("SELECT * " +
            "FROM sources " +
            "WHERE category_id IN (:category)")
    LiveData<List<Source>> getSourcesByCategories(String... category);

    @Query("SELECT * " +
            "FROM sources " +
            "WHERE language_id IN (:language)")
    LiveData<List<Source>> getSourcesByLanguages(String... language);

    @Query("SELECT * " +
            "FROM sources " +
            "WHERE country_id IN (:country)")
    LiveData<List<Source>> getSourcesByCountries(String... country);
}
