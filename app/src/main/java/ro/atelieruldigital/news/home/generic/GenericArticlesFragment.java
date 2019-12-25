package ro.atelieruldigital.news.home.generic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.categories.adapters.ArticleAdapter;
import ro.atelieruldigital.news.home.categories.adapters.SubAdapter;
import ro.atelieruldigital.news.home.categories.viewholders.ArticleViewHolder;
import ro.atelieruldigital.news.model.NewsViewModel;
import ro.atelieruldigital.news.model.db.entities.Article;

public abstract class GenericArticlesFragment extends Fragment {
    public static final String KEY = "ARGS_KEY";

    public GenericArticlesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_articles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_article_language);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        SubAdapter<Article, ArticleViewHolder> articleAdapter = new ArticleAdapter(R.layout.item_article_language);
        recyclerView.setAdapter(articleAdapter);

        Bundle bundle = getArguments();

        if (bundle != null && bundle.getString(KEY) != null) {
            setObservers(ViewModelProviders.of(this).get(NewsViewModel.class), articleAdapter, bundle.getString(KEY));
        }
    }

    protected abstract void setObservers(NewsViewModel newsViewModel, SubAdapter<Article, ArticleViewHolder> articleAdapter, String value);
}
