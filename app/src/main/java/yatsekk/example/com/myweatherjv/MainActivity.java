package yatsekk.example.com.myweatherjv;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private final String ID = "61eb58c265fe5d33c3595a0fea9cc7c4";
    private final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&APPID=" + ID + "&lang=ru&units=metric";

    TextView cityTextView;
    EditText cityEditText;
    TextView currentTempTextView;
    TextView currentWeatherTextView;
    TextView comfortTempTextView;
    TextView humidityTextView;
    TextView pressureTextView;
    TextView windTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
    }

    private class WeatherAsyncTask extends AsyncTask<String, Void, Weather> {
        @Override
        protected Weather doInBackground(String... urls) {
            Weather weather = QueryUtils.fetchWeatherData(urls[0]);
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            showWeather(weather);
        }
    }

    private void showWeather(Weather weather) {
        currentTempTextView.setText(Integer.toString(weather.getCurrentTemp()) + "°");
        currentWeatherTextView.setText(weather.getCurrentWeather());
        comfortTempTextView.setText(weather.getComfortTemp());
        humidityTextView.setText(weather.getHumidity());
        pressureTextView.setText(weather.getPressure());
        windTextView.setText(weather.getWind());
    }

    private void setViews() {
        cityTextView = findViewById(R.id.city_name_textView);
        cityEditText = findViewById(R.id.city_name_editText);
        currentTempTextView = findViewById(R.id.current_temp_textView);
        currentWeatherTextView = findViewById(R.id.current_weather_textView);
        comfortTempTextView = findViewById(R.id.comfort_temp_textView);
        humidityTextView = findViewById(R.id.humidity_textView);
        pressureTextView = findViewById(R.id.pressure_textView);
        windTextView = findViewById(R.id.wind_textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.show_weather) {
            String chosenCity = cityEditText.getText().toString();
            cityTextView.setVisibility(View.VISIBLE);
            cityEditText.setVisibility(View.GONE);
            String weatherUrl = String.format(WEATHER_BASE_URL, chosenCity);
            WeatherAsyncTask weatherAsyncTask = new WeatherAsyncTask();
            weatherAsyncTask.execute(weatherUrl);
        }
        return super.onOptionsItemSelected(item);
    }
}
