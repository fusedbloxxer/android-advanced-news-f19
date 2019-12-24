package ro.atelieruldigital.news.home.categories.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.atelieruldigital.news.home.categories.adapters.SubAdapter;

public abstract class OneToManyViewHolder<One extends String, Many, ViewHolder extends RecyclerView.ViewHolder> extends RecyclerView.ViewHolder {

    private SubAdapter<Many, ViewHolder> mSubAdapter;

    OneToManyViewHolder(@NonNull View itemView, int recyclerViewId, SubAdapter<Many, ViewHolder> subAdapter) {
        super(itemView);
        mSubAdapter = subAdapter;
        RecyclerView mRecyclerView = itemView.findViewById(recyclerViewId);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(subAdapter);
    }

    public void setMany(List<Many> many) {
        mSubAdapter.setManyList(many);
    }

    public abstract void setOne(One one);
}
