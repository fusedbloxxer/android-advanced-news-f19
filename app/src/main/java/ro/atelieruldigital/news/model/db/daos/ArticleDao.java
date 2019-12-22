package ro.atelieruldigital.news.model.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Article;

@Dao
public interface ArticleDao extends BaseDao<Article> {

    @Query("SELECT * FROM articles")
    LiveData<List<Article>> getAllArticles();
}

//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertArticle(Article article);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertArticles(Article... articles);
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertArticles(List<Article> articles);
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    void updateArticle(Article article);
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    void updateArticles(Article... articles);
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    void updateArticles(List<Article> articles);
//
//    @Delete
//    void deleteArticle(Article article);
//
//    @Delete
//    void deleteArticles(Article... articles);
//
//    @Delete
//    void deleteArticles(List<Article> articles);