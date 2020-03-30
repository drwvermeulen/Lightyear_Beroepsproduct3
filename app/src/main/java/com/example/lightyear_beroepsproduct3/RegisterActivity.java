package com.example.lightyear_beroepsproduct3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private String strEmailadres;
    private Button btnRegistreren;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Terugknop
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHelper = new DatabaseHelper(RegisterActivity.this);

        btnRegistreren = findViewById(R.id.btnRegistreren);
        btnRegistreren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hier worden de waardes die ingevuld zijn omgezet in een string
                String strVoornaam = ((EditText)findViewById(R.id.etRegistrerenVoornaam)).getText().toString();
                String strAchternaam = ((EditText)findViewById(R.id.etRegistrerenAchternaam)).getText().toString();
                String strTelefoonnummer = ((EditText)findViewById(R.id.etRegistrerenTelefoonnummer)).getText().toString();
                strEmailadres = ((EditText)findViewById(R.id.etRegistrerenEmailadres)).getText().toString();
                String strWachtwoord = ((EditText)findViewById(R.id.etRegistrerenWachtwoord)).getText().toString();
                String strHerhaalWachtwoord = ((EditText)findViewById(R.id.etRegistrerenHerhaalWachtwoord)).getText().toString();

                //Deze string wordt juist geformateerd voor de KlantLogin constructor
                String strNaam = String.format("%s %s", strVoornaam, strAchternaam);

                //Deze if statement kijkt of alle velden zijn ingevuld
                String veldNamen = "";
                int j = 0;
                if (strVoornaam.equals("")){
                    veldNamen += "voornaam";
                    j++;
                }

                if (strAchternaam.equals("")) {
                    if (!veldNamen.equals("")) {
                        veldNamen += ", ";
                    }
                    veldNamen += "achternaam";
                    j++;
                }

                if (strTelefoonnummer.equals("")){
                    veldNamen += "telefoonnummer";
                    j++;
                }

                if (strEmailadres.equals("")) {
                    if (!veldNamen.equals("")) {
                        veldNamen += ", ";
                    }
                    veldNamen += "emailadres";
                    j++;
                }

                if (strWachtwoord.equals("")){
                    veldNamen += "wachtwoord";
                    j++;
                }

                if (strHerhaalWachtwoord.equals("")) {
                    if (!veldNamen.equals("")) {
                        veldNamen += ", ";
                    }
                    veldNamen += "herhaal wachtwoord";
                    j++;
                }

                if (j == 1) {
                    Message.message(getApplicationContext(), String.format("Het veld %s is niet gevuld", veldNamen));
                } else if (j > 1) {
                    Message.message(getApplicationContext(), String.format("De velden %s zijn niet gevuld", veldNamen));
                } else if (databaseHelper.checkEmailadres(strEmailadres)) {
                    Message.message(getApplicationContext(), "Het emailadres is al in gebruik!");
                } else if(!strWachtwoord.equals(strHerhaalWachtwoord)) {
                    Message.message(getApplicationContext(), "Wachtwoorden komen niet overeen!");
                } else {
                    KlantLogin kl = new KlantLogin(strEmailadres, strNaam, strWachtwoord, strTelefoonnummer);
                    addKlantLogin(kl);
                    Intent i = new Intent(v.getContext(), LoginActivity.class);
                    startActivity(i);
                }
            }
        });

        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }

    //Methode die kijkt of het inserten gelukt is of niet
    public void addKlantLogin(KlantLogin kl) {
        boolean insertGelukt = databaseHelper.addKlantLogin(kl);
        if(insertGelukt) {
            Message.message(getApplicationContext(), "Registratie succesvol!");
        } else {
            Message.message(getApplicationContext(), "Oeps, er is iets fout gegaan!");
        }
    }

    //Deze methode zorgt ervoor dat als je op de terugknop klikt, je naar de juiste activity gaat
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
