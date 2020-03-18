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

public class ConfiguratorVelgActivity extends AppCompatActivity {
    private RadioGroup rgKeuzeVelg;
    private RadioButton rb16Velg, rb17Velg, rb18Velg;
    private Button btnVolgende;
    private Model geselecteerdeModel;
    private Kleur geselecteerdeKleur;
    private Lak geselecteerdeLak;
    private Velg geselecteerdeVelg;
    public static final String CONFIGURERENMODEL = "Model";
    public static final String CONFIGURERENKLEUR = "Kleur";
    public static final String CONFIGURERENLAK = "Lak";
    public static final String CONFIGURERENVELG = "Velg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator_velg);

        //Terugknop
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialiseert en wijst variabele toe
        BottomNavigationView bnvTopNavigation = findViewById(R.id.bnvTopNavigation);

        //Zet home op geselecteerd
        bnvTopNavigation.setSelectedItemId(R.id.configuratorVelg);

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
                        return true;
                    case R.id.configuratorBestellen:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorBestellenActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        rgKeuzeVelg = findViewById(R.id.rgKeuzeVelg);
        rb16Velg = findViewById(R.id.rb16Velg);
        rb16Velg.setText(Velg.Velgen16.toString());
        rb17Velg = findViewById(R.id.rb17Velg);
        rb17Velg.setText(Velg.Velgen17.toString());
        rb18Velg = findViewById(R.id.rb18Velg);
        rb18Velg.setText(Velg.Velgen18.toString());

        btnVolgende = findViewById(R.id.btnVolgende);
        btnVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedID = rgKeuzeVelg.getCheckedRadioButtonId();
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
                    geselecteerdeLak = i.getParcelableExtra(ConfiguratorLakActivity.CONFIGURERENLAK);

                    i = new Intent(v.getContext(), ConfiguratorBestellenActivity.class);
                    i.putExtra(CONFIGURERENMODEL, geselecteerdeModel);
                    i.putExtra(CONFIGURERENKLEUR, geselecteerdeKleur);
                    i.putExtra(CONFIGURERENLAK, geselecteerdeLak);
                    i.putExtra(CONFIGURERENVELG, geselecteerdeVelg);
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
            case R.id.rb16Velg:
                geselecteerdeVelg = Velg.Velgen16;
                break;
            case R.id.rb17Velg:
                geselecteerdeVelg = Velg.Velgen17;
                break;
            case R.id.rb18Velg:
                geselecteerdeVelg = Velg.Velgen18;
                break;
        }
    }
}
