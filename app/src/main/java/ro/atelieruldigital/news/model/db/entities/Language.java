package ro.atelieruldigital.news.model.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "languages")
public class Language implements IUId<String> {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "language_id")
    private String id;

    public Language(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NotNull
    @Override
    public String toString() {
        return "Language{" +
                "id='" + id + '\'' +
                '}';
    }
}
