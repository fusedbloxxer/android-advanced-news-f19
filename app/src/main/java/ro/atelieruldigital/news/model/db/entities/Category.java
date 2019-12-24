package ro.atelieruldigital.news.model.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "categories")
public class Category implements IUId<String> {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "category_id")
    private String id;

    public Category(@NonNull String id) {
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
        return "Category{" +
                "id='" + id + '\'' +
                '}';
    }
}
