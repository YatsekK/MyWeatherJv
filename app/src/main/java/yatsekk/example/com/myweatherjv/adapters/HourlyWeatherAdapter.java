package yatsekk.example.com.myweatherjv.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import yatsekk.example.com.myweatherjv.R;
import yatsekk.example.com.myweatherjv.Weather;

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherViewHolder> {

    private List<Weather> weatherList = new ArrayList();

    public HourlyWeatherAdapter() {
        createWeatherListData();
    }

    private void createWeatherListData() {
        weatherList.add(new Weather("22.00", 9, "Облачно", "Ветер: 5 м/с"));
        weatherList.add(new Weather("23.00", 8, "Облачно", "Ветер: 5 м/с"));
        weatherList.add(new Weather("00.00", 8, "Облачно", "Ветер: 4 м/с"));
        weatherList.add(new Weather("01.00", 7, "Облачно", "Ветер: 4 м/с"));
        weatherList.add(new Weather("02.00", 6, "Облачно", "Ветер: 3 м/с"));
        weatherList.add(new Weather("03.00", 6, "Облачно", "Ветер: 3 м/с"));
        weatherList.add(new Weather("04.00", 6, "Облачно", "Ветер: 2 м/с"));
        weatherList.add(new Weather("05.00", 5, "Облачно", "Ветер: 0 м/с"));
        weatherList.add(new Weather("06.00", 5, "Облачно", "Ветер: 0 м/с"));
        weatherList.add(new Weather("07.00", 5, "Облачно", "Ветер: 1 м/с"));
        weatherList.add(new Weather("08.00", 5, "Облачно", "Ветер: 1 м/с"));
        weatherList.add(new Weather("09.00", 6, "Облачно", "Ветер: 2 м/с"));
        weatherList.add(new Weather("10.00", 5, "Облачно и кратковременные осадки", "Ветер: 2 м/с"));
        weatherList.add(new Weather("11.00", 5, "Облачно и кратковременные осадки", "Ветер: 3 м/с"));
        weatherList.add(new Weather("12.00", 5, "Облачно и кратковременные осадки", "Ветер: 3 м/с"));
        weatherList.add(new Weather("13.00", 5, "Облачно и кратковременные осадки", "Ветер: 4 м/с"));
        weatherList.add(new Weather("14.00", 5, "Облачно и кратковременные осадки", "Ветер: 4 м/с"));
        weatherList.add(new Weather("15.00", 5, "Облачно и кратковременные осадки", "Ветер: 5 м/с"));
        weatherList.add(new Weather("16.00", 5, "Частично облачно и слабый дождь", "Ветер: 6 м/с"));
    }

    @NonNull
    @Override

    public HourlyWeatherAdapter.HourlyWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_weather_list_item, parent, false);
        return new HourlyWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyWeatherAdapter.HourlyWeatherViewHolder holder, int position) {
        Weather weather = weatherList.get(position);
        holder.applyWeatherData(weather);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    static class HourlyWeatherViewHolder extends RecyclerView.ViewHolder {

        private final TextView hourTextView;
        private final TextView hourlyTempTextView;
        private final TextView hourlyConditionTextView;
        private final TextView hourlyWindTextView;

        private HourlyWeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            hourTextView = itemView.findViewById(R.id.hourly_hour_textView);
            hourlyTempTextView = itemView.findViewById(R.id.hourly_temp_textView);
            hourlyConditionTextView = itemView.findViewById(R.id.hourly_weather_condition_textView);
            hourlyWindTextView = itemView.findViewById(R.id.hourly_weather_wind_TextView);
        }

        void applyWeatherData(Weather weather) {
            hourTextView.setText(weather.getTimeHour());
            hourlyTempTextView.setText(weather.getCurrentTemp() + "°");
            hourlyConditionTextView.setText(weather.getCurrentWeather());
            hourlyWindTextView.setText(weather.getWind());
        }
    }
}
