package ro.atelieruldigital.news.home.sources;


import androidx.fragment.app.Fragment;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.categories.adapters.ArticleAdapter;
import ro.atelieruldigital.news.home.categories.viewholders.ArticleViewHolder;
import ro.atelieruldigital.news.home.generic.GenericArticlesFragment;
import ro.atelieruldigital.news.home.generic.adapters.SubAdapter;
import ro.atelieruldigital.news.model.NewsViewModel;
import ro.atelieruldigital.news.model.db.entities.Article;


/**
 * A simple {@link Fragment} subclass.
 */
public class SourceArticlesFragment extends GenericArticlesFragment<Article, ArticleViewHolder> {

    public SourceArticlesFragment() {
        super(R.layout.fragment_with_list, new ArticleAdapter(R.layout.item_article_wide));
    }

    @Override
    protected void setObservers(NewsViewModel newsViewModel, SubAdapter<Article, ArticleViewHolder> articleAdapter, String value) {
        newsViewModel.getArticlesBySources(value).observe(this, articles -> {
            articleAdapter.setManyList(articles);
            setProgress(articles.size() != 0);
        });
    }
}