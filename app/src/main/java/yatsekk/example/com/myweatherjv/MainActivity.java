package yatsekk.example.com.myweatherjv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView cityTextView;
    EditText cityEditText;
    Button showWeatherButton;
    TextView currentTempTextView;
    TextView currentWeatherTextView;
    TextView comfortTempTextView;
    TextView humidityTextView;
    TextView pressureTextView;
    TextView windTextView;

    Weather weather = new Weather("Владивосток", 23,
            "Облачно, без осадков и солнечно с осадками", "Температура комфорта: 21°",
            "Влажность: 66%", "Давление: 760 мм.", "Ветер: Юго-восточный, 3 м/с");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        showWeather();
        showWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void showWeather() {
        cityTextView.setText(weather.getCityName());
        cityTextView.setVisibility(View.VISIBLE);
        cityEditText.setVisibility(View.GONE);
        showWeatherButton.setVisibility(View.GONE);
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
        showWeatherButton = findViewById(R.id.show_weather_button);
        currentTempTextView = findViewById(R.id.current_temp_textView);
        currentWeatherTextView = findViewById(R.id.current_weather_textView);
        comfortTempTextView = findViewById(R.id.comfort_temp_textView);
        humidityTextView = findViewById(R.id.humidity_textView);
        pressureTextView = findViewById(R.id.pressure_textView);
        windTextView = findViewById(R.id.wind_textView);
    }
}
