package ro.atelieruldigital.news.home.filters;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.core.LoadingFragment;
import ro.atelieruldigital.news.home.categories.adapters.ArticleAdapter;
import ro.atelieruldigital.news.home.categories.viewholders.ArticleViewHolder;
import ro.atelieruldigital.news.home.filters.adapters.SelectContainerAdapter;
import ro.atelieruldigital.news.home.filters.utilities.OnToggleUpdateSelected;
import ro.atelieruldigital.news.home.generic.adapters.SubAdapter;
import ro.atelieruldigital.news.model.NewsViewModel;
import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.Category;
import ro.atelieruldigital.news.model.db.entities.Country;
import ro.atelieruldigital.news.model.db.entities.Language;
import ro.atelieruldigital.news.model.db.entities.Source;

public class FiltersFragment extends LoadingFragment implements OnToggleUpdateSelected {

    private NewsViewModel mNewsViewModel;
    private SelectedViewModel mSelectViewModel;

    private Observer<List<Source>> mSourceListObserver;
    private Observer<List<Country>> mCountriesListObserver;
    private Observer<List<Language>> mLanguagesListObserver;
    private Observer<List<Article>> mArticleObserver;
    private Observer<List<Category>> mCategoriesListObserver;
    private Observer<HashMap<String, HashMap<String, Boolean>>> mCheckboxObserver;

    private LiveData<List<Source>> mSourcesLiveData;
    private LiveData<List<Article>> mArticlesLiveData;
    private LiveData<List<Country>> mCountriesLiveData;
    private LiveData<List<Language>> mLanguagesLiveData;
    private LiveData<List<Category>> mCategoriesLiveData;
    private LiveData<HashMap<String, HashMap<String, Boolean>>> mHashMapLiveData;

    public FiltersFragment() {
        super(R.layout.fragment_filters);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        mSelectViewModel = ViewModelProviders.of(this).get(SelectedViewModel.class);

        Button filterButton = view.findViewById(R.id.filters_button);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ArticleAdapter subAdapter = new ArticleAdapter(R.layout.item_article_wide);
        recyclerView.setAdapter(subAdapter);

        View dialogView = getLayoutInflater().inflate(R.layout.item_container, null);
        AlertDialog filterDialog = new AlertDialog.Builder(getContext())
                .setTitle("Choose Filters")
                .setView(dialogView)
                .setPositiveButton("Accept", (dialog, which) -> {
                    mSelectViewModel.setDialogShown(false);
                    attachArticlesObserver(subAdapter);
                })
                .setNegativeButton("Cancel", ((dialog, which) -> {
                    mSelectViewModel.setDialogShown(false);
                    removeDialogObservers();
                }))
                .create();

        RecyclerView container = dialogView.findViewById(R.id.container_recycler_view);
        container.setLayoutManager(new LinearLayoutManager(dialogView.getContext(), LinearLayoutManager.VERTICAL, false));
        SelectContainerAdapter selectContainerAdapter = new SelectContainerAdapter(this);
        container.setAdapter(selectContainerAdapter);

        filterButton.setOnClickListener(v -> {
            mSelectViewModel.setDialogShown(true);
            showDialog(filterDialog);
        });

        setObservers(selectContainerAdapter, subAdapter);
        attachArticlesObserver(subAdapter);

        if (mSelectViewModel.isDialogShown()) {
            showDialog(filterDialog);
        }
    }

    private void showDialog(AlertDialog filterDialog) {
        attachDialogObservers();
        filterDialog.show();
    }

    private void setObservers(SelectContainerAdapter selectContainerAdapter, SubAdapter<Article, ArticleViewHolder> subAdapter) {
        mSourceListObserver = sources -> {
            for (Source source : sources) {
                mSelectViewModel.addOptions(SelectedViewModel.SOURCES, source.getId());
            }
        };
        mCategoriesListObserver = categories -> {
            for (Category category : categories) {
                mSelectViewModel.addOptions(SelectedViewModel.CATEGORIES, category.getId());
            }
        };
        mCountriesListObserver = countries -> {
            for (Country country : countries) {
                mSelectViewModel.addOptions(SelectedViewModel.COUNTRIES, country.getId());
            }
        };
        mLanguagesListObserver = languages -> {
            for (Language language : languages) {
                mSelectViewModel.addOptions(SelectedViewModel.LANGUAGES, language.getId());
            }
        };

        // Update dialog
        mCheckboxObserver = selectContainerAdapter::setStringDoubleHash;

        // Update article list.
        mArticleObserver = articles -> {
            subAdapter.setManyList(articles);
            setProgress(articles.size() != 0);
        };
    }

    private void attachDialogObservers() {
        removeDialogObservers();

        mSourcesLiveData = mNewsViewModel.getAllSources();
        mHashMapLiveData = mSelectViewModel.getAllHashMap();
        mCountriesLiveData = mNewsViewModel.getAllCountries();
        mLanguagesLiveData = mNewsViewModel.getAllLanguages();
        mCategoriesLiveData = mNewsViewModel.getAllCategories();

        mHashMapLiveData.observe(this, mCheckboxObserver);
        mSourcesLiveData.observe(this, mSourceListObserver);
        mCountriesLiveData.observe(this, mCountriesListObserver);
        mLanguagesLiveData.observe(this, mLanguagesListObserver);
        mCategoriesLiveData.observe(this, mCategoriesListObserver);
    }

    private void removeDialogObservers() {
        if (mSourcesLiveData != null) {
            mSourcesLiveData.removeObserver(mSourceListObserver);
        }
        if (mCategoriesLiveData != null) {
            mCategoriesLiveData.removeObserver(mCategoriesListObserver);
        }
        if (mCountriesLiveData != null) {
            mCountriesLiveData.removeObserver(mCountriesListObserver);
        }
        if (mLanguagesLiveData != null) {
            mLanguagesLiveData.removeObserver(mLanguagesListObserver);
        }
        if (mHashMapLiveData != null) {
            mHashMapLiveData.removeObserver(mCheckboxObserver);
        }
    }

    private void attachArticlesObserver(SubAdapter<Article, ArticleViewHolder> subAdapter) {
        if (subAdapter.getItemCount() == 0 && getProgress()) {
            setProgress(false);
        }

        if (mArticlesLiveData != null) {
            mArticlesLiveData.removeObserver(mArticleObserver);
        }

        mArticlesLiveData = mNewsViewModel.getArticlesByFilters(
                mSelectViewModel.filters(SelectedViewModel.LANGUAGES),
                mSelectViewModel.filters(SelectedViewModel.CATEGORIES),
                mSelectViewModel.filters(SelectedViewModel.COUNTRIES),
                mSelectViewModel.filters(SelectedViewModel.SOURCES));

        mArticlesLiveData.observe(this, mArticleObserver);
    }

    @Override
    public void updateSelected(String filterType, String selection) {
        mSelectViewModel.toggleFilter(filterType, selection);
    }
}