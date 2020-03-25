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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Een nieuwe databasehelper wordt geinstancieerd
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        //Waarde van de methode getKLantnaam wordt in een string gezet
        String strKlantnaam = databaseHelper.getKlantnaam();
        //TextView wordt geinstancieerd
        TextView tvWelkomKlant = findViewById(R.id.tvWelkomKlant);
        //De waarde van de string wordt getoond op de textview
        tvWelkomKlant.setText(String.format("Welkom %s",strKlantnaam));

<<<<<<< HEAD:app/src/main/java/com/example/lightyear_beroepsproduct3/ProfileActivity.java
        GeconfigureerdeLightyear geconfigureerdeLightyear = databaseHelper.getGeconfigureerdeLightyear();

        //De waardes van de geconfigureerde lightyear worden in een string geplaatst
        String strMdl = geconfigureerdeLightyear.getMdl().toString();
        String strKlr = geconfigureerdeLightyear.getKlr().toString();
        String strLk = geconfigureerdeLightyear.getLk().toString();
        String strVlg = geconfigureerdeLightyear.getVlg().toString();

        //De textview wordt geinitaliseerd
        TextView tvProfileMdl = findViewById(R.id.tvProfileMdl);
        TextView tvProfileKlr = findViewById(R.id.tvProfileKlr);
        TextView tvProfileLk = findViewById(R.id.tvProfileLk);
        TextView tvProfileVlg = findViewById(R.id.tvProfileVlg);

        //De waardes in de string worden getoond op de textview
        tvProfileMdl.setText(strMdl);
        tvProfileKlr.setText(strKlr);
        tvProfileLk.setText(strLk);
        tvProfileVlg.setText(strVlg);

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
=======
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
>>>>>>> parent of 4cb19c7... Enums aangepast, zodat de waardes getoond kunnen worden in profile:app/src/main/java/com/example/lightyear_beroepsproduct3/Profile.java
    }
}
