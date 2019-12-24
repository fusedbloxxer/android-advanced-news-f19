package ro.atelieruldigital.news.home.categories.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.categories.viewholders.ArticleContainerViewHolder;
import ro.atelieruldigital.news.model.db.containers.CategoryWithArticles;

public class ArticleContainerAdapter extends RecyclerView.Adapter<ArticleContainerViewHolder> {
    private List<CategoryWithArticles> categoryWithArticles;

    public ArticleContainerAdapter(List<CategoryWithArticles> categoryWithArticles) {
        this.categoryWithArticles = categoryWithArticles;
    }

    public ArticleContainerAdapter() {
        this(new ArrayList<>());
    }

    @NonNull
    @Override
    public ArticleContainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container_article, parent, false);
        return new ArticleContainerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleContainerViewHolder holder, int position) {
        CategoryWithArticles categoryWithArticle = categoryWithArticles.get(position);
        holder.setArticles(categoryWithArticle.getArticles());
        holder.setCategory(categoryWithArticle.getCategory_id());
    }

    public void setCategoryWithArticles(List<CategoryWithArticles> categoryWithArticles) {
        this.categoryWithArticles = categoryWithArticles;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return categoryWithArticles.size();
    }
}
