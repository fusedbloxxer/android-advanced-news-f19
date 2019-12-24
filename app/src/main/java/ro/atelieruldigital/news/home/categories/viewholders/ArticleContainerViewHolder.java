package ro.atelieruldigital.news.home.categories.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.categories.adapters.ArticleAdapter;
import ro.atelieruldigital.news.model.db.entities.Article;

public class ArticleContainerViewHolder extends RecyclerView.ViewHolder {
    private TextView mCategory;
    private RecyclerView mRecyclerView;
    private ArticleAdapter mArticleAdapter;

    public ArticleContainerViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
        setUpViews();
    }

    private void setUpViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(mArticleAdapter = new ArticleAdapter());
    }

    private void initViews(View itemView) {
        mCategory = itemView.findViewById(R.id.article_container_title);
        mRecyclerView = itemView.findViewById(R.id.article_container_recycler_view);
    }

    public void setCategory(String category) {
        if (category != null) {
            mCategory.setText(category);
        }
    }

    public void setArticles(List<Article> articles) {
        mArticleAdapter.setArticleList(articles);
    }
}
