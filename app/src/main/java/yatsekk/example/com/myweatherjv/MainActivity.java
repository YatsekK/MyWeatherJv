package yatsekk.example.com.myweatherjv;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import yatsekk.example.com.myweatherjv.fragments.CurrentWeatherFragment;
import yatsekk.example.com.myweatherjv.fragments.DailyWeatherFragment;
import yatsekk.example.com.myweatherjv.fragments.HourlyWeatherFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // loading the default fragment
        loadFragment(new CurrentWeatherFragment());

        // getting bottom navigation view and attaching the listener
        BottomNavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment fragment = new CurrentWeatherFragment();
//        fragmentManager.beginTransaction().add(R.id.fragment, fragment).commit();
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_current_weather:
                fragment = new CurrentWeatherFragment();
                break;

            case R.id.navigation_24hour_forecast:
                fragment = new HourlyWeatherFragment();
                break;

            case R.id.navigation_week_forecast:
                fragment = new DailyWeatherFragment();
                break;
        }
        return loadFragment(fragment);
    }
}


