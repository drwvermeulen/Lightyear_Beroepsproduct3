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

public class ConfiguratorKleurActivity extends AppCompatActivity {
    private RadioGroup rgKeuzeKleur;
    private RadioButton rbKleurZwart, rbKleurWit, rbKleurRood, rbKleurBlauw;
    private Button btnVolgende;
    private Model geselecteerdeModel;
    private Kleur geselecteerdeKleur;
    public static final String CONFIGURERENMODEL = "Model";
    public static final String CONFIGURERENKLEUR = "Kleur";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator_kleur);

        //Terugknop
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialiseert en wijst variabele toe
        BottomNavigationView bnvTopNavigation = findViewById(R.id.bnvTopNavigation);

        //Zet home op geselecteerd
        bnvTopNavigation.setSelectedItemId(R.id.configuratorKleur);

        //Perform ItemSelectedListener
        bnvTopNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.configuratorKleur:
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
                        startActivity(new Intent(getApplicationContext(), ConfiguratorBestellenActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        rgKeuzeKleur = findViewById(R.id.rgKeuzeKleur);
        rbKleurZwart = findViewById(R.id.rbKleurZwart);
        rbKleurZwart.setText(Kleur.KleurZwart.toString());
        rbKleurWit = findViewById(R.id.rbKleurWit);
        rbKleurWit.setText(Kleur.KleurWit.toString());
        rbKleurRood = findViewById(R.id.rbKleurRood);
        rbKleurRood.setText(Kleur.KleurRood.toString());
        rbKleurBlauw = findViewById(R.id.rbKleurBlauw);
        rbKleurBlauw.setText(Kleur.KleurBlauw.toString());

        btnVolgende = findViewById(R.id.btnVolgende);
        btnVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedID = rgKeuzeKleur.getCheckedRadioButtonId();
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
                    geselecteerdeModel = i.getParcelableExtra(ConfiguratorLakActivity.CONFIGURERENMODEL);

                    i = new Intent(ConfiguratorKleurActivity.this, ConfiguratorLakActivity.class);
                    i.putExtra(CONFIGURERENMODEL, geselecteerdeModel);
                    i.putExtra(CONFIGURERENKLEUR, geselecteerdeKleur);
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
            case R.id.rbKleurZwart:
                geselecteerdeKleur = Kleur.KleurZwart;
                break;
            case R.id.rbKleurWit:
                geselecteerdeKleur = Kleur.KleurWit;
                break;
            case R.id.rbKleurRood:
                geselecteerdeKleur = Kleur.KleurRood;
                break;
            case R.id.rbKleurBlauw:
                geselecteerdeKleur = Kleur.KleurBlauw;
                break;
        }
    }
}
