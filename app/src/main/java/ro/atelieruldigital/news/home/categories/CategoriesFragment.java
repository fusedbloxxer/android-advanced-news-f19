package ro.atelieruldigital.news.home.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.core.BaseFragment;
import ro.atelieruldigital.news.home.categories.adapters.ArticleContainerAdapter;
import ro.atelieruldigital.news.model.NewsViewModel;
import ro.atelieruldigital.news.model.db.containers.CategoryWithArticle;
import ro.atelieruldigital.news.model.db.entities.IUId;
import ro.atelieruldigital.news.model.ws.NewsWebService;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends BaseFragment {
    private ArticleContainerAdapter mArticleAdapter;
    private RecyclerView mRecyclerView;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setUpViews();
        setObservers();
    }

    private void initViews(View view) {
        mRecyclerView = view.findViewById(R.id.categories_recycler_view);
    }

    private void setUpViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mArticleAdapter = new ArticleContainerAdapter());
    }

    private void setObservers() {
        NewsViewModel newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        LifecycleOwner owner = this;
        newsViewModel
                .getAllCategories()
                .observe(owner, categories ->
                        newsViewModel
                                .querySourcesByCategories(IUId.params(categories.size(), categories))
                                .observe(owner, sources -> {
                                    Collections.shuffle(sources);
                                    newsViewModel
                                            .getArticlesBySources(IUId.params(Math.min(NewsWebService.MAX_SOURCES, sources.size()), sources))
                                            .observe(owner, articles ->
                                                    newsViewModel
                                                            .getCategoriesWithArticles()
                                                            .observe(owner, categoryWithArticleList -> mArticleAdapter.setCategoryWithArticles(CategoryWithArticle.transform(categoryWithArticleList))));
                                }));
    }
}