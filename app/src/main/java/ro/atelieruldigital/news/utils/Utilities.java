package ro.atelieruldigital.news.utils;

import org.jetbrains.annotations.NotNull;

public class Utilities {

    @NotNull
    public static String capitalize(String e) {
        return e.substring(0, 1).toUpperCase() + e.substring(1);
    }
}
