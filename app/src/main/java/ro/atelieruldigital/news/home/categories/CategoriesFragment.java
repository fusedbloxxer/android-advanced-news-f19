package ro.atelieruldigital.news.home.categories;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.core.LoadingFragment;
import ro.atelieruldigital.news.home.categories.adapters.ArticleContainerAdapter;
import ro.atelieruldigital.news.model.NewsViewModel;
import ro.atelieruldigital.news.model.db.containers.CategoryWithArticle;
import ro.atelieruldigital.news.model.db.containers.OneToMany;
import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.IUId;
import ro.atelieruldigital.news.model.ws.NewsWebService;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends LoadingFragment {
    private ArticleContainerAdapter mArticleAdapter;
    private RecyclerView mRecyclerView;

    public CategoriesFragment() {
        super(R.layout.fragment_with_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setUpViews();
        setObservers();
    }

    private void initViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
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
                                                            .observe(owner, categoryWithArticleList -> {
                                                                List<OneToMany<String, Article>> oneToManyList = CategoryWithArticle.transform(categoryWithArticleList);
                                                                mArticleAdapter.setOneToManyList(oneToManyList);
                                                                setProgress(oneToManyList.size() != 0);
                                                            }));
                                }));
    }
}