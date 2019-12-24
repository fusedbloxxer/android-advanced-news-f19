package ro.atelieruldigital.news.model.db.containers;

import androidx.room.Embedded;
import androidx.room.Relation;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ro.atelieruldigital.news.model.db.entities.Category;
import ro.atelieruldigital.news.model.db.entities.Source;

public class CategoryWithSources {
    @Embedded
    private Category category;

    @Relation(
            parentColumn = "categories.category_id",
            entityColumn = "sources.category_id"
    )
    private List<Source> sources;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    @NotNull
    @Override
    public String toString() {
        return "CategoryWithSources{" +
                "category=" + category +
                ", sources=" + sources +
                '}';
    }
}
