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

public class ConfiguratorUpdateLakActivity extends AppCompatActivity {
    private GeconfigureerdeLightyear lightyearUpdate;
    private ImageView ivConfiguratorUpdateLightyear;
    private RadioGroup rgUpdateKeuzeLak;
    private RadioButton rbUpdateUnilak, rbUpdateMetalliclak, rbUpdateMattelak;
    private TextView tvUpdatePrijs;
    private Button btnUpdateVolgende;
    private Lak geselecteerdeUpdateLak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator_update_lak);

        //Initialiseert en wijst variabele toe
        BottomNavigationView bnvTopNavigation = findViewById(R.id.bnvTopNavigationUpdate);

        //Zet home op geselecteerd
        bnvTopNavigation.setSelectedItemId(R.id.configuratorUpdateLak);

        //Perform ItemSelectedListener
        bnvTopNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.configuratorUpdateKleur:
                        startActivity(new Intent(getApplicationContext(), ConfiguratorUpdateKleurActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.configuratorUpdateLak:
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

        rgUpdateKeuzeLak = findViewById(R.id.rgUpdateKeuzeLak);
        rbUpdateUnilak = findViewById(R.id.rbUpdateUnilak);
        rbUpdateMetalliclak = findViewById(R.id.rbUpdateMetalliclak);
        rbUpdateMattelak = findViewById(R.id.rbUpdateMattelak);

        rbUpdateUnilak.setText(Lak.Unilak.toString());
        rbUpdateMetalliclak.setText(Lak.Metalliclak.toString());
        rbUpdateMattelak.setText(Lak.Mattelak.toString());

        tvUpdatePrijs = findViewById(R.id.tvUpdatePrijs);
        tvUpdatePrijs.setText(String.format("€ %,.2f", lightyearUpdate.berekenPrijs()));

        btnUpdateVolgende = findViewById(R.id.btnUpdateVolgende);
        btnUpdateVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedID = rgUpdateKeuzeLak.getCheckedRadioButtonId();
                if(checkedID == -1) {
                    //No radio buttons are checked
                    Message.message(getApplicationContext(), "Selecteer iets!");
                }
                else {
                    //One of the radio buttons are selected
                    findRadioButton(checkedID);
                    if (lightyearUpdate != null) {
                        lightyearUpdate.setLk(geselecteerdeUpdateLak);
                        Intent i = new Intent(v.getContext(), ConfiguratorUpdateVelgActivity.class);
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
            case R.id.rbUpdateUnilak:
                geselecteerdeUpdateLak = Lak.Unilak;
                break;
            case R.id.rbUpdateMetalliclak:
                geselecteerdeUpdateLak = Lak.Metalliclak;
                break;
            case R.id.rbUpdateMattelak:
                geselecteerdeUpdateLak = Lak.Mattelak;
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if(checked) {
            Lak lk = null;
            switch(view.getId()) {
                case R.id.rbUpdateUnilak:
                    lk = Lak.Unilak;
                    break;
                case R.id.rbUpdateMetalliclak:
                    lk = Lak.Metalliclak;
                    break;
                case R.id.rbUpdateMattelak:
                    lk = Lak.Mattelak;
                    break;
            }
            tvUpdatePrijs.setText(String.format("€ %,.2f", lightyearUpdate.berekenPrijs() + lk.getPrijs()));
        }
    }
}