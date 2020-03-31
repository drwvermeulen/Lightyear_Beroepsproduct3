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

public class ConfiguratorUpdateKleurActivity extends AppCompatActivity {
    private GeconfigureerdeLightyear lightyearUpdate;
    private ImageView ivConfiguratorUpdateLightyear;
    private RadioGroup rgUpdateKeuzeKleur;
    private RadioButton rbUpdateKleurZwart, rbUpdateKleurWit, rbUpdateKleurRood, rbupdateKleurBlauw;
    private TextView tvUpdatePrijs;
    private Button btnUpdateVolgende;
    private Kleur geselecteerdeUpdateKleur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator_update_kleur);


        BottomNavigationView bnvTopNavigationUpdate = findViewById(R.id.bnvTopNavigationUpdate);
        bnvTopNavigationUpdate.setSelectedItemId(R.id.configuratorUpdateKleur);
        bnvTopNavigationUpdate.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.configuratorUpdateKleur:
                        return true;
                    case R.id.configuratorUpdateLak:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorUpdateLakActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.configuratorUpdateVelg:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorUpdateVelgActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.configuratorUpdateBestelling:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorUpdateBestellingActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        lightyearUpdate = (GeconfigureerdeLightyear) getIntent().getSerializableExtra(ProfileBestellingActivity.UPDATELIGHTYEARCONFIGURATIENUMMER);
        lightyearUpdate = (GeconfigureerdeLightyear) getIntent().getSerializableExtra(ProfileBestellingActivity.UPDATELIGHTYEARMODEL);

        ivConfiguratorUpdateLightyear = findViewById(R.id.ivConfiguratorUpdateLightyear);
        ivConfiguratorUpdateLightyear.setImageResource(lightyearUpdate.getImageResource());

        rgUpdateKeuzeKleur = findViewById(R.id.rgUpdateKeuzeKleur);
        rbUpdateKleurZwart = findViewById(R.id.rbUpdateKleurZwart);
        rbUpdateKleurWit = findViewById(R.id.rbUpdateKleurWit);
        rbUpdateKleurRood = findViewById(R.id.rbUpdateKleurRood);
        rbupdateKleurBlauw = findViewById(R.id.rbUpdateKleurBlauw);

        rbUpdateKleurZwart.setText(Kleur.KleurZwart.toString());
        rbUpdateKleurWit.setText(Kleur.KleurWit.toString());
        rbUpdateKleurRood.setText(Kleur.KleurRood.toString());
        rbupdateKleurBlauw.setText(Kleur.KleurBlauw.toString());

        tvUpdatePrijs = findViewById(R.id.tvUpdatePrijs);
        tvUpdatePrijs.setText(String.format("€ %,.2f", lightyearUpdate.berekenPrijs()));

        btnUpdateVolgende = findViewById(R.id.btnUpdateVolgende);
        btnUpdateVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedID = rgUpdateKeuzeKleur.getCheckedRadioButtonId();
                if(checkedID == -1) {
                    //No radio buttons are checked
                    Message.message(getApplicationContext(), "Selecteer iets!");
                }
                else {
                    //One of the radio buttons are selected
                    findRadioButton(checkedID);
                    if (lightyearUpdate != null) {
                        lightyearUpdate.setKlr(geselecteerdeUpdateKleur);
                        Intent i = new Intent(v.getContext(), ConfiguratorUpdateLakActivity.class);
                        i.putExtra(ProfileBestellingActivity.UPDATELIGHTYEARCONFIGURATIENUMMER, lightyearUpdate);
                        i.putExtra(ProfileBestellingActivity.UPDATELIGHTYEARMODEL, lightyearUpdate);
                        startActivity(i);
                    }
                }
            }
        });
    }

    //Deze methode kijkt welke radiobutton is geselecteerd en geeft de juiste waarde mee
    private void findRadioButton(int checkedID) {
        switch (checkedID) {
            case R.id.rbUpdateKleurZwart:
                geselecteerdeUpdateKleur = Kleur.KleurZwart;
                break;
            case R.id.rbUpdateKleurWit:
                geselecteerdeUpdateKleur = Kleur.KleurWit;
                break;
            case R.id.rbUpdateKleurRood:
                geselecteerdeUpdateKleur = Kleur.KleurRood;
                break;
            case R.id.rbUpdateKleurBlauw:
                geselecteerdeUpdateKleur = Kleur.KleurBlauw;
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if(checked) {
            Kleur klr = null;
            switch(view.getId()) {
                case R.id.rbUpdateKleurZwart:
                    klr = Kleur.KleurZwart;
                    ivConfiguratorUpdateLightyear.setImageResource(R.drawable.lightyearone_zwart);
                    break;
                case R.id.rbUpdateKleurWit:
                    klr = Kleur.KleurWit;
                    ivConfiguratorUpdateLightyear.setImageResource(R.drawable.lightyearone_wit);
                    break;
                case R.id.rbUpdateKleurRood:
                    klr = Kleur.KleurRood;
                    ivConfiguratorUpdateLightyear.setImageResource(R.drawable.lightyearone_rood);
                    break;
                case R.id.rbUpdateKleurBlauw:
                    klr = Kleur.KleurBlauw;
                    ivConfiguratorUpdateLightyear.setImageResource(R.drawable.lightyearone_blauw);
                    break;
            }
            tvUpdatePrijs.setText(String.format("€ %,.2f", lightyearUpdate.berekenPrijs() + klr.getPrijs()));
        }
    }
}
