package com.example.lightyear_beroepsproduct3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConfiguratorLakActivity extends AppCompatActivity {
    private RadioGroup rgKeuzeLak;
    private RadioButton rbUnilak, rbMetalliclak, rbMattelak;
    private Button btnVolgende;
    private Model geselecteerdeModel;
    private Kleur geselecteerdeKleur;
    private Lak geselecteerdeLak;
    public static final String CONFIGURERENMODEL = "Model";
    public static final String CONFIGURERENKLEUR = "Kleur";
    public static final String CONFIGURERENLAK = "Lak";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator_lak);

        //Terugknop
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialiseert en wijst variabele toe
        BottomNavigationView bnvTopNavigation = findViewById(R.id.bnvTopNavigation);

        //Zet home op geselecteerd
        bnvTopNavigation.setSelectedItemId(R.id.configuratorLak);

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
                        return true;
                    case R.id.configuratorVelg:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorVelgActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        rgKeuzeLak = findViewById(R.id.rgKeuzeKleur);
        rbUnilak = findViewById(R.id.rbUnilak);
        rbUnilak.setText(Lak.Unilak.toString());
        rbMetalliclak = findViewById(R.id.rbMetalliclak);
        rbMetalliclak.setText(Lak.Metalliclak.toString());
        rbMattelak = findViewById(R.id.rbMattelak);
        rbMattelak.setText(Lak.Mattelak.toString());

        btnVolgende = findViewById(R.id.btnConfigureren);
        btnVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedID = rgKeuzeLak.getCheckedRadioButtonId();
                if(checkedID == -1) {
                    //No radio buttons are checked
                    Message.message(getApplicationContext(), "Selecteer iets!");
                }
                else {
                    //One of the radio buttons are selected
                    findRadioButton(checkedID);
//                    GeconfigureerdeLightyear lightyear = GeconfigureerdeLightyear.construct(geselecteerdeModel);
//                    int prijs = berekenPrijs();
//                    lightyear.berekenSubtotaal(prijs);

                    Intent i = getIntent();
                    geselecteerdeModel = i.getParcelableExtra(ConfiguratorActivity.CONFIGURERENMODEL);
                    geselecteerdeKleur = i.getParcelableExtra(ConfiguratorKleurActivity.CONFIGURERENKLEUR);

                    i = new Intent(v.getContext(), ConfiguratorVelgActivity.class);
                    i.putExtra(CONFIGURERENMODEL, geselecteerdeModel);
                    i.putExtra(CONFIGURERENKLEUR, geselecteerdeKleur);
                    i.putExtra(CONFIGURERENLAK, geselecteerdeLak);
                    startActivity(i);
                }
            }
        });
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

    //Deze methode kijkt welke radiobutton is geselecteerd en geeft de juiste waarde mee
    private void findRadioButton(int checkedID) {
        switch (checkedID) {
            case R.id.rbUnilak:
                geselecteerdeLak = Lak.Unilak;
                break;
            case R.id.rbMetalliclak:
                geselecteerdeLak = Lak.Metalliclak;
                break;
            case R.id.rbMattelak:
                geselecteerdeLak = Lak.Mattelak;
                break;
        }
    }
}
