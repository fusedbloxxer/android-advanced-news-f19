package ro.atelieruldigital.news.home.generic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoadingViewModel extends ViewModel {
    private MutableLiveData<Boolean> isLoaded;

    public LoadingViewModel() {
        isLoaded = new MutableLiveData<>(false);
    }

    public LiveData<Boolean> isLoaded() {
        return isLoaded;
    }

    public void setLoaded(Boolean loaded) {
        isLoaded.setValue(loaded);
    }
}
