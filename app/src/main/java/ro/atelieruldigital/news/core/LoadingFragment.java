package ro.atelieruldigital.news.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.generic.LoadingViewModel;

public abstract class LoadingFragment extends BaseFragment {

    private int mLayoutResourceId;
    private LoadingViewModel mLoadingViewModel;

    protected LoadingFragment(int layoutResourceId) {
        this.mLayoutResourceId = layoutResourceId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(mLayoutResourceId, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        mLoadingViewModel = ViewModelProviders.of(this).get(LoadingViewModel.class);
        mLoadingViewModel.isLoaded().observe(this,
                loaded -> progressBar.setVisibility(loaded ? View.GONE : View.VISIBLE));
    }

    protected final void setProgress(Boolean loaded) {
        mLoadingViewModel.setLoaded(loaded);
    }

    protected final Boolean getProgress() {
        return mLoadingViewModel.isLoaded().getValue();
    }
}