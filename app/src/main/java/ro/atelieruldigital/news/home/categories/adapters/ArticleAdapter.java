package ro.atelieruldigital.news.home.categories.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.categories.viewholders.ArticleViewHolder;
import ro.atelieruldigital.news.model.db.entities.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private List<Article> articleList;

    public ArticleAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    public ArticleAdapter() {
        this(new ArrayList<>());
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);

        holder.setAuthor(article.getAuthor());
        holder.setImage(article.getUrlToImage());
        holder.setPublishedAt(article.getPublishedAt());
        holder.setSourceName(article.getSource().getName());
        holder.setTitle(article.getTitle());
        holder.setUrl(article.getId());
        holder.setDescription(article.getDescription());
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
