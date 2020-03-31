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

public class ConfiguratorLakActivity extends AppCompatActivity {
    private GeconfigureerdeLightyear lightyear;
    private ImageView ivConfiguratorLightyear;
    private RadioGroup rgKeuzeLak;
    private RadioButton rbUnilak, rbMetalliclak, rbMattelak;
    private TextView tvPrijs;
    private Button btnVolgende;
    private Lak geselecteerdeLak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator_lak);

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
        ivConfiguratorLightyear.setImageResource(lightyear.getImageResource());

        rgKeuzeLak = findViewById(R.id.rgKeuzeLak);
        rbUnilak = findViewById(R.id.rbUnilak);
        rbMetalliclak = findViewById(R.id.rbMetalliclak);
        rbMattelak = findViewById(R.id.rbMattelak);

        rbUnilak.setText(Lak.Unilak.toString());
        rbMetalliclak.setText(Lak.Metalliclak.toString());
        rbMattelak.setText(Lak.Mattelak.toString());

        tvPrijs = findViewById(R.id.tvPrijs);
        tvPrijs.setText(String.format("€ %,.2f", lightyear.berekenPrijs()));

        btnVolgende = findViewById(R.id.btnVolgende);
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
                    if (lightyear != null) {
                        lightyear.setLk(geselecteerdeLak);
                        Intent i = new Intent(v.getContext(), ConfiguratorVelgActivity.class);
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

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if(checked) {
            Lak lk = null;
            switch(view.getId()) {
                case R.id.rbUnilak:
                    lk = Lak.Unilak;
                    break;
                case R.id.rbMetalliclak:
                    lk = Lak.Metalliclak;
                    break;
                case R.id.rbMattelak:
                    lk = Lak.Mattelak;
                    break;
            }
            tvPrijs.setText(String.format("€ %,.2f", lightyear.berekenPrijs() + lk.getPrijs()));
        }
    }
}


