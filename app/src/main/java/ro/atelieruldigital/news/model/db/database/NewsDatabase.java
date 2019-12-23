package ro.atelieruldigital.news.model.db.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ro.atelieruldigital.news.model.db.converters.DateConverter;
import ro.atelieruldigital.news.model.db.daos.ArticleDao;
import ro.atelieruldigital.news.model.db.daos.AuthorDao;
import ro.atelieruldigital.news.model.db.daos.CategoryDao;
import ro.atelieruldigital.news.model.db.daos.CountryDao;
import ro.atelieruldigital.news.model.db.daos.LanguageDao;
import ro.atelieruldigital.news.model.db.daos.SourceDao;
import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.ArticleSource;
import ro.atelieruldigital.news.model.db.entities.Author;
import ro.atelieruldigital.news.model.db.entities.Category;
import ro.atelieruldigital.news.model.db.entities.Country;
import ro.atelieruldigital.news.model.db.entities.Language;
import ro.atelieruldigital.news.model.db.entities.Release;
import ro.atelieruldigital.news.model.db.entities.Source;

@Database(entities = {Article.class, ArticleSource.class, Author.class, Release.class, Source.class,
        Language.class, Category.class, Country.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class NewsDatabase extends RoomDatabase {
    private static volatile NewsDatabase INSTANCE;

    public static final int NUMBER_OF_THREADS = 4;

    private static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract AuthorDao authorDao();

    public abstract SourceDao sourceDao();

    public abstract CountryDao countryDao();

    public abstract ArticleDao articleDao();

    public abstract CategoryDao categoryDao();

    public abstract LanguageDao languageDao();

    public static NewsDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NewsDatabase.class, "news_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static ExecutorService getDatabaseWriteExecutor() {
        return databaseWriteExecutor;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {
                insertCountries();
                insertLanguages();
                insertCategories();
            });
        }

        private void insertCountries() {
            CountryDao countryDao = INSTANCE.countryDao();

            countryDao.insertObject(new Country("ae"));
            countryDao.insertObject(new Country("ar"));
            countryDao.insertObject(new Country("at"));
            countryDao.insertObject(new Country("au"));

            countryDao.insertObject(new Country("be"));
            countryDao.insertObject(new Country("bg"));
            countryDao.insertObject(new Country("br"));

            countryDao.insertObject(new Country("ca"));
            countryDao.insertObject(new Country("ch"));
            countryDao.insertObject(new Country("cn"));
            countryDao.insertObject(new Country("co"));
            countryDao.insertObject(new Country("cu"));
            countryDao.insertObject(new Country("cz"));

            countryDao.insertObject(new Country("de"));

            countryDao.insertObject(new Country("eg"));

            countryDao.insertObject(new Country("fr"));

            countryDao.insertObject(new Country("gb"));
            countryDao.insertObject(new Country("gr"));

            countryDao.insertObject(new Country("hk"));
            countryDao.insertObject(new Country("hu"));

            countryDao.insertObject(new Country("id"));
            countryDao.insertObject(new Country("ie"));
            countryDao.insertObject(new Country("il"));
            countryDao.insertObject(new Country("in"));
            countryDao.insertObject(new Country("it"));

            countryDao.insertObject(new Country("jp"));

            countryDao.insertObject(new Country("kr"));

            countryDao.insertObject(new Country("lt"));
            countryDao.insertObject(new Country("lv"));

            countryDao.insertObject(new Country("ma"));
            countryDao.insertObject(new Country("mx"));
            countryDao.insertObject(new Country("my"));

            countryDao.insertObject(new Country("ng"));
            countryDao.insertObject(new Country("nl"));
            countryDao.insertObject(new Country("no"));
            countryDao.insertObject(new Country("nz"));

            countryDao.insertObject(new Country("ph"));
            countryDao.insertObject(new Country("pl"));
            countryDao.insertObject(new Country("pt"));

            countryDao.insertObject(new Country("ro"));
            countryDao.insertObject(new Country("rs"));
            countryDao.insertObject(new Country("ru"));

            countryDao.insertObject(new Country("sa"));
            countryDao.insertObject(new Country("se"));
            countryDao.insertObject(new Country("sg"));
            countryDao.insertObject(new Country("si"));
            countryDao.insertObject(new Country("sk"));

            countryDao.insertObject(new Country("th"));
            countryDao.insertObject(new Country("tr"));
            countryDao.insertObject(new Country("tw"));

            countryDao.insertObject(new Country("ua"));
            countryDao.insertObject(new Country("us"));

            countryDao.insertObject(new Country("ve"));
            countryDao.insertObject(new Country("zh"));
        }

        private void insertLanguages() {
            LanguageDao languageDao = INSTANCE.languageDao();
            languageDao.insertObject(new Language("ar"));
            languageDao.insertObject(new Language("de"));
            languageDao.insertObject(new Language("en"));
            languageDao.insertObject(new Language("es"));
            languageDao.insertObject(new Language("fr"));
            languageDao.insertObject(new Language("he"));
            languageDao.insertObject(new Language("it"));
            languageDao.insertObject(new Language("nl"));
            languageDao.insertObject(new Language("no"));
            languageDao.insertObject(new Language("pt"));
            languageDao.insertObject(new Language("ru"));
            languageDao.insertObject(new Language("se"));
            languageDao.insertObject(new Language("ud"));
            languageDao.insertObject(new Language("zh"));
        }

        private void insertCategories() {
            CategoryDao categoryDao = INSTANCE.categoryDao();
            categoryDao.insertObject(new Category("business"));
            categoryDao.insertObject(new Category("entertainment"));
            categoryDao.insertObject(new Category("general"));
            categoryDao.insertObject(new Category("health"));
            categoryDao.insertObject(new Category("science"));
            categoryDao.insertObject(new Category("sports"));
            categoryDao.insertObject(new Category("technology"));
        }
    };
}
