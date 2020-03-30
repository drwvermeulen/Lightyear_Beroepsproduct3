package com.example.lightyear_beroepsproduct3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConfiguratorKleurActivity extends AppCompatActivity {
    private GeconfigureerdeLightyear lightyear;
    private ImageView ivConfiguratorLightyear;
    private RadioGroup rgKeuzeKleur;
    private RadioButton rbKleurZwart, rbKleurWit, rbKleurRood, rbKleurBlauw;
    private TextView tvPrijs;
    private Button btnVolgende;
    private Kleur geselecteerdeKleur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator_kleur);

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

        lightyear = (GeconfigureerdeLightyear) getIntent().getSerializableExtra(ConfiguratorActivity.CONFIGURERENMODEL);

        ivConfiguratorLightyear = findViewById(R.id.ivConfiguratorLightyear);

        rgKeuzeKleur = findViewById(R.id.rgKeuzeKleur);
        rbKleurZwart = findViewById(R.id.rbKleurZwart);
        rbKleurZwart.setText(Kleur.KleurZwart.toString());
        rbKleurWit = findViewById(R.id.rbKleurWit);
        rbKleurWit.setText(Kleur.KleurWit.toString());
        rbKleurRood = findViewById(R.id.rbKleurRood);
        rbKleurRood.setText(Kleur.KleurRood.toString());
        rbKleurBlauw = findViewById(R.id.rbKleurBlauw);
        rbKleurBlauw.setText(Kleur.KleurBlauw.toString());

        tvPrijs = findViewById(R.id.tvPrijs);
        tvPrijs.setText(String.format("€ %,.2f", lightyear.berekenPrijs()));

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
                    if (lightyear != null) {
                        lightyear.setKlr(geselecteerdeKleur);
                        Intent i = new Intent(v.getContext(), ConfiguratorLakActivity.class);
                        i.putExtra(ConfiguratorActivity.CONFIGURERENMODEL, lightyear);
                        startActivity(i);
                    }
                }
            }
        });
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

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if(checked) {
            Kleur klr = null;
            switch(view.getId()) {
                case R.id.rbKleurZwart:
                    klr = Kleur.KleurZwart;
                    ivConfiguratorLightyear.setImageResource(R.drawable.lightyearone_zwart);
                    break;
                case R.id.rbKleurWit:
                    klr = Kleur.KleurWit;
                    ivConfiguratorLightyear.setImageResource(R.drawable.lightyearone_wit);
                    break;
                case R.id.rbKleurRood:
                    klr = Kleur.KleurRood;
                    ivConfiguratorLightyear.setImageResource(R.drawable.lightyearone_rood);
                    break;
                case R.id.rbKleurBlauw:
                    klr = Kleur.KleurBlauw;
                    ivConfiguratorLightyear.setImageResource(R.drawable.lightyearone_blauw);
                    break;
            }
            tvPrijs.setText(String.format("€ %,.2f", lightyear.berekenPrijs() + klr.getPrijs()));
        }
    }
}
