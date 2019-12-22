package ro.atelieruldigital.news.model.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "articles_sources", foreignKeys = {
        @ForeignKey(entity = Article.class,
                parentColumns = "article_id",
                childColumns = "article_id"),
        @ForeignKey(entity = Source.class,
                parentColumns = "source_id",
                childColumns = "source_id")
})
public class ArticleSource {
    @ColumnInfo(name = "article_id")
    private String articleId;

    @ColumnInfo(name = "source_id")
    private String sourceId;
}
