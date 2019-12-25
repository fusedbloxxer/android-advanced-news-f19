package ro.atelieruldigital.news.home.categories.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.categories.viewholders.ArticleContainerViewHolder;
import ro.atelieruldigital.news.home.generic.adapters.ContainerAdapter;
import ro.atelieruldigital.news.model.db.entities.Article;

public class ArticleContainerAdapter extends ContainerAdapter<String, Article, ArticleContainerViewHolder> {

    @NonNull
    @Override
    public ArticleContainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container_article, parent, false);

        return new ArticleContainerViewHolder(view);
    }
}
