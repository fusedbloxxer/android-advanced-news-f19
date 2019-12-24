package ro.atelieruldigital.news.model.db.entities;

import java.util.List;

public interface IUId<T> {
    T getId();

    static <T extends IUId<String>> String[] params(int size, List<T> uniqueId) {
        String[] params = new String[size];

        for (int i = 0; i < size; i++) {
            params[i] = uniqueId.get(i).getId();
        }

        return params;
    }
}
