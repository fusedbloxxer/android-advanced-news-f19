package ro.atelieruldigital.news.home.filters.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.filters.utilities.OnToggleUpdateSelected;
import ro.atelieruldigital.news.home.filters.utilities.ToggledPair;
import ro.atelieruldigital.news.home.filters.adapters.SelectAdapter;
import ro.atelieruldigital.news.utils.Utilities;

public class SelectedContainerViewHolder extends RecyclerView.ViewHolder {
    private SelectAdapter mAdapter;
    private TextView mTextView;

    public SelectedContainerViewHolder(@NonNull View itemView, OnToggleUpdateSelected onToggleUpdateSelected) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.container_title);
        RecyclerView recyclerView = itemView.findViewById(R.id.container_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(mAdapter = new SelectAdapter(onToggleUpdateSelected));
    }

    public void setFilterType(String filterType) {
        if (filterType != null) {
            mTextView.setText(Utilities.capitalize(filterType));
            mAdapter.setFilterType(filterType);
        }
    }

    public void setData(List<ToggledPair> selectionList) {
        mAdapter.setSelectionList(selectionList);
    }
}
