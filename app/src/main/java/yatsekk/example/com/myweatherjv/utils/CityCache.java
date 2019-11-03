package yatsekk.example.com.myweatherjv.utils;

import android.app.Activity;
import android.content.SharedPreferences;

public final class CityCache {

    private static final String CITY_KEY = "city";

    private final SharedPreferences cityPreferences;

    public CityCache(Activity activity) {
        cityPreferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public void saveCity(String city) {
        cityPreferences.edit().putString(CITY_KEY, city).apply();
    }

    public String getSavedCity() {
        return cityPreferences.getString(CITY_KEY, "Vladivostok");
    }
}
