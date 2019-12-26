package ro.atelieruldigital.news.home.generic.adapters;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class SubAdapter<Many, ViewHolder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<ViewHolder> {
    protected List<Many> manyList;
    protected int layoutResourceId;

    protected SubAdapter(int layoutResourceId, List<Many> articleList) {
        this.manyList = articleList;
        this.layoutResourceId = layoutResourceId;
    }

    protected SubAdapter(int layoutResourceId) {
        this(layoutResourceId, new ArrayList<>());
    }

    public void setManyList(List<Many> manyList) {
        this.manyList = manyList;
        notifyDataSetChanged();
    }

    public List<Many> getManyList() {
        return this.manyList;
    }

    @Override
    public int getItemCount() {
        return manyList.size();
    }
}
