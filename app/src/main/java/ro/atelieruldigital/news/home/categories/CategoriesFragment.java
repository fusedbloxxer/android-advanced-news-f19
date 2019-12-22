package ro.atelieruldigital.news.home.categories;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import org.jetbrains.annotations.NotNull;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.core.BaseFragment;
import ro.atelieruldigital.news.databinding.FragmentCategoriesBinding;
import ro.atelieruldigital.news.model.NewsViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends BaseFragment {

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentCategoriesBinding fragmentCategoriesBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_categories, container, false);

        NewsViewModel newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        fragmentCategoriesBinding.setVariable(ro.atelieruldigital.news.BR.newsViewModel, newsViewModel);

        return fragmentCategoriesBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}