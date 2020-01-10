package com.harilee.employeeapp.Employee;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.harilee.employeeapp.Employee.ui.HR.HRActivity;
import com.harilee.employeeapp.Employee.ui.Settings.SettingsFragment;
import com.harilee.employeeapp.Employee.ui.Vacation.RequestFragment;
import com.harilee.employeeapp.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.request_vacation, R.id.hr_activity, R.id.settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);*/
        navView.setOnNavigationItemSelectedListener(this);
        loadFragmentStart(new RequestFragment());


    }

    public boolean loadFragmentStart(RequestFragment requestFragment) {
        if (requestFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, requestFragment)
                    .commit();
            return true;
        }
        return false;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.request_vacation:
                fragment = new RequestFragment();
                break;

            case R.id.hr_activity:
                fragment = new HRActivity();
                break;

            case R.id.settings:
                fragment = new SettingsFragment();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

}
