package ro.atelieruldigital.news.home.sources.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.generic.adapters.SubAdapter;
import ro.atelieruldigital.news.home.sources.viewholders.SourceViewHolder;
import ro.atelieruldigital.news.model.db.entities.Source;

public class SourceAdapter extends SubAdapter<Source, SourceViewHolder> {

    public SourceAdapter() {
        super(R.layout.item_source);
    }

    @NonNull
    @Override
    public SourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layoutResourceId, parent, false);
        return new SourceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SourceViewHolder holder, int position) {
        Source source = manyList.get(position);

        holder.setCategory(source.getCategory());
        holder.setCountry(source.getCountry());
        holder.setDescription(source.getDescription());
        holder.setFindMore(source.getUrl());
        holder.setLanguage(source.getLanguage());
        holder.setTitle(source.getName());
        holder.setSourceId(source.getId());
    }
}
