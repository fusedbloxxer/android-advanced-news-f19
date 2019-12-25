package ro.atelieruldigital.news.home.countries;


import androidx.fragment.app.Fragment;

import ro.atelieruldigital.news.home.generic.GenericTabsFragment;
import ro.atelieruldigital.news.model.NewsViewModel;
import ro.atelieruldigital.news.model.db.entities.Country;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountriesFragment extends GenericTabsFragment<Country, CountryFragment> {

    public CountriesFragment() {
        super(CountryFragment.class);
    }

    @Override
    protected void setObservers(NewsViewModel newsViewModel) {
        newsViewModel.getAllCountries()
                .observe(this, countries -> {
                    mGenericFragmentAdapter.setList(countries);
                    setCurrentTab();
                });
    }
}
