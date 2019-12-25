package ro.atelieruldigital.news.home.languages;

import ro.atelieruldigital.news.home.categories.adapters.SubAdapter;
import ro.atelieruldigital.news.home.categories.viewholders.ArticleViewHolder;
import ro.atelieruldigital.news.home.generic.GenericArticlesFragment;
import ro.atelieruldigital.news.model.NewsViewModel;
import ro.atelieruldigital.news.model.db.entities.Article;

public class LanguageFragment extends GenericArticlesFragment {

    public LanguageFragment() {
    }

    @Override
    protected void setObservers(NewsViewModel newsViewModel, SubAdapter<Article, ArticleViewHolder> articleAdapter, String value) {
        newsViewModel.getArticlesByLanguages(value).observe(this, articleAdapter::setManyList);
    }
}
