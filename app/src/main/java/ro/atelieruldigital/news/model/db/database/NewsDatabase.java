package ro.atelieruldigital.news.model.db.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ro.atelieruldigital.news.model.db.converters.DateConverter;
import ro.atelieruldigital.news.model.db.daos.ArticleDao;
import ro.atelieruldigital.news.model.db.daos.CategoryDao;
import ro.atelieruldigital.news.model.db.daos.CountryDao;
import ro.atelieruldigital.news.model.db.daos.LanguageDao;
import ro.atelieruldigital.news.model.db.daos.SourceDao;
import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.Category;
import ro.atelieruldigital.news.model.db.entities.Country;
import ro.atelieruldigital.news.model.db.entities.Language;
import ro.atelieruldigital.news.model.db.entities.Source;

@Database(entities = {Article.class, Source.class, Language.class, Category.class,
        Country.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class NewsDatabase extends RoomDatabase {
    private static volatile NewsDatabase INSTANCE;

    public static final int NUMBER_OF_THREADS = 4;

    private static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract SourceDao sourceDao();

    public abstract CountryDao countryDao();

    public abstract ArticleDao articleDao();

    public abstract CategoryDao categoryDao();

    public abstract LanguageDao languageDao();

    public static NewsDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    @NotNull
    private static NewsDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                NewsDatabase.class, "news_database")
                .fallbackToDestructiveMigration()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        databaseWriteExecutor.execute(() -> {
                            getInstance(context).categoryDao().insertObjects(populateCategories());
                            getInstance(context).countryDao().insertObjects(populateCountries());
                            getInstance(context).languageDao().insertObjects(populateLanguages());
                        });
                    }
                })
                .build();
    }

    public static ExecutorService getDatabaseWriteExecutor() {
        return databaseWriteExecutor;
    }

    private static Country[] populateCountries() {
        return new Country[]{
                new Country("ae"),
                new Country("ar"),
                new Country("at"),
                new Country("au"),

                new Country("be"),
                new Country("bg"),
                new Country("br"),

                new Country("ca"),
                new Country("ch"),
                new Country("cn"),
                new Country("co"),
                new Country("cu"),
                new Country("cz"),

                new Country("de"),

                new Country("eg"),
                new Country("es"),

                new Country("fr"),

                new Country("gb"),
                new Country("gr"),

                new Country("hk"),
                new Country("hu"),

                new Country("id"),
                new Country("ie"),
                new Country("il"),
                new Country("in"),
                new Country("it"),
                new Country("is"),

                new Country("jp"),

                new Country("kr"),

                new Country("lt"),
                new Country("lv"),

                new Country("ma"),
                new Country("mx"),
                new Country("my"),

                new Country("ng"),
                new Country("nl"),
                new Country("no"),
                new Country("nz"),

                new Country("ph"),
                new Country("pl"),
                new Country("pt"),
                new Country("pk"),

                new Country("ro"),
                new Country("rs"),
                new Country("ru"),

                new Country("sa"),
                new Country("se"),
                new Country("sg"),
                new Country("si"),
                new Country("sk"),

                new Country("th"),
                new Country("tr"),
                new Country("tw"),

                new Country("ua"),
                new Country("us"),

                new Country("ve"),

                new Country("za"),
                new Country("zh")
        };
    }

    private static Language[] populateLanguages() {
        return new Language[]{
                new Language("ar"),
                new Language("de"),
                new Language("en"),
                new Language("es"),
                new Language("fr"),
                new Language("he"),
                new Language("it"),
                new Language("nl"),
                new Language("no"),
                new Language("pt"),
                new Language("ru"),
                new Language("se"),
                new Language("ud"),
                new Language("zh")
        };
    }

    private static Category[] populateCategories() {
        return new Category[]{
                new Category("business"),
                new Category("entertainment"),
                new Category("general"),
                new Category("health"),
                new Category("science"),
                new Category("sports"),
                new Category("technology")
        };
    }
}
