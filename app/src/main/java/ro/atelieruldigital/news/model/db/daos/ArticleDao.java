package ro.atelieruldigital.news.model.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.List;

import ro.atelieruldigital.news.model.db.containers.CategoryWithArticle;
import ro.atelieruldigital.news.model.db.converters.DateConverter;
import ro.atelieruldigital.news.model.db.entities.Article;

@Dao
@TypeConverters(DateConverter.class)
public interface ArticleDao extends IBaseDao<Article> {
    @Query("SELECT * FROM articles")
    LiveData<List<Article>> getAllArticles();

    @Query("SELECT * " +
            "FROM articles " +
            "ORDER BY articles.published_at DESC")
    LiveData<List<Article>> getLatestArticles();

    @Query("SELECT * " +
            "FROM articles " +
            "WHERE INSTR(title||' '||content, :keyword)<>0")
    LiveData<List<Article>> getArticlesByKeyword(String keyword);

    @Query("SELECT articles.* " +
            "FROM articles " +
            "JOIN sources USING(source_id)" +
            "WHERE language_id IN (:languages)")
    LiveData<List<Article>> getArticlesByLanguages(String... languages);

    @Query("SELECT articles.* " +
            "FROM articles " +
            "JOIN sources USING(source_id) " +
            "WHERE category_id IN (:categories)")
    LiveData<List<Article>> getArticlesByCategories(String... categories);

    @Query("SELECT category_id, articles.* " +
            "FROM articles " +
            "JOIN sources USING(source_id)")
    LiveData<List<CategoryWithArticle>> getCategoriesWithArticles();

    @Query("SELECT articles.* " +
            "FROM articles " +
            "WHERE source_id IN (:sources)")
    LiveData<List<Article>> getArticlesBySources(String... sources);

    @Query("SELECT articles.* " +
            "FROM articles " +
            "WHERE author IN (:authors)")
    LiveData<List<Article>> getArticlesByAuthors(String... authors);
}