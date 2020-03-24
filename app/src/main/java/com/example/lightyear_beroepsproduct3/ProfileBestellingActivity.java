package com.example.lightyear_beroepsproduct3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileBestellingActivity extends AppCompatActivity {
    ImageView ivProfileBestelling;
    TextView tvConfiguratienummerBestelling, tvConfiguratieItemsBestelling;

    String data1, data2;
    int images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_bestelling);

        ivProfileBestelling = findViewById(R.id.ivProfileBestelling);
        tvConfiguratienummerBestelling = findViewById(R.id.tvConfiguratienummerBestelling);
        tvConfiguratieItemsBestelling = findViewById(R.id.tvConfiguratieItemsBestelling);

        getData();
        setData();
    }

    private void getData() {
        if(getIntent().hasExtra("images") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2")) {
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            images = getIntent().getIntExtra("images", 1);
        } else {
            Message.message(getApplicationContext(), "Er is geen informatie beschikbaar!");
        }
    }

    private void setData() {
        ivProfileBestelling.setImageResource(images);
        tvConfiguratienummerBestelling.setText(data1);
        tvConfiguratieItemsBestelling.setText(data2);
    }
}
