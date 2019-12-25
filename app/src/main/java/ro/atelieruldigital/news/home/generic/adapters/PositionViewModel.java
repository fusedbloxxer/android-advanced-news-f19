package ro.atelieruldigital.news.home.generic.adapters;

import androidx.lifecycle.ViewModel;

public class PositionViewModel extends ViewModel {
    private Integer position;

    public PositionViewModel() {
        this.position = 0;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
