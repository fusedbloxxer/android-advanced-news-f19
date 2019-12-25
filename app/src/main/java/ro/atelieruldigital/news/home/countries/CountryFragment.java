package ro.atelieruldigital.news.home.countries;

import ro.atelieruldigital.news.home.categories.adapters.SubAdapter;
import ro.atelieruldigital.news.home.categories.viewholders.ArticleViewHolder;
import ro.atelieruldigital.news.home.generic.GenericArticlesFragment;
import ro.atelieruldigital.news.model.NewsViewModel;
import ro.atelieruldigital.news.model.db.entities.Article;

public class CountryFragment extends GenericArticlesFragment {

    public CountryFragment() {
    }

    @Override
    protected void setObservers(NewsViewModel newsViewModel, SubAdapter<Article, ArticleViewHolder> articleAdapter, String value) {
        newsViewModel.getArticlesByCountries(value).observe(this, articleAdapter::setManyList);
    }
}