package ro.atelieruldigital.news.home.categories.adapters;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class SubAdapter<Many, ViewHolder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<ViewHolder> {
    protected List<Many> manyList;

    protected SubAdapter(List<Many> articleList) {
        this.manyList = articleList;
    }

    protected SubAdapter() {
        this(new ArrayList<>());
    }

    public void setManyList(List<Many> manyList) {
        this.manyList = manyList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return manyList.size();
    }
}
