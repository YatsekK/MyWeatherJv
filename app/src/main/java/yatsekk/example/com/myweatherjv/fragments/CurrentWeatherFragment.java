package yatsekk.example.com.myweatherjv.fragments;

import android.content.Context;
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

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import yatsekk.example.com.myweatherjv.MainActivity;
import yatsekk.example.com.myweatherjv.R;
import yatsekk.example.com.myweatherjv.utils.ApiFactory;
import yatsekk.example.com.myweatherjv.utils.ApiService;
import yatsekk.example.com.myweatherjv.utils.CityCache;
import yatsekk.example.com.myweatherjv.utils.pojo.MainModel;

public class CurrentWeatherFragment extends Fragment {

    private final String ID = "61eb58c265fe5d33c3595a0fea9cc7c4";
    private final String UNITS = "metric";
    private final String LANG = "ru";

    private TextView cityTextView;
    private EditText cityEditText;
    private TextView currentTempTextView;
    private TextView currentWeatherTextView;
    private TextView comfortTempTextView;
    private TextView humidityTextView;
    private TextView pressureTextView;
    private TextView windTextView;

    private CityCache cityCache;

    private ApiFactory apiFactory = ApiFactory.getInstance();
    private ApiService apiService = apiFactory.getApiService();

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

    private void showWeather(MainModel mainModel) {
        cityTextView.setText(mainModel.getName());
        currentTempTextView.setText(String.format("%s°C", mainModel.getMain().getTemp()));
        currentWeatherTextView.setText(String.format("Сейчас на улице: %s",
                mainModel.getWeatherResponses().get(0).getDescription()));
        comfortTempTextView.setText(String.format("Минимальная температура: %s",
                mainModel.getMain().getTempMin()));
        humidityTextView.setText(String.format("Влажность: %s%%",
                mainModel.getMain().getHumidity()));
        pressureTextView.setText(String.format("Давление: %s",
                mainModel.getMain().getPressure()));
        windTextView.setText(String.format("Ветер: %s м/с", mainModel.getWind().getSpeed()));
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
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity()!= null) {
            cityCache = new CityCache(getActivity());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.show_weather) {
            if (cityEditText.getText().length() != 0) {
                String chosenCity = cityEditText.getText().toString();
                cityCache.saveCity(chosenCity);
                showForecast(chosenCity);
                InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity())
                        .getSystemService(MainActivity.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(cityEditText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
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
        apiService.getResponse(chosenCity, ID, LANG, UNITS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MainModel>() {
                    @Override
                    public void accept(MainModel mainModel) throws Exception {
                        showWeather(mainModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), R.string.error_loading_data, Toast.LENGTH_LONG).show();
                    }
                });
    }
}
