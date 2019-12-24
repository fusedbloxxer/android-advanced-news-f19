package ro.atelieruldigital.news.home.categories.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.atelieruldigital.news.home.categories.viewholders.ArticleViewHolder;
import ro.atelieruldigital.news.home.categories.viewholders.OneToManyViewHolder;
import ro.atelieruldigital.news.model.db.containers.OneToMany;

public abstract class ContainerAdapter<One extends String, Many, ViewHolder extends OneToManyViewHolder<One, Many, ArticleViewHolder>> extends RecyclerView.Adapter<ViewHolder> {
    private List<OneToMany<One, Many>> oneToManyList;

    private ContainerAdapter(List<OneToMany<One, Many>> categoryWithArticles) {
        this.oneToManyList = categoryWithArticles;
    }

    ContainerAdapter() {
        this(new ArrayList<>());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OneToMany<One, Many> oneToMany = oneToManyList.get(position);
        holder.setMany(oneToMany.getMany());
        holder.setOne(oneToMany.getOne());
    }

    public void setOneToManyList(List<OneToMany<One, Many>> oneToManyList) {
        this.oneToManyList = oneToManyList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return oneToManyList.size();
    }

}
