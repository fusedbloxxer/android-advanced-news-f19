package ro.atelieruldigital.news.model.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Author;

@Dao
public interface AuthorDao extends BaseDao<Author> {
    @Query("SELECT * " +
            "FROM authors")
    LiveData<List<Author>> getAllAuthors();
}
