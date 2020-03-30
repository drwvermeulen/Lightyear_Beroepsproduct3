package com.example.lightyear_beroepsproduct3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileBestellingActivity extends AppCompatActivity {
    private ImageView ivProfileLightyear, ivProfilePioneerEdition;
    private TextView tvProfileConfiguratienummer, tvProfileModel, tvProfileKleur, tvProfileLak, tvProfileVelgen, tvProfilePioneerEdition;
    private GeconfigureerdeLightyear geconfigureerdeLightyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_bestelling);

        ivProfileLightyear = findViewById(R.id.ivProfileLightyear);
        ivProfilePioneerEdition = findViewById(R.id.ivProfilePioneerEdition);
        tvProfileConfiguratienummer = findViewById(R.id.tvProfileConfiguratienummer);
        tvProfileModel = findViewById(R.id.tvProfileModel);
        tvProfileKleur = findViewById(R.id.tvProfileKleur);
        tvProfileLak = findViewById(R.id.tvProfileLak);
        tvProfileVelgen = findViewById(R.id.tvProfileVelgen);
        tvProfilePioneerEdition = findViewById(R.id.tvProfilePioneerEdition);

        getData();
        setData();
    }

    private void getData() {
        if(getIntent().hasExtra(ConfiguratorActivity.CONFIGURERENMODEL)) {
            geconfigureerdeLightyear = (GeconfigureerdeLightyear) getIntent().getSerializableExtra(ConfiguratorActivity.CONFIGURERENMODEL);
        } else {
            Message.message(getApplicationContext(), "No data");
        }
    }

    private void setData() {
        ivProfileLightyear.setImageResource(geconfigureerdeLightyear.getImageResource());
        tvProfileConfiguratienummer.setText(geconfigureerdeLightyear.getFormattedConfig());
        tvProfileModel.setText(geconfigureerdeLightyear.getMdl().toString());
        tvProfileKleur.setText(geconfigureerdeLightyear.getKlr().toString());
        tvProfileLak.setText(geconfigureerdeLightyear.getLk().toString());
        tvProfileVelgen.setText(geconfigureerdeLightyear.getVlg().toString());
        if(geconfigureerdeLightyear instanceof GeconfigureerdeLightyearPioneerEdition) {
            GeconfigureerdeLightyearPioneerEdition glpe = (GeconfigureerdeLightyearPioneerEdition) geconfigureerdeLightyear;
            tvProfilePioneerEdition.setText(glpe.getPnrdtn().toString());
        } else {
            tvProfilePioneerEdition.setVisibility(View.INVISIBLE);
            ivProfilePioneerEdition.setVisibility(View.INVISIBLE);
        }
    }
}
