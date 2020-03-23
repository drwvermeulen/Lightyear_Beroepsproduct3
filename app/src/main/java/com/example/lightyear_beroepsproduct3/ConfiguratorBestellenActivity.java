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

public class ConfiguratorBestellenActivity extends AppCompatActivity {
    private Button btnBestellen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator_bestellen);

        //Terugknop
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialiseert en wijst variabele toe
        BottomNavigationView bnvTopNavigation = findViewById(R.id.bnvTopNavigation);

        //Zet home op geselecteerd
        bnvTopNavigation.setSelectedItemId(R.id.configuratorBestellen);

        //Perform ItemSelectedListener
        bnvTopNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.configuratorKleur:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorKleurActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.configuratorLak:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorLakActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.configuratorVelg:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorVelgActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.configuratorBestellen:
                        return true;
                }
                return false;
            }
        });

        GeconfigureerdeLightyear lightyear = (GeconfigureerdeLightyear) getIntent().getSerializableExtra(ConfiguratorActivity.CONFIGURERENMODEL);
        String strMdl = lightyear.getMdl().toString();
        String strKlr = lightyear.getKlr().toString();
        String strLk = lightyear.getLk().toString();
        String strVlg = lightyear.getVlg().toString();

        TextView tvGeconfigureerdeMdl = findViewById(R.id.tvGeconfigureerdeMdl);
        TextView tvGeconfigureerdeKlr = findViewById(R.id.tvGeconfigureerdeKlr);
        TextView tvGeconfigureerdeLk = findViewById(R.id.tvGeconfigureerdeLk);
        TextView tvGeconfigureerdeVlg = findViewById(R.id.tvGeconfigureerdeVlg);

        tvGeconfigureerdeMdl.setText(strMdl);
        tvGeconfigureerdeKlr.setText(strKlr);
        tvGeconfigureerdeLk.setText(strLk);
        tvGeconfigureerdeVlg.setText(strVlg);

        btnBestellen = findViewById(R.id.btnBestellen);
        btnBestellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeconfigureerdeLightyear lightyear = (GeconfigureerdeLightyear) getIntent().getSerializableExtra(ConfiguratorActivity.CONFIGURERENMODEL);
                addGeconfigureerdeLightyear(lightyear);
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    //Methode die kijkt of het inserten gelukt is of niet
    public void addGeconfigureerdeLightyear(GeconfigureerdeLightyear cl) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        boolean insertGelukt = databaseHelper.addGeconfigureerdeLightyear(cl);
        if(insertGelukt) {
            Message.message(getApplicationContext(), "Bestelling is geplaatst!");
        } else {
            Message.message(getApplicationContext(), "Oeps, er is iets fout gegaan!");
        }
    }

    //Deze methode zorgt ervoor dat als je op de terugknop klikt, je naar de juiste activity gaat
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
