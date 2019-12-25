package ro.atelieruldigital.news.home.languages;


import androidx.fragment.app.Fragment;

import ro.atelieruldigital.news.home.generic.GenericTabsFragment;
import ro.atelieruldigital.news.model.NewsViewModel;
import ro.atelieruldigital.news.model.db.entities.Language;

/**
 * A simple {@link Fragment} subclass.
 */
public class LanguagesFragment extends GenericTabsFragment<Language, LanguageFragment> {

    public LanguagesFragment() {
        super(LanguageFragment.class);
    }

    @Override
    protected void setObservers(NewsViewModel newsViewModel) {
        newsViewModel.getAllLanguages()
                .observe(this, languages -> mGenericFragmentAdapter.setList(languages));
    }
}