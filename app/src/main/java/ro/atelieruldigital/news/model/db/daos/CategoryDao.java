package ro.atelieruldigital.news.model.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Category;

@Dao
public interface CategoryDao extends IBaseDao<Category> {
    @Query("SELECT * FROM categories ORDER BY category_id ASC")
    LiveData<List<Category>> getAllCategories();
}