package ro.atelieruldigital.news.home.categories.viewholders;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ro.atelieruldigital.news.R;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    private TextView mSourceName, mTitle, mDescription, mAuthor, mPublishedAt;
    private CardView mCardView;
    private ImageView mImage;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mSourceName = itemView.findViewById(R.id.article_source_name);
        mTitle = itemView.findViewById(R.id.article_title);
        mDescription = itemView.findViewById(R.id.article_description);
        mAuthor = itemView.findViewById(R.id.article_author);
        mPublishedAt = itemView.findViewById(R.id.article_published_at);
        mImage = itemView.findViewById(R.id.article_image);
        mCardView = itemView.findViewById(R.id.article_card_view);
    }

    public void setUrl(String urlToArticle) {
        if (urlToArticle != null) {
            mCardView.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToArticle));
                v.getContext().startActivity(intent);
            });
        }
    }

    public void setImage(String urlToImage) {
        if (urlToImage != null && !urlToImage.equals("null")) {
            mImage.setVisibility(View.VISIBLE);

            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(mImage.getContext());
            circularProgressDrawable.setStrokeWidth(5.0f);
            circularProgressDrawable.setCenterRadius(30.0f);
            circularProgressDrawable.start();

            Glide
                    .with(mImage.getContext())
                    .load(urlToImage)
                    .placeholder(circularProgressDrawable)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(mImage);
        } else {
            mImage.setVisibility(View.GONE);
        }
    }

    public void setSourceName(String sourceName) {
        if (sourceName != null) {
            mSourceName.setText(sourceName);
        }
    }

    public void setTitle(String title) {
        if (title != null) {
            mTitle.setText(title);
        }
    }

    public void setDescription(String description) {
        if (description != null) {
            mDescription.setText(description);
        }
    }

    public void setAuthor(String author) {
        if (author != null) {
            mAuthor.setText(author);
        }
    }

    public void setPublishedAt(Date publishedAt) {
        if (publishedAt != null) {
            mPublishedAt.setText(SIMPLE_DATE_FORMAT.format(publishedAt));
        }
    }
}
