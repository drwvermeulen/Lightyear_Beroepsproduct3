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

public class ConfiguratorUpdateBestellingActivity extends AppCompatActivity {
    private GeconfigureerdeLightyear lightyearUpdate;
    private String strUpdateCnfgrtnmmr, strUpdateMdl;
    private DatabaseHelper databaseHelperUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator_update_bestelling);

        //Initialiseert en wijst variabele toe
        BottomNavigationView bnvTopNavigation = findViewById(R.id.bnvTopNavigationUpdate);

        //Zet home op geselecteerd
        bnvTopNavigation.setSelectedItemId(R.id.configuratorUpdateBestelling);

        //Perform ItemSelectedListener
        bnvTopNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.configuratorUpdateKleur:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorUpdateKleurActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.configuratorLak:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorUpdateLakActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.configuratorUpdateVelg:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorUpdateVelgActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.configuratorUpdateBestelling:
                        return true;
                }
                return false;
            }
        });

        lightyearUpdate = (GeconfigureerdeLightyear) getIntent().getSerializableExtra(ProfileBestellingActivity.UPDATELIGHTYEARCONFIGURATIENUMMER);
        lightyearUpdate = (GeconfigureerdeLightyear) getIntent().getSerializableExtra(ProfileBestellingActivity.UPDATELIGHTYEARMODEL);

        ImageView ivConfiguratorUpdateLightyear = findViewById(R.id.ivConfiguratorUpdateLightyear);
        ivConfiguratorUpdateLightyear.setImageResource(lightyearUpdate.getImageResource());

        //De waardes van de geconfigureerde lightyear worden in een string geplaatst
        strUpdateCnfgrtnmmr = lightyearUpdate.getCnfgrtnmmr().toString();
        strUpdateMdl = lightyearUpdate.getMdl().toString();
        String strUpdateKlr = lightyearUpdate.getKlr().toString();
        String strUpdateLk = lightyearUpdate.getLk().toString();
        String strUpdateVlg = lightyearUpdate.getVlg().toString();

        //De textview wordt geinitaliseerd
        TextView tvUpdateGeconfigureerdeMdl = findViewById(R.id.tvUpdateGeconfigureerdeMdl);
        TextView tvUpdateGeconfigureerdeKlr = findViewById(R.id.tvUpdateGeconfigureerdeKlr);
        TextView tvUpdateGeconfigureerdeLk = findViewById(R.id.tvUpdateGeconfigureerdeLk);
        TextView tvUpdateGeconfigureerdeVlg = findViewById(R.id.tvUpdateGeconfigureerdeVlg);
        TextView tvUpdateGeconfigureerdePnrdtn = findViewById(R.id.tvUpdateGeconfigureerdePnrdtn);

        //De waardes in de string worden getoond op de textview
        tvUpdateGeconfigureerdeMdl.setText(strUpdateMdl);
        tvUpdateGeconfigureerdeKlr.setText(strUpdateKlr);
        tvUpdateGeconfigureerdeLk.setText(strUpdateLk);
        tvUpdateGeconfigureerdeVlg.setText(strUpdateVlg);

        if(lightyearUpdate instanceof GeconfigureerdeLightyearPioneerEdition) {
            GeconfigureerdeLightyearPioneerEdition clpe = (GeconfigureerdeLightyearPioneerEdition) lightyearUpdate;
            Integer pnrdtnUpdate = clpe.getPnrdtn();
            tvUpdateGeconfigureerdePnrdtn.setText(pnrdtnUpdate.toString());
        } else {
            tvUpdateGeconfigureerdePnrdtn.setVisibility(View.INVISIBLE);
        }

        TextView tvUpdatePrijs = findViewById(R.id.tvUpdatePrijs);
        tvUpdatePrijs.setText(String.format("€ %,.2f", lightyearUpdate.berekenPrijs()));

        Button btnUpdateBestelling = findViewById(R.id.btnUpdateBestelling);
        btnUpdateBestelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperUpdate = new DatabaseHelper(ConfiguratorUpdateBestellingActivity.this);
                updateGeconfigureerdeLightyear(lightyearUpdate, strUpdateCnfgrtnmmr, strUpdateMdl);
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    //Methode die kijkt of het updaten gelukt is of niet
    public void updateGeconfigureerdeLightyear(GeconfigureerdeLightyear cl, String cnfgrtnmmr, String mdl){
        databaseHelperUpdate = new DatabaseHelper(ConfiguratorUpdateBestellingActivity.this);
        boolean updateGelukt = databaseHelperUpdate.updateGeconfigureerdeLightyear(cl, cnfgrtnmmr, mdl);
        if(updateGelukt) {
            Message.message(getApplicationContext(), "Bestelling is aangepast!");
        } else {
            Message.message(getApplicationContext(), "Oeps, er is iets fout gegaan!");
        }
    }
}
