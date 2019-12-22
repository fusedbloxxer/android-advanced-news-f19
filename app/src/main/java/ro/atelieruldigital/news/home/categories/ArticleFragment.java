package ro.atelieruldigital.news.home.categories;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.core.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends BaseFragment {


    public ArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

}
