package com.example.lightyear_beroepsproduct3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Button btnConfigureren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConfigureren = findViewById(R.id.btnConfigureren);
        btnConfigureren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ConfiguratorActivity.class);
                startActivity(i);
            }
        });

        //Initialiseert en wijst variabele toe
        BottomNavigationView bnvBottomNavigation = findViewById(R.id.bnvBottomNavigation);

        //Zet home op geselecteerd
        bnvBottomNavigation.setSelectedItemId(R.id.home);

        //Perform ItemSelectedListener
        bnvBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.configurator:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}
