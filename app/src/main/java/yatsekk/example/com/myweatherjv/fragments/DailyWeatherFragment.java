package yatsekk.example.com.myweatherjv.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yatsekk.example.com.myweatherjv.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyWeatherFragment extends Fragment {


    public DailyWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_weather, container, false);
    }

}
