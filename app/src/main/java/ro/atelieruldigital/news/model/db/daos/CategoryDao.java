package ro.atelieruldigital.news.model.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Category;

@Dao
public interface CategoryDao extends BaseDao<Category> {
    @Query("SELECT * FROM categories")
    LiveData<List<Category>> getAllCategories();
}
