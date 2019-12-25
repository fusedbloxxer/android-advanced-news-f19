package ro.atelieruldigital.news.home.sources;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.core.LoadingFragment;
import ro.atelieruldigital.news.home.sources.adapters.SourceAdapter;
import ro.atelieruldigital.news.model.NewsViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SourcesFragment extends LoadingFragment {

    public SourcesFragment() {
        super(R.layout.fragment_with_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        SourceAdapter sourceAdapter = new SourceAdapter();
        recyclerView.setAdapter(sourceAdapter);

        ViewModelProviders.of(this).get(NewsViewModel.class).getAllSources()
                .observe(this, sources -> {
                    sourceAdapter.setManyList(sources);
                    setProgress(sources.size() != 0);
                });
    }
}
