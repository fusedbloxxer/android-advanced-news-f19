package ro.atelieruldigital.news.home.filters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.HashMap;

public class SelectedViewModel extends ViewModel {
    public static final String CATEGORIES = "categories";
    public static final String SOURCES = "sources";
    public static final String LANGUAGES = "languages";
    public static final String COUNTRIES = "countries";
    private MutableLiveData<HashMap<String, HashMap<String, Boolean>>> allHashMap;

    public SelectedViewModel() {
        allHashMap = new MutableLiveData<>(new HashMap<>());
        allHashMap.getValue().put(CATEGORIES, new HashMap<>());
        allHashMap.getValue().put(LANGUAGES, new HashMap<>());
        allHashMap.getValue().put(COUNTRIES, new HashMap<>());
        allHashMap.getValue().put(SOURCES, new HashMap<>());
    }

    public void addOptions(String filterType, String selection) {
        if (!allHashMap.getValue().get(filterType).containsKey(selection)) {
            allHashMap.getValue().get(filterType).put(selection, false);
            allHashMap.setValue(allHashMap.getValue());
        }
    }

    public LiveData<HashMap<String, HashMap<String, Boolean>>> getAllHashMap() {
        return allHashMap;
    }

    public void toggleFilter(String filterType, String selection) {
        allHashMap.getValue().get(filterType).put(selection, !allHashMap.getValue().get(filterType).get(selection));
    }

    public String[] filters(String filterType) {
        int index = 0;
        String[] selectedArray = new String[allHashMap.getValue().get(filterType).size()];
        for (HashMap.Entry<String, Boolean> entry : allHashMap.getValue().get(filterType).entrySet()) {
            if (entry.getValue()) {
                selectedArray[index++] = entry.getKey();
            }
        }
        return Arrays.copyOf(selectedArray, index);
    }
}
