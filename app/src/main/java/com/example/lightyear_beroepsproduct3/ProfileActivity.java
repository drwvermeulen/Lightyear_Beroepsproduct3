package com.example.lightyear_beroepsproduct3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ArrayList<GeconfigureerdeLightyear> geconfigureerdeLightyearList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        databaseHelper = new DatabaseHelper(ProfileActivity.this);

        geconfigureerdeLightyearList = new ArrayList<>();

        geconfigureerdeLightyearList = databaseHelper.getGeconfigureerdeLightyearList();

        ProfileAdapter profileAdapter = new ProfileAdapter(ProfileActivity.this, geconfigureerdeLightyearList);
        recyclerView.setAdapter(profileAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));

        //Waarde van de methode getKLantnaam wordt in een string gezet
        String strKlantnaam = databaseHelper.getKlantnaam();
        //TextView wordt geinstancieerd
        TextView tvWelkomKlant = findViewById(R.id.tvWelkomKlant);
        //De waarde van de string wordt getoond op de textview
        tvWelkomKlant.setText(String.format("Welkom %s",strKlantnaam));

        //Initialiseert en wijst variabele toe
        BottomNavigationView bnvBottomNavigation = findViewById(R.id.bnvBottomNavigation);

        //Zet home op geselecteerd
        bnvBottomNavigation.setSelectedItemId(R.id.profile);

        //Perform ItemSelectedListener
        bnvBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.configurator:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });
    }
}
