package com.example.lightyear_beroepsproduct3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfielBewerkenActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText etProfielBewerkenVoornaam, etProfielBewerkenAchternaam, etProfielBewerkenTelefoonnummer, etProfielBewerkenWachtwoord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiel_bewerken);

        databaseHelper = new DatabaseHelper(ProfielBewerkenActivity.this);

        etProfielBewerkenVoornaam = findViewById(R.id.etProfielBewerkenVoornaam);
        etProfielBewerkenAchternaam = findViewById(R.id.etProfielBewerkenAchternaam);
        etProfielBewerkenTelefoonnummer = findViewById(R.id.etProfielBewerkenTelefoonnummer);
        etProfielBewerkenWachtwoord = findViewById(R.id.etProfielBewerkenWachtwoord);

        etProfielBewerkenVoornaam.setText(databaseHelper.getKlantVoornaam());
        etProfielBewerkenAchternaam.setText(databaseHelper.getKlantAchternaam());
        etProfielBewerkenTelefoonnummer.setText(databaseHelper.getTelefoonnummer());

        Button btnProfielAanpassen = findViewById(R.id.btnProfielAanpssen);
        btnProfielAanpassen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strProfielEmailadres = LoginActivity.strEmailadres;
                String strProfielWachtwoord = etProfielBewerkenWachtwoord.getText().toString();
                String strProfielVoornaam = etProfielBewerkenVoornaam.getText().toString();
                String strPofielAchternaam = etProfielBewerkenAchternaam.getText().toString();
                String strProfielTelefoonnummer = etProfielBewerkenTelefoonnummer.getText().toString();

                //Deze if statement kijkt of alle velden zijn ingevuld
                String veldNamen = "";
                int j = 0;
                if (strProfielVoornaam.equals("")) {
                    veldNamen += "voornaam";
                    j++;
                }

                if (strPofielAchternaam.equals("")) {
                    if (!veldNamen.equals("")) {
                        veldNamen += ", ";
                    }
                    veldNamen += "achternaam";
                    j++;
                }

                if (strProfielTelefoonnummer.equals("")) {
                    veldNamen += "telefoonnummer";
                    j++;
                }

                if (strProfielEmailadres.equals("")) {
                    if (!veldNamen.equals("")) {
                        veldNamen += ", ";
                    }
                    veldNamen += "emailadres";
                    j++;
                }

                if (strProfielWachtwoord.equals("")) {
                    veldNamen += "wachtwoord";
                    j++;
                }

                databaseHelper = new DatabaseHelper(ProfielBewerkenActivity.this);
                if (j == 1) {
                    Message.message(getApplicationContext(), String.format("Het veld %s is niet gevuld", veldNamen));
                } else if (j > 1) {
                    Message.message(getApplicationContext(), String.format("De velden %s zijn niet gevuld", veldNamen));
                } else if (!databaseHelper.checkWachtwoord(strProfielWachtwoord)) {
                    Message.message(getApplicationContext(), "Wachtwoord is niet juist!");
                } else {
                    updateKlantLogin(strProfielEmailadres, strProfielWachtwoord, strProfielVoornaam, strPofielAchternaam, strProfielTelefoonnummer);
                    Intent i = new Intent(v.getContext(), ProfileActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    //Methode die kijkt of het updaten gelukt is of niet
    public void updateKlantLogin(String mldrs, String wchtwrd, String vrnm, String chtrnm, String tlfnnmmr) {
        boolean insertGelukt = databaseHelper.updateKlantLogin(mldrs, wchtwrd, vrnm, chtrnm, tlfnnmmr);
        if(insertGelukt) {
            Message.message(getApplicationContext(), "Profiel aangepastl!");
        } else {
            Message.message(getApplicationContext(), "Oeps, er is iets fout gegaan!");
        }
    }
}
