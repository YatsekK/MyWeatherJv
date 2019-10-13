package yatsekk.example.com.myweatherjv.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yatsekk.example.com.myweatherjv.R;
import yatsekk.example.com.myweatherjv.adapters.HourlyWeatherAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HourlyWeatherFragment extends Fragment {

    RecyclerView recyclerView;
    HourlyWeatherAdapter hourlyWeatherAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hourly_weather, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hourlyWeatherAdapter = new HourlyWeatherAdapter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView_hourly_weather);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(hourlyWeatherAdapter);
    }
}
