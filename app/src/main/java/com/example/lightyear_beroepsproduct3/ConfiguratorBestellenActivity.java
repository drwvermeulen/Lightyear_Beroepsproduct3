package com.example.lightyear_beroepsproduct3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConfiguratorBestellenActivity extends AppCompatActivity {
    private GeconfigureerdeLightyear lightyear;
    private ImageView ivConfiguratorLightyear;
    private DatabaseHelper databaseHelper;
    private Integer pnrdtn;
    private TextView tvGeconfigureerdePnrdtn, tvPrijs;
    private Button btnBestellen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator_bestellen);

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

        lightyear = (GeconfigureerdeLightyear) getIntent().getSerializableExtra(ConfiguratorActivity.CONFIGURERENMODEL);

        ivConfiguratorLightyear = findViewById(R.id.ivConfiguratorLightyear);
        ivConfiguratorLightyear.setImageResource(lightyear.getImageResource());

        //De waardes van de geconfigureerde lightyear worden in een string geplaatst
        String strMdl = lightyear.getMdl().toString();
        String strKlr = lightyear.getKlr().toString();
        String strLk = lightyear.getLk().toString();
        String strVlg = lightyear.getVlg().toString();

        //De textview wordt geinitaliseerd
        TextView tvGeconfigureerdeMdl = findViewById(R.id.tvGeconfigureerdeMdl);
        TextView tvGeconfigureerdeKlr = findViewById(R.id.tvGeconfigureerdeKlr);
        TextView tvGeconfigureerdeLk = findViewById(R.id.tvGeconfigureerdeLk);
        TextView tvGeconfigureerdeVlg = findViewById(R.id.tvGeconfigureerdeVlg);
        tvGeconfigureerdePnrdtn = findViewById(R.id.tvGeconfigureerdePnrdtn);

        //De waardes in de string worden getoond op de textview
        tvGeconfigureerdeMdl.setText(strMdl);
        tvGeconfigureerdeKlr.setText(strKlr);
        tvGeconfigureerdeLk.setText(strLk);
        tvGeconfigureerdeVlg.setText(strVlg);

        if(lightyear.getMdl().equals(Model.LightyearPioneer)) {
            databaseHelper = new DatabaseHelper(ConfiguratorBestellenActivity.this);
            pnrdtn = databaseHelper.getNummerReeksVolgendeWaarde(DatabaseHelper.PIONEERNUMMERREEKSID);
            tvGeconfigureerdePnrdtn.setText(pnrdtn.toString());
        } else {
            tvGeconfigureerdePnrdtn.setVisibility(View.INVISIBLE);
        }

        tvPrijs = findViewById(R.id.tvPrijs);
        tvPrijs.setText(String.format("€ %,.2f", lightyear.berekenPrijs()));

        btnBestellen = findViewById(R.id.btnBestellen);
        btnBestellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper = new DatabaseHelper(ConfiguratorBestellenActivity.this);
                if(lightyear instanceof GeconfigureerdeLightyearPioneerEdition) {
                    GeconfigureerdeLightyearPioneerEdition clpe = (GeconfigureerdeLightyearPioneerEdition) lightyear;
                   clpe.setPnrdtn(pnrdtn);
                   addGeconfigureerdeLightyear(clpe);
                   databaseHelper.updateNummerReeksVolgendeWaarde(DatabaseHelper.PIONEERNUMMERREEKSID, pnrdtn + 1);
                } else {
                    addGeconfigureerdeLightyear(lightyear);
                }
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    //Methode die kijkt of het inserten gelukt is of niet
    public void addGeconfigureerdeLightyear(GeconfigureerdeLightyear cl) {
        databaseHelper = new DatabaseHelper(ConfiguratorBestellenActivity.this);
        boolean insertGelukt = databaseHelper.addGeconfigureerdeLightyear(cl);
        if(insertGelukt) {
            Message.message(getApplicationContext(), "Bestelling is geplaatst!");
        } else {
            Message.message(getApplicationContext(), "Oeps, er is iets fout gegaan!");
        }
    }
}
