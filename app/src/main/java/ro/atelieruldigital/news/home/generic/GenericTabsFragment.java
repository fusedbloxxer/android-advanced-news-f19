package ro.atelieruldigital.news.home.generic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.generic.adapters.GenericFragmentAdapter;
import ro.atelieruldigital.news.home.generic.adapters.PositionViewModel;
import ro.atelieruldigital.news.model.NewsViewModel;
import ro.atelieruldigital.news.model.db.entities.IUId;

public abstract class GenericTabsFragment<TabType extends IUId<String>, InnerFragment extends GenericArticlesFragment> extends Fragment {

    protected GenericFragmentAdapter<TabType, InnerFragment> mGenericFragmentAdapter;
    private Class<InnerFragment> mInnerFragmentClass;
    private PositionViewModel mPositionViewModel;
    private ViewPager2 mViewPager2;

    protected GenericTabsFragment(Class<InnerFragment> innerFragmentClass) {
        this.mInnerFragmentClass = innerFragmentClass;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout_articles);
        mViewPager2 = view.findViewById(R.id.view_pager_2_articles);
        mPositionViewModel = ViewModelProviders.of(this).get(PositionViewModel.class);
        mViewPager2.setAdapter(mGenericFragmentAdapter = new GenericFragmentAdapter<>(this, mInnerFragmentClass));

        new TabLayoutMediator(tabLayout, mViewPager2,
                ((tab, position) -> tab.setText(mGenericFragmentAdapter.getList().get(position).getId()))
        ).attach();

        setObservers(ViewModelProviders.of(this).get(NewsViewModel.class));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mPositionViewModel.setPosition(mViewPager2.getCurrentItem());
    }

    protected abstract void setObservers(NewsViewModel newsViewModel);

    protected void setCurrentTab() {
        mViewPager2.setCurrentItem(mPositionViewModel.getPosition());
    }
}
