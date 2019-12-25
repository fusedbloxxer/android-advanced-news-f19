package ro.atelieruldigital.news.model.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Language;

@Dao
public interface LanguageDao extends IBaseDao<Language> {
    @Query("SELECT * FROM languages ORDER BY language_id ASC")
    LiveData<List<Language>> getAllLanguages();
}