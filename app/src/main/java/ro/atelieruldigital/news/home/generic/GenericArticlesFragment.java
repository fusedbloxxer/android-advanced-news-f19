package ro.atelieruldigital.news.home.generic;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.core.LoadingFragment;
import ro.atelieruldigital.news.home.generic.adapters.SubAdapter;
import ro.atelieruldigital.news.model.NewsViewModel;

public abstract class GenericArticlesFragment<Many, ViewHolder extends RecyclerView.ViewHolder> extends LoadingFragment {
    public static final String KEY = "ARGS_KEY";
    private SubAdapter<Many, ViewHolder> subAdapter;

    protected GenericArticlesFragment(int layoutResourceId, SubAdapter<Many, ViewHolder> subAdapter) {
        super(layoutResourceId);
        this.subAdapter = subAdapter;
    }

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(subAdapter);

        Bundle bundle = getArguments();

        if (bundle != null && bundle.getString(KEY) != null) {
            setObservers(ViewModelProviders.of(this).get(NewsViewModel.class), subAdapter, bundle.getString(KEY));
        }
    }

    protected abstract void setObservers(NewsViewModel newsViewModel, SubAdapter<Many, ViewHolder> articleAdapter, String value);
}
