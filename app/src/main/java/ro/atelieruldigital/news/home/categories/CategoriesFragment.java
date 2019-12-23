package ro.atelieruldigital.news.home.categories;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.core.BaseFragment;
import ro.atelieruldigital.news.model.NewsViewModel;
import ro.atelieruldigital.news.model.db.entities.Source;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends BaseFragment {

    private NewsViewModel mNewsViewModel;
    private TextView mTextView;
    // private CategoriesAdapter categoriesAdapter;

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

        mTextView = view.findViewById(R.id.test_text_view);

        NewsViewModel newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        LifecycleOwner owner = this;

        newsViewModel.querySourcesByCategory("technology").observe(owner, new Observer<List<Source>>() {
            @Override
            public void onChanged(List<Source> sources) {
                if (sources != null) {
                        mTextView.setText(sources.toString());
                    }
// newsViewModel.getCategoriesWithArticles().observe(owner, categoryWithArticles -> {
//                    if (categoryWithArticles != null) {
//                        mTextView.setText(categoryWithArticles.toString());
//                    }
//                });
            }
        });


//        RecyclerView recyclerView = view.findViewById(R.id.categories_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        categoriesAdapter = new CategoriesAdapter();
//        recyclerView.setAdapter(categoriesAdapter);
//
//        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
//        mNewsViewModel.
    }

    private void setUpViews() {
    }

    private void initViews(View view) {
        // mRecyclerView = view.findViewById(R.id.categories_recycler_view);
    }
}