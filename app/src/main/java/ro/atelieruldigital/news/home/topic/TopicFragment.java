package ro.atelieruldigital.news.home.topic;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.core.LoadingFragment;
import ro.atelieruldigital.news.home.categories.adapters.ArticleAdapter;
import ro.atelieruldigital.news.home.categories.viewholders.ArticleViewHolder;
import ro.atelieruldigital.news.home.generic.adapters.SubAdapter;
import ro.atelieruldigital.news.model.NewsViewModel;
import ro.atelieruldigital.news.model.db.entities.Article;

public class TopicFragment extends LoadingFragment {

    public TopicFragment() {
        super(R.layout.fragment_topic);
    }

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Fragment fragment = this;
        SubAdapter<Article, ArticleViewHolder> articleAdapter;
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        SearchView searchView = view.findViewById(R.id.topic_search_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(articleAdapter = new ArticleAdapter(R.layout.item_article_wide));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ViewModelProviders.of(fragment).get(NewsViewModel.class).queryArticles(query).observe(fragment, articles -> {
                articleAdapter.setManyList(articles);
                setProgress(articles.size() != 0);
            });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
