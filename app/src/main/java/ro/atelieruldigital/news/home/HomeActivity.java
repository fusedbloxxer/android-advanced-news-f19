package ro.atelieruldigital.news.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.core.BaseActivity;
import ro.atelieruldigital.news.model.Article;
import ro.atelieruldigital.news.model.NewsViewModel;

public class HomeActivity extends BaseActivity {

    private NewsViewModel newsViewModel;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.queryBitcoinArticles()
                .observe(this, articles -> textView.setText(articles.toString()));
    }

    private void initView() {
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
    }

    public void fetchNewsOnClick(View view) {
        if (newsViewModel.queryBitcoinArticles().getValue() != null) {
            textView.setText(newsViewModel.queryBitcoinArticles().getValue().toString());
        }
    }
}
