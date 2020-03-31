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

public class ConfiguratorVelgActivity extends AppCompatActivity {
    private GeconfigureerdeLightyear lightyear;
    private ImageView ivConfiguratorLightyear;
    private RadioGroup rgKeuzeVelg;
    private RadioButton rb16Velg, rb17Velg, rb18Velg;
    private Button btnVolgende;
    private TextView tvPrijs;
    private Velg geselecteerdeVelg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator_velg);

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

        lightyear = (GeconfigureerdeLightyear) getIntent().getSerializableExtra(ConfiguratorActivity.CONFIGURERENMODEL);

        ivConfiguratorLightyear = findViewById(R.id.ivConfiguratorLightyear);
        ivConfiguratorLightyear.setImageResource(lightyear.getImageResource());

        rgKeuzeVelg = findViewById(R.id.rgKeuzeVelg);
        rb16Velg = findViewById(R.id.rb16Velg);
        rb17Velg = findViewById(R.id.rb17Velg);
        rb18Velg = findViewById(R.id.rb18Velg);

        rb16Velg.setText(Velg.Velgen16.toString());
        rb17Velg.setText(Velg.Velgen17.toString());
        rb18Velg.setText(Velg.Velgen18.toString());

        tvPrijs = findViewById(R.id.tvPrijs);
        tvPrijs.setText(String.format("€ %,.2f", lightyear.berekenPrijs()));

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
                    if (lightyear != null) {
                        lightyear.setVlg(geselecteerdeVelg);
                        Intent i = new Intent(v.getContext(), ConfiguratorBestellenActivity.class);
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

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if(checked) {
            Velg vlg = null;
            switch(view.getId()) {
                case R.id.rb16Velg:
                    vlg = Velg.Velgen16;
                    break;
                case R.id.rb17Velg:
                    vlg = Velg.Velgen17;
                    break;
                case R.id.rb18Velg:
                    vlg = Velg.Velgen18;
                    break;
            }
            tvPrijs.setText(String.format("€ %,.2f", lightyear.berekenPrijs() + vlg.getPrijs()));
        }
    }
}
