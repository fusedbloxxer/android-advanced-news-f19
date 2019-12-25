package ro.atelieruldigital.news.home.filters.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.filters.utilities.OnToggleUpdateSelected;
import ro.atelieruldigital.news.home.filters.viewholders.SelectViewHolder;
import ro.atelieruldigital.news.home.filters.utilities.ToggledPair;

public class SelectAdapter extends RecyclerView.Adapter<SelectViewHolder> {
    private String filterType;
    private List<ToggledPair> selectionList;
    private OnToggleUpdateSelected onToggleUpdateSelected;

    public SelectAdapter(OnToggleUpdateSelected onToggleUpdateSelected) {
        this.onToggleUpdateSelected = onToggleUpdateSelected;
        this.selectionList = new ArrayList<>();
    }

    @NonNull
    @Override
    public SelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select, parent, false);
        return new SelectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectViewHolder holder, int position) {
        ToggledPair selection = selectionList.get(position);
        holder.setToggle(selection, (buttonView, isChecked) ->
                onToggleUpdateSelected.updateSelected(filterType, selection.getValue()));
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
        notifyDataSetChanged();
    }

    public void setSelectionList(List<ToggledPair> selectionList) {
        this.selectionList = selectionList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return selectionList.size();
    }
}
