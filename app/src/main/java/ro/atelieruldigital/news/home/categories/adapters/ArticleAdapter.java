package ro.atelieruldigital.news.home.categories.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import ro.atelieruldigital.news.home.categories.viewholders.ArticleViewHolder;
import ro.atelieruldigital.news.home.generic.adapters.SubAdapter;
import ro.atelieruldigital.news.model.db.entities.Article;

public class ArticleAdapter extends SubAdapter<Article, ArticleViewHolder> {

    public ArticleAdapter(int layoutResourceId) {
        super(layoutResourceId);
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutResourceId, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = manyList.get(position);

        holder.setAuthor(article.getAuthor());
        holder.setImage(article.getUrlToImage());
        holder.setPublishedAt(article.getPublishedAt());
        holder.setSourceName(article.getSource().getName());
        holder.setTitle(article.getTitle());
        holder.setUrl(article.getId());
        holder.setDescription(article.getDescription());
    }
}
