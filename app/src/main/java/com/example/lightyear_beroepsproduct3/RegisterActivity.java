package com.example.lightyear_beroepsproduct3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    Button btnRegistreren;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegistreren = findViewById(R.id.btnRegistreren);
        databaseHelper = new DatabaseHelper(this);
        btnRegistreren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hier worden de waardes die ingevuld zijn omgezet in een string
                String strVoornaam = ((EditText)findViewById(R.id.etRegistrerenVoornaam)).getText().toString();
                String strAchternaam = ((EditText)findViewById(R.id.etRegistrerenAchternaam)).getText().toString();
                String strTelefoonnummer = ((EditText)findViewById(R.id.etRegistrerenTelefoonnummer)).getText().toString();
                String strEmailadres = ((EditText)findViewById(R.id.etRegistrerenEmailadres)).getText().toString();
                String strWachtwoord = ((EditText)findViewById(R.id.etRegistrerenWachtwoord)).getText().toString();
                String strHerhaalWachtwoord = ((EditText)findViewById(R.id.etRegistrerenHerhaalWachtwoord)).getText().toString();

                //Deze string wordt juist geformateerd voor de KlantLogin constructor
                String strNaam = String.format("%s %s", strVoornaam, strAchternaam);

                //Deze if statement kijkt of alle velden zijn ingevuld
                if(strVoornaam.equals("") && strAchternaam.equals("") && strTelefoonnummer.equals("") && strEmailadres.equals("") && strWachtwoord.equals("") && strHerhaalWachtwoord.equals(""))
                    Message.message(getApplicationContext(), "Velden zijn niet ingevuld!");
                //Deze else if statement kijkt of er een of meerdere velden niet zijn ingevuld
                else if (strVoornaam.equals("") || strAchternaam.equals("") || strTelefoonnummer.equals("") || strEmailadres.equals("") || strWachtwoord.equals("") || strHerhaalWachtwoord.equals("")) {
                    Message.message(getApplicationContext(), "Een of meerdere velden zijn niet ingevuld!");
                }
                //Deze else if statement kijkt of de voornaam is ingevuld
                else if (strVoornaam.equals("")) {
                    Message.message(getApplicationContext(), "De voornaam is niet ingevuld!");
                }
                //Deze else if statement kijkt of de achternaam is ingevuld
                else if (strAchternaam.equals("")) {
                    Message.message(getApplicationContext(), "De achternaam is niet ingevuld!");
                }
                //Deze else if statement kijkt of het telefoonnummer is ingevuld
                else if (strTelefoonnummer.equals("")) {
                    Message.message(getApplicationContext(), "Het telefoonnummer is niet ingevuld!");
                }
                //Deze else if statement kijkt of het emailadres is ingevuld
                else if (strEmailadres.equals("")) {
                    Message.message(getApplicationContext(), "Het emailadres is niet ingevuld!");
                }
                //Deze else if statement kijkt of het wachtwoord is ingevuld
                else if (strWachtwoord.equals("")) {
                    Message.message(getApplicationContext(), "Het wachtwoord is niet ingevuld!");
                }
                //Deze else if statement kijkt of het herhaal wachtwoord is ingevuld
                else if (strHerhaalWachtwoord.equals("")) {
                    Message.message(getApplicationContext(), "Het herhaal wachtwoord is niet ingevuld!");
                }
                //Controleert of de wachtwoorden die ingevuld zijn gelijk zijn aan elkaar en als dat waar is wordt er een nieuwe KlantLogin geinstansieerd
                else if(!strWachtwoord.equals(strHerhaalWachtwoord)) {
                    Message.message(getApplicationContext(), "Wachtwoorden komen niet overeen!");
                } else {
                    KlantLogin kl = new KlantLogin(strEmailadres, strNaam, strWachtwoord, strTelefoonnummer);
                    addKlantLogin(kl);
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
}
