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
    @Query("SELECT * FROM articles ORDER BY article_id ASC")
    LiveData<List<Article>> getAllArticles();

    @Query("SELECT * " +
            "FROM articles " +
            "ORDER BY published_at DESC")
    LiveData<List<Article>> getLatestArticles();

    @Query("SELECT * " +
            "FROM articles " +
            "WHERE INSTR(LOWER(title||' '||content), LOWER(:keyword))<>0 " +
            "ORDER BY LENGTH(title||' '||content) - LENGTH(REPLACE(LOWER(title||' '||content), LOWER(:keyword), '')) DESC")
    LiveData<List<Article>> getArticlesByKeyword(String keyword);

    @Query("SELECT articles.* " +
            "FROM articles " +
            "JOIN sources USING(source_id)" +
            "WHERE language_id IN (:languages)" +
            "ORDER BY published_at DESC")
    LiveData<List<Article>> getArticlesByLanguages(String... languages);

    @Query("SELECT articles.* " +
            "FROM articles " +
            "JOIN sources USING(source_id) " +
            "WHERE language_id IN(:languages) " +
            "AND category_id IN(:categories) " +
            "AND country_id IN(:countries) " +
            "AND (LOWER(sources.source_id) IN(:sources) OR LOWER(source_name) IN (:sources))" +
            "ORDER BY published_at DESC")
    LiveData<List<Article>> getArticlesByFilters(String[] languages, String[] categories, String[] countries, String[] sources);

    @Query("SELECT articles.* " +
            "FROM articles " +
            "JOIN sources USING(source_id) " +
            "WHERE country_id IN (:countries)" +
            "ORDER BY country_id ASC, published_at DESC")
    LiveData<List<Article>> getArticlesByCountries(String... countries);

    @Query("SELECT articles.* " +
            "FROM articles " +
            "JOIN sources USING(source_id) " +
            "WHERE category_id IN (:categories)" +
            "ORDER BY category_id ASC, published_at DESC")
    LiveData<List<Article>> getArticlesByCategories(String... categories);

    @Query("SELECT category_id, articles.* " +
            "FROM articles " +
            "JOIN sources USING(source_id)" +
            "ORDER BY category_id ASC, published_at DESC")
    LiveData<List<CategoryWithArticle>> getCategoriesWithArticles();

    @Query("SELECT articles.* " +
            "FROM articles " +
            "WHERE source_id IN (:sources)" +
            "ORDER BY source_id ASC, published_at DESC")
    LiveData<List<Article>> getArticlesBySources(String... sources);

    @Query("SELECT articles.* " +
            "FROM articles " +
            "WHERE author IN (:authors)" +
            "ORDER BY author ASC, published_at DESC")
    LiveData<List<Article>> getArticlesByAuthors(String... authors);
}