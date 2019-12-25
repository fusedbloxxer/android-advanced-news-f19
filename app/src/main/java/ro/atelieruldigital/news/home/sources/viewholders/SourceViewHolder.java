package ro.atelieruldigital.news.home.sources.viewholders;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.sources.SourcesFragmentDirections;
import ro.atelieruldigital.news.utils.Utilities;

public class SourceViewHolder extends RecyclerView.ViewHolder {
    private TextView mTitle, mCategory, mDescription, mLanguage, mCountry, mFindMore;
    private CardView mCardView;

    public SourceViewHolder(@NonNull View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View itemView) {
        mTitle = itemView.findViewById(R.id.source_title_text_view);
        mCategory = itemView.findViewById(R.id.source_category_text_view);
        mDescription = itemView.findViewById(R.id.source_description_text_view);
        mLanguage = itemView.findViewById(R.id.source_language_text_view);
        mCountry = itemView.findViewById(R.id.source_country_text_view);
        mFindMore = itemView.findViewById(R.id.source_url_find_more_text_view);
        mCardView = itemView.findViewById(R.id.source_card_view);
    }

    public void setTitle(String title) {
        if (title != null) {
            mTitle.setText(Utilities.capitalize(title));
        }
    }

    public void setCategory(String category) {
        if (category != null) {
            mCategory.setText(" - " + Utilities.capitalize(category) + " - ");
        }
    }

    public void setDescription(String description) {
        if (description != null) {
            mDescription.setText(description);
        }
    }

    public void setLanguage(String language) {
        if (language != null) {
            mLanguage.setText("Language: " + Utilities.capitalize(language));
        }
    }

    public void setCountry(String country) {
        if (country != null) {
            mCountry.setText("Country: " + Utilities.capitalize(country));
        }
    }

    public void setFindMore(String findMore) {
        if (findMore != null) {
            mFindMore.setOnClickListener(v -> {
                Intent openSourceUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(findMore));
                v.getContext().startActivity(openSourceUrl);
            });
        }
    }

    public void setSourceId(String sourceId) {
        if (sourceId != null) {
            mCardView.setOnClickListener(v -> Navigation.findNavController(v)
                    .navigate(SourcesFragmentDirections.actionSourcesToSourceArticlesFragment(sourceId)));
        }
    }
}
