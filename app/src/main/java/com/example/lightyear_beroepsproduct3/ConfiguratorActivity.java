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
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConfiguratorActivity extends AppCompatActivity {
    private RadioGroup rgKeuzeModel;
    private RadioButton rbLightyearOne, rbLightyearOnePioneer;
    private TextView tvPrijs;
    private Button btnConfigureren;
    private Model geselecteerdeModel;
    public static final String CONFIGURERENMODEL = "Model";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurator);

        rgKeuzeModel = findViewById(R.id.rgKeuzeModel);
        rbLightyearOne = findViewById(R.id.rbLightyearOne);
        rbLightyearOne.setText(Model.LightyearOne.toString());
        rbLightyearOnePioneer = findViewById(R.id.rbLightyearOnePioneer);
        rbLightyearOnePioneer.setText(Model.LightyearPioneer.toString());

        tvPrijs = findViewById(R.id.tvPrijs);

        btnConfigureren = findViewById(R.id.btnConfigureren);
        btnConfigureren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedID = rgKeuzeModel.getCheckedRadioButtonId();
                if(checkedID == -1) {
                    //No radio buttons are checked
                    Message.message(getApplicationContext(), "Selecteer iets!");
                } else {
                    //One of the radio buttons are selected
                    findRadioButton(checkedID);
                    GeconfigureerdeLightyear lightyear = GeconfigureerdeLightyear.construct(geselecteerdeModel);
                    Intent i = new Intent (v.getContext(), ConfiguratorKleurActivity.class);
                    i.putExtra(CONFIGURERENMODEL, lightyear);
                    startActivity(i);
                }
            }
        });

        //Initialiseert en wijst variabele toe
        BottomNavigationView bnvBottomNavigation = findViewById(R.id.bnvBottomNavigation);

        //Zet home op geselecteerd
        bnvBottomNavigation.setSelectedItemId(R.id.configurator);

        //Perform ItemSelectedListener
        bnvBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.configurator:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    //Deze methode kijkt welke radiobutton is geselecteerd en geeft de juiste waarde mee
    private void findRadioButton(int checkedID) {
        switch (checkedID) {
            case R.id.rbLightyearOne:
                geselecteerdeModel = Model.LightyearOne;
                break;
            case R.id.rbLightyearOnePioneer:
                geselecteerdeModel = Model.LightyearPioneer;
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if(checked) {
            Model mdl = null;
            switch(view.getId()) {
                case R.id.rbLightyearOne:
                    mdl = Model.LightyearOne;
                    break;
                case R.id.rbLightyearOnePioneer:
                    mdl = Model.LightyearPioneer;
                    break;
            }
            tvPrijs.setText(String.format("€ %,.2f", mdl.getPrijs()));
        }
    }
}
