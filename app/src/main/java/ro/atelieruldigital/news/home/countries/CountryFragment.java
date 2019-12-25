package ro.atelieruldigital.news.home.countries;

import java.util.Collections;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.categories.adapters.ArticleAdapter;
import ro.atelieruldigital.news.home.categories.viewholders.ArticleViewHolder;
import ro.atelieruldigital.news.home.generic.GenericArticlesFragment;
import ro.atelieruldigital.news.home.generic.adapters.SubAdapter;
import ro.atelieruldigital.news.model.NewsViewModel;
import ro.atelieruldigital.news.model.db.entities.Article;
import ro.atelieruldigital.news.model.db.entities.IUId;
import ro.atelieruldigital.news.model.ws.NewsWebService;

public class CountryFragment extends GenericArticlesFragment<Article, ArticleViewHolder> {

    public CountryFragment() {
        super(R.layout.fragment_with_list, new ArticleAdapter(R.layout.item_article_wide));
    }

    @Override
    protected void setObservers(NewsViewModel newsViewModel, SubAdapter<Article, ArticleViewHolder> articleAdapter, String value) {
        newsViewModel.getSourcesByCountries(value).observe(this, sources -> {
            Collections.shuffle(sources); // Get the first 20 or sources.size() sources randomly.
            newsViewModel.getArticlesBySources(IUId.params(Math.min(NewsWebService.MAX_SOURCES, sources.size()), sources))
                    .observe(this, articles -> {
                        articleAdapter.setManyList(articles);
                        setProgress(articles.size() != 0);
                    });
        });
    }
}