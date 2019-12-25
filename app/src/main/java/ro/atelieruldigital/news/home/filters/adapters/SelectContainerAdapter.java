package ro.atelieruldigital.news.home.filters.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.filters.utilities.OnToggleUpdateSelected;
import ro.atelieruldigital.news.home.filters.viewholders.SelectedContainerViewHolder;
import ro.atelieruldigital.news.home.filters.SelectedViewModel;
import ro.atelieruldigital.news.home.filters.utilities.ToggledPair;

public class SelectContainerAdapter extends RecyclerView.Adapter<SelectedContainerViewHolder> {

    private HashMap<String, HashMap<String, Boolean>> stringHashMapHashMap;
    private OnToggleUpdateSelected onToggleUpdateSelected;

    public SelectContainerAdapter(OnToggleUpdateSelected onToggleUpdateSelected) {
        this.onToggleUpdateSelected = onToggleUpdateSelected;
        this.stringHashMapHashMap = new HashMap<>();
    }

    @NonNull
    @Override
    public SelectedContainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container, parent, false);
        return new SelectedContainerViewHolder(itemView, onToggleUpdateSelected);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedContainerViewHolder holder, int position) {
        String key = getKey(position);

        List<ToggledPair> selectionList = new ArrayList<>();
        for (HashMap.Entry<String, Boolean> entry : stringHashMapHashMap.get(key).entrySet()) {
            selectionList.add(new ToggledPair(entry.getKey(), entry.getValue()));
        }

        holder.setData(selectionList);
        holder.setFilterType(key);
    }

    private String getKey(int position) {
        switch (position) {
            case 0: {
                return SelectedViewModel.CATEGORIES;
            }
            case 1: {
                return SelectedViewModel.SOURCES;
            }
            case 2: {
                return SelectedViewModel.LANGUAGES;
            }
            case 3: {
                return SelectedViewModel.COUNTRIES;
            }
            default:
                throw new RuntimeException();
        }
    }

    public void setStringDoubleHash(HashMap<String, HashMap<String, Boolean>> stringHashMapHashMap) {
        this.stringHashMapHashMap = stringHashMapHashMap;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return stringHashMapHashMap.size();
    }
}
