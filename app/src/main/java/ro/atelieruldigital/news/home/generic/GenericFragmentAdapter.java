package ro.atelieruldigital.news.home.generic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import ro.atelieruldigital.news.model.db.entities.IUId;

public class GenericFragmentAdapter<T extends IUId<String>, DerivedFragment extends GenericArticlesFragment> extends FragmentStateAdapter {

    private List<T> list;
    private Class<DerivedFragment> derivedFragmentClass;

    public GenericFragmentAdapter(@NonNull Fragment fragment, Class<DerivedFragment> derivedFragmentClass) {
        super(fragment);
        this.list = new ArrayList<>();
        this.derivedFragmentClass = derivedFragmentClass;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        try {
            Fragment fragment = derivedFragmentClass.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString(GenericArticlesFragment.KEY, list.get(position).getId());
            fragment.setArguments(bundle);
            return fragment;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}