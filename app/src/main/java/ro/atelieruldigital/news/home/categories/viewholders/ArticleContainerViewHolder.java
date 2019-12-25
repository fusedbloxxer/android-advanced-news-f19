package ro.atelieruldigital.news.home.categories.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.categories.adapters.ArticleAdapter;
import ro.atelieruldigital.news.home.generic.viewholders.OneToManyViewHolder;
import ro.atelieruldigital.news.model.db.entities.Article;

public class ArticleContainerViewHolder extends OneToManyViewHolder<String, Article, ArticleViewHolder> {
    private TextView mCategory;

    public ArticleContainerViewHolder(@NonNull View itemView) {
        super(itemView, R.id.article_container_recycler_view, new ArticleAdapter(R.layout.item_article));
        mCategory = itemView.findViewById(R.id.article_container_title);
    }

    @Override
    public void setOne(String category) {
        if (category != null) {
            mCategory.setText(category);
        }
    }
}