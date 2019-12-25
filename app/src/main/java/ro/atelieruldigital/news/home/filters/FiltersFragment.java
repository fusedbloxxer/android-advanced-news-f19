package ro.atelieruldigital.news.home.filters;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
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
    private LiveData<List<Source>> mAllSources;
    private LiveData<List<Country>> mAllCountries;
    private LiveData<List<Language>> mAllLanguages;
    private LiveData<List<Category>> mAllCategories;
    private LiveData<HashMap<String, HashMap<String, Boolean>>> mHashMap;

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
                    removeAlertDialogObservers();
                    setObservers(subAdapter);
                })
                .setNegativeButton("Cancel", (dialog, which) -> removeAlertDialogObservers())
                .setOnDismissListener(dialog -> removeAlertDialogObservers())
                .setOnCancelListener(dialog -> removeAlertDialogObservers())
                .create();

        RecyclerView container = dialogView.findViewById(R.id.container_recycler_view);
        container.setLayoutManager(new LinearLayoutManager(dialogView.getContext(), LinearLayoutManager.VERTICAL, false));
        SelectContainerAdapter selectContainerAdapter = new SelectContainerAdapter(this);
        container.setAdapter(selectContainerAdapter);

        mHashMap = mSelectViewModel.getAllHashMap();
        mAllSources = mNewsViewModel.getAllSources();
        mAllCountries = mNewsViewModel.getAllCountries();
        mAllLanguages = mNewsViewModel.getAllLanguages();
        mAllCategories = mNewsViewModel.getAllCategories();

        filterButton.setOnClickListener(v -> {
            setAlertDialogObservers(selectContainerAdapter);
            filterDialog.show();
        });

        setObservers(subAdapter);
    }

    private void removeAlertDialogObservers() {
        mAllSources.removeObservers(this);
        mAllCountries.removeObservers(this);
        mAllLanguages.removeObservers(this);
        mAllCategories.removeObservers(this);
    }

    private void setAlertDialogObservers(SelectContainerAdapter selectContainerAdapter) {
        mAllSources.observe(this, sources -> {
            for (Source source : sources) {
                mSelectViewModel.addOptions(SelectedViewModel.SOURCES, source.getId());
            }
        });

        mAllCategories.observe(this, categories -> {
            for (Category category : categories) {
                mSelectViewModel.addOptions(SelectedViewModel.CATEGORIES, category.getId());
            }
        });

        mAllCountries.observe(this, countries -> {
            for (Country country : countries) {
                mSelectViewModel.addOptions(SelectedViewModel.COUNTRIES, country.getId());
            }
        });

        mAllLanguages.observe(this, languages -> {
            for (Language language : languages) {
                mSelectViewModel.addOptions(SelectedViewModel.LANGUAGES, language.getId());
            }
        });

        mHashMap.observe(this, selectContainerAdapter::setStringDoubleHash);
    }

    private void setObservers(SubAdapter<Article, ArticleViewHolder> subAdapter) {
        if (subAdapter.getItemCount() == 0) {
            setProgress(false);
        }
        mNewsViewModel.getArticlesByFilters(
                mSelectViewModel.filters(SelectedViewModel.LANGUAGES),
                mSelectViewModel.filters(SelectedViewModel.CATEGORIES),
                mSelectViewModel.filters(SelectedViewModel.COUNTRIES),
                mSelectViewModel.filters(SelectedViewModel.SOURCES))
                .observe(this, articles -> {
                    subAdapter.setManyList(articles);
                    setProgress(articles.size() != 0);
                });
    }

    @Override
    public void updateSelected(String filterType, String selection) {
        mSelectViewModel.toggleFilter(filterType, selection);
    }
}
