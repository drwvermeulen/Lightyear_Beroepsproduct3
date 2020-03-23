package com.example.lightyear_beroepsproduct3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        String strKlantnaam = databaseHelper.getKlantnaam();
        TextView tvWelkomKlant = findViewById(R.id.tvWelkomKlant);
        tvWelkomKlant.setText(String.format("Welkom %s",strKlantnaam));

//        GeconfigureerdeLightyear geoonfigureerdeLightyear = databaseHelper.getGeconfigureerdeLightyear();
//
//        //De waardes van de geconfigureerde lightyear worden in een string geplaatst
//        String strMdl = geoonfigureerdeLightyear.getMdl().toString();
//        String strKlr = geoonfigureerdeLightyear.getKlr().toString();
//        String strLk = geoonfigureerdeLightyear.getLk().toString();
//        String strVlg = geoonfigureerdeLightyear.getVlg().toString();
//
//        //De textview wordt geinitaliseerd
//        TextView tvProfileMdl = findViewById(R.id.tvProfileMdl);
//        TextView tvProfileKlr = findViewById(R.id.tvProfileKlr);
//        TextView tvProfileLk = findViewById(R.id.tvProfileLk);
//        TextView tvProfileVlg = findViewById(R.id.tvProfileVlg);
//
//        //De waardes in de string worden getoond op de textview
//        tvProfileMdl.setText(strMdl);
//        tvProfileKlr.setText(strKlr);
//        tvProfileLk.setText(strLk);
//        tvProfileVlg.setText(strVlg);
    }
}
