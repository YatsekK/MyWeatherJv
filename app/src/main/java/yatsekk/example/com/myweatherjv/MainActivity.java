package yatsekk.example.com.myweatherjv;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import yatsekk.example.com.myweatherjv.fragments.CurrentWeatherFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //       setViews();
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new CurrentWeatherFragment();
        fragmentManager.beginTransaction().add(R.id.fragment, fragment).commit();
    }
}


