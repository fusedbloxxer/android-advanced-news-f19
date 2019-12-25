package ro.atelieruldigital.news.model.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Country;

@Dao
public interface CountryDao extends IBaseDao<Country> {
    @Query("SELECT * FROM countries ORDER BY country_id ASC")
    LiveData<List<Country>> getAllCountries();
}
