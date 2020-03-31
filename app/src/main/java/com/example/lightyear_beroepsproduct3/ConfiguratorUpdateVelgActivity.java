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

public class ConfiguratorUpdateVelgActivity extends AppCompatActivity {
    private GeconfigureerdeLightyear lightyearUpdate;
    private ImageView ivConfiguratorUpdateLightyear;
    private RadioGroup rgUpdateKeuzeVelg;
    private RadioButton rbUpdate16Velg, rbUpdate17Velg, rbUpdate18Velg;
    private Button btnUpdateVolgende;
    private TextView tvUpdatePrijs;
    private Velg geselecteerdeUpdateVelg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator_update_velg);

        //Initialiseert en wijst variabele toe
        BottomNavigationView bnvTopNavigation = findViewById(R.id.bnvTopNavigationUpdate);

        //Zet home op geselecteerd
        bnvTopNavigation.setSelectedItemId(R.id.configuratorUpdateVelg);

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
                        startActivity(new Intent(getApplicationContext(), ConfiguratorUpdateLakActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.configuratorUpdateVelg:
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

        rgUpdateKeuzeVelg = findViewById(R.id.rgUpdateKeuzeVelg);
        rbUpdate16Velg = findViewById(R.id.rbUpdate16Velg);
        rbUpdate17Velg = findViewById(R.id.rbUpdate17Velg);
        rbUpdate18Velg = findViewById(R.id.rbUpdate18Velg);

        rbUpdate16Velg.setText(Velg.Velgen16.toString());
        rbUpdate17Velg.setText(Velg.Velgen17.toString());
        rbUpdate18Velg.setText(Velg.Velgen18.toString());

        tvUpdatePrijs = findViewById(R.id.tvUpdatePrijs);
        tvUpdatePrijs.setText(String.format("€ %,.2f", lightyearUpdate.berekenPrijs()));

        btnUpdateVolgende = findViewById(R.id.btnUpdateVolgende);
        btnUpdateVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedID = rgUpdateKeuzeVelg.getCheckedRadioButtonId();
                if(checkedID == -1) {
                    //No radio buttons are checked
                    Message.message(getApplicationContext(), "Selecteer iets!");
                }
                else {
                    //One of the radio buttons are selected
                    findRadioButton(checkedID);
                    if (lightyearUpdate != null) {
                        lightyearUpdate.setVlg(geselecteerdeUpdateVelg);
                        Intent i = new Intent(v.getContext(), ConfiguratorUpdateBestellingActivity.class);
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
            case R.id.rbUpdate16Velg:
                geselecteerdeUpdateVelg = Velg.Velgen16;
                break;
            case R.id.rbUpdate17Velg:
                geselecteerdeUpdateVelg = Velg.Velgen17;
                break;
            case R.id.rbUpdate18Velg:
                geselecteerdeUpdateVelg = Velg.Velgen18;
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if(checked) {
            Velg vlg = null;
            switch(view.getId()) {
                case R.id.rbUpdate16Velg:
                    vlg = Velg.Velgen16;
                    break;
                case R.id.rbUpdate17Velg:
                    vlg = Velg.Velgen17;
                    break;
                case R.id.rbUpdate18Velg:
                    vlg = Velg.Velgen18;
                    break;
            }
            tvUpdatePrijs.setText(String.format("€ %,.2f", lightyearUpdate.berekenPrijs() + vlg.getPrijs()));
        }
    }
}
