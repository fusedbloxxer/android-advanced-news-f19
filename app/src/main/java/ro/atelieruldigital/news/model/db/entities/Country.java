package ro.atelieruldigital.news.model.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "countries")
public class Country implements IUId<String> {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "country_id")
    private String id;

    public Country(@NonNull String id) {
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
        return "Country{" +
                "id='" + id + '\'' +
                '}';
    }
}
