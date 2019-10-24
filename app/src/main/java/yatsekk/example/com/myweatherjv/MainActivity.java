package yatsekk.example.com.myweatherjv;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import yatsekk.example.com.myweatherjv.fragments.CurrentWeatherFragment;
import yatsekk.example.com.myweatherjv.fragments.DailyWeatherFragment;
import yatsekk.example.com.myweatherjv.fragments.HourlyWeatherFragment;

public class MainActivity
        extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                null,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//        drawerLayout.setNavigationItemSelectedListener(this);

        // loading the default fragment
        loadFragment(new CurrentWeatherFragment());

        // getting bottom bottom_navigation view and attaching the listener
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

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return loadFragment(fragment);
    }
}


