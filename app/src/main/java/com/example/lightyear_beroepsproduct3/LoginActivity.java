package com.example.lightyear_beroepsproduct3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText etLoginEmailadres, etLoginWachtwoord;
    public static String strEmailadres;
    private Button btnLogin;
    private TextView tvRegistreren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        databaseHelper = new DatabaseHelper(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hier worden de waardes die ingevuld zijn omgezet in een string
                strEmailadres = ((EditText)findViewById(R.id.etLoginEmailadres)).getText().toString();
                String strWachtwoord = ((EditText)findViewById(R.id.etLoginWachtwoord)).getText().toString();

                //Deze if statement controleert of beide velden zijn ingevuld
                if(strEmailadres.equals("") && strWachtwoord.equals("")) {
                    Message.message(getApplicationContext(), "Velden zijn niet ingevuld!");
                    //Deze else if statement controleert of het emailadres veld is ingevuld
                } else if (strEmailadres.equals("")){
                    Message.message(getApplicationContext(), "Emailadres is niet ingevuld!");
                    //Deze else if statement controleert of het wachtwoord veld is ingevuld
                } else if (strWachtwoord.equals("")) {
                    Message.message(getApplicationContext(), "Wachtwoord is niet ingevuld!");
                    //Deze else if kijkt of het emailadres en wachtwoord bestaat en als dat klopt wordt er ingelogd
                } else if (!databaseHelper.checkLogin(strEmailadres, strWachtwoord)) {
                    Message.message(getApplicationContext(), "Emailadres of wachtwoord is onjuist!");
                } else {
                    Intent i = new Intent(v.getContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });

        tvRegistreren = findViewById(R.id.tvRegistreren);
        tvRegistreren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
