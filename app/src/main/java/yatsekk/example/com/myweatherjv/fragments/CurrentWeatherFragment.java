package yatsekk.example.com.myweatherjv.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import yatsekk.example.com.myweatherjv.R;
import yatsekk.example.com.myweatherjv.Weather;
import yatsekk.example.com.myweatherjv.utils.CityCache;
import yatsekk.example.com.myweatherjv.utils.QueryUtils;

public class CurrentWeatherFragment extends Fragment {

    private final String ID = "61eb58c265fe5d33c3595a0fea9cc7c4";
    private final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&APPID=" + ID + "&lang=ru&units=metric";

    private TextView cityTextView;
    private EditText cityEditText;
    private TextView currentTempTextView;
    private TextView currentWeatherTextView;
    private TextView comfortTempTextView;
    private TextView humidityTextView;
    private TextView pressureTextView;
    private TextView windTextView;

    private CityCache cityCache;

    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_weather, container, false);
        setViews(view);
        return view;
    }

    private void setViews(View view) {
        cityTextView = view.findViewById(R.id.city_name_textView);
        cityEditText = view.findViewById(R.id.city_name_editText);
        currentTempTextView = view.findViewById(R.id.current_temp_textView);
        currentWeatherTextView = view.findViewById(R.id.current_weather_textView);
        comfortTempTextView = view.findViewById(R.id.comfort_temp_textView);
        humidityTextView = view.findViewById(R.id.humidity_textView);
        pressureTextView = view.findViewById(R.id.pressure_textView);
        windTextView = view.findViewById(R.id.wind_textView);
    }

    private class WeatherAsyncTask extends AsyncTask<String, Void, Weather> {
        @Override
        protected Weather doInBackground(String... urls) {
            return QueryUtils.fetchWeatherData(urls[0]);
        }

        @Override
        protected void onPostExecute(Weather weather) {
            showWeather(weather);
        }
    }

    private void showWeather(Weather weather) {
        cityTextView.setText(weather.getCityName());
        currentTempTextView.setText(weather.getCurrentTemp() + "°");
        currentWeatherTextView.setText(String.format("Сейчас на улице: %s", weather.getCurrentWeather()));
        comfortTempTextView.setText(String.format("Минимальная температура: %s", weather.getComfortTemp()));
        humidityTextView.setText(String.format("Влажность: %s%%", weather.getHumidity()));
        pressureTextView.setText(String.format("Давление: %s", weather.getPressure()));
        windTextView.setText(String.format("Ветер: %s м/с", weather.getWind()));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        activity = getActivity();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        cityCache = new CityCache(getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.show_weather) {
            if (cityEditText.getText().length() != 0) {
                String chosenCity = cityEditText.getText().toString();
                cityCache.saveCity(chosenCity);
                showForecast(chosenCity);
                InputMethodManager imm = (InputMethodManager) activity
                        .getSystemService(activity.getApplicationContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(cityEditText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } else {
                Toast.makeText(getActivity(), R.string.city_from_cache, Toast.LENGTH_LONG).show();
                String chosenCity = cityCache.getSavedCity();
                showForecast(chosenCity);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showForecast(String chosenCity) {
        cityTextView.setVisibility(View.VISIBLE);
        cityEditText.setVisibility(View.GONE);
        String weatherUrl = String.format(WEATHER_BASE_URL, chosenCity);
        WeatherAsyncTask weatherAsyncTask = new WeatherAsyncTask();
        weatherAsyncTask.execute(weatherUrl);
    }
}
