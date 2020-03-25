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
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private ArrayList<String> cnfgrtnmmr, mdl, klr, lk, vlg;
    private ProfileAdapter profileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        recyclerView = findViewById(R.id.recyclerView);

        //Een nieuwe databasehelper wordt geinstancieerd
        databaseHelper = new DatabaseHelper(ProfileActivity.this);

        cnfgrtnmmr = new ArrayList<>();
        mdl = new ArrayList<>();
        klr = new ArrayList<>();
        lk = new ArrayList<>();
        vlg = new ArrayList<>();

        storeGeconfigureerdeLightyearInArray();

        profileAdapter = new ProfileAdapter(ProfileActivity.this, cnfgrtnmmr, mdl, klr, lk, vlg);
        recyclerView.setAdapter(profileAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));

        //Waarde van de methode getKLantnaam wordt in een string gezet
        String strKlantnaam = databaseHelper.getKlantnaam();
        //TextView wordt geinstancieerd
        TextView tvWelkomKlant = findViewById(R.id.tvWelkomKlant);
        //De waarde van de string wordt getoond op de textview
        tvWelkomKlant.setText(String.format("Welkom %s, bekijk hieronder uw bestelling(en):",strKlantnaam));
//
//        GeconfigureerdeLightyear geconfigureerdeLightyear = databaseHelper.getGeconfigureerdeLightyear();
//
//        //De waardes van de geconfigureerde lightyear worden in een string geplaatst
//        String strMdl = geconfigureerdeLightyear.getMdl().toString();
//        String strKlr = geconfigureerdeLightyear.getKlr().toString();
//        String strLk = geconfigureerdeLightyear.getLk().toString();
//        String strVlg = geconfigureerdeLightyear.getVlg().toString();
//
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

    void storeGeconfigureerdeLightyearInArray() {
        Cursor cursor = databaseHelper.getCursorGeconfigureerdeLightyear();
        if(cursor.getCount() == 0) {
            Message.message(getApplicationContext(), "Je hebt nog geen Lightyear besteld!");
        } else {
            while (cursor.moveToNext()) {
                cnfgrtnmmr.add(cursor.getString(0));
                mdl.add(cursor.getString(1));
                klr.add(cursor.getString(2));
                lk.add(cursor.getString(3));
                vlg.add(cursor.getString(4));
            }
        }
    }
}
