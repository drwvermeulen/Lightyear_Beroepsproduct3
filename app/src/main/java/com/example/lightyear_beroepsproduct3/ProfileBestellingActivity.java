package com.example.lightyear_beroepsproduct3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileBestellingActivity extends AppCompatActivity {
    private ImageView ivProfileLightyear, ivProfilePioneerEdition;
    private TextView tvProfileConfiguratienummer, tvProfileModel, tvProfileKleur, tvProfileLak, tvProfileVelgen;
    private GeconfigureerdeLightyear geconfigureerdeLightyear;
    public static final String UPDATELIGHTYEARCONFIGURATIENUMMER = "cnfgrtnmmr";
    public static final String UPDATELIGHTYEARMODEL = "mdl";

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

        getData();
        setData();

        Button btnAanpassen = findViewById(R.id.btnAanpassen);
        btnAanpassen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ConfiguratorUpdateKleurActivity.class);
                i.putExtra(UPDATELIGHTYEARMODEL, geconfigureerdeLightyear);
                startActivity(i);
            }
        });
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
        tvProfileKleur.setText(geconfigureerdeLightyear.getKlr().toString());
        tvProfileLak.setText(geconfigureerdeLightyear.getLk().toString());
        tvProfileVelgen.setText(geconfigureerdeLightyear.getVlg().toString());
        if(geconfigureerdeLightyear instanceof GeconfigureerdeLightyearPioneerEdition) {
            GeconfigureerdeLightyearPioneerEdition glpe = (GeconfigureerdeLightyearPioneerEdition) geconfigureerdeLightyear;
            tvProfileModel.setText(String.format("%s - %s", geconfigureerdeLightyear.getMdl(), glpe.getPnrdtn()));
        } else {
            ivProfilePioneerEdition.setVisibility(View.INVISIBLE);
        }
    }
}
