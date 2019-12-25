package ro.atelieruldigital.news.home.filters.viewholders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ro.atelieruldigital.news.R;
import ro.atelieruldigital.news.home.filters.utilities.ToggledPair;

public class SelectViewHolder extends RecyclerView.ViewHolder {

    private CheckBox mCheckbox;

    public SelectViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mCheckbox = itemView.findViewById(R.id.filter_checkbox);
    }

    public void setToggle(ToggledPair selection, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        if (selection != null) {
            mCheckbox.setText(selection.getValue());
            mCheckbox.setOnCheckedChangeListener(null);
            mCheckbox.setChecked(selection.getOn());
            mCheckbox.setOnCheckedChangeListener(onCheckedChangeListener);
        }
    }
}
