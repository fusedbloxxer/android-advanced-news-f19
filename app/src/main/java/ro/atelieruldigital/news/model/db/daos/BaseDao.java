package ro.atelieruldigital.news.model.db.daos;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

public interface BaseDao <T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticle(T article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(T... articles);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<T> articles);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateArticle(T article);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateArticles(T... articles);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateArticles(List<T> articles);

    @Delete
    void deleteArticle(T article);

    @Delete
    void deleteArticles(T... articles);

    @Delete
    void deleteArticles(List<T> articles);
}
