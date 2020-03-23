package com.example.lightyear_beroepsproduct3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Naam  van de database
    private static final String DATABASE_NAME = "LightyearDatabase";

    //Deze strings worden gebruikt voor de tabel klantlogin
    private static final String TABLE_KLANTLOGIN = "KlantLogin";
    private static final String COL_EMALADRES = "mldrs";
    private static final String COL_WACHTWOORD = "wchtwrd";
    private static final String COL_NAAM = "nm";
    private static final String COL_TELEFOONNUMMER = "tlfnnmmr";
    private static final String CONSTRAINT_PK_MLDRS = "pk_mldrs";

    //Dit is de string die uiteindelijk de tabel klantlogin gaat creeeren
    private String strCreateTableKlantLogin ="CREATE TABLE " + TABLE_KLANTLOGIN + "("
            + COL_EMALADRES + " TEXT NOT NULL, "
            + COL_WACHTWOORD + " TEXT NOT NULL, "
            + COL_NAAM + " TEXT NOT NULL, "
            + COL_TELEFOONNUMMER + " TEXT NOT NULL,"
            + "CONSTRAINT " + CONSTRAINT_PK_MLDRS + " PRIMARY KEY (" + COL_EMALADRES + "))";

    //Deez strings worden gebruikt voor de tabel geconfigureerde lightyear
    private static final String TABLE_GECONFIGUREERDELIGHTYEAR = "GeconfigureerdeLightyear";
    private static final String COL_CONFIGURATIENUMMER = "cnfgrtnmmr";
    private static final String COL_EMAILADRES_KLANTLOGIN = "mldrs";
    private static final String COL_MODEL = "mdl";
    private static final String COL_KLEUR = "klr";
    private static final String COL_LAK = "lk";
    private static final String COL_VELG = "vlg";
    private static final String COL_PIONEEREDITION = "pnrdtn";
    private static final String CONSTRAINT_PK_CNFGRTNMMR = "pk_cnfgrtnmmr";
    private static final String CONSTRAINT_FK_MLDRS_KLANTLOGIN = "fk_mldrs";

    //Dit is de string die uiteindelijk de tabel geconfigureerdelightyear gaat creeeren
    private String strCreateTableGeconfigureerdeLightyear = "CREATE TABLE " + TABLE_GECONFIGUREERDELIGHTYEAR + "("
            + COL_CONFIGURATIENUMMER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_EMAILADRES_KLANTLOGIN + " TEXT NOT NULL, "
            + COL_MODEL + " TEXT NOT NULL, "
            + COL_KLEUR + " TEXT NOT NULL, "
            + COL_LAK + " TEXT NOT NULL, "
            + COL_VELG + " TEXT NOT NULL, "
            + "CONSTRAINT " + CONSTRAINT_FK_MLDRS_KLANTLOGIN + " FOREIGN KEY (" + COL_EMAILADRES_KLANTLOGIN + ") REFERENCES " + TABLE_KLANTLOGIN + " (" + COL_EMALADRES + "))";

    //Klasse variabelen
    private String klantnaam;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(strCreateTableKlantLogin);
        db.execSQL(strCreateTableGeconfigureerdeLightyear);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Deze methode voegt een klant toe zodat deze kan inloggen
    public Boolean addKlantLogin(KlantLogin kl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMALADRES, kl.getMldrs());
        values.put(COL_WACHTWOORD, kl.getWchtwrd());
        values.put(COL_NAAM, kl.getNm());
        values.put(COL_TELEFOONNUMMER, kl.getTlfnnmmr());
        long result = db.insert(TABLE_KLANTLOGIN, null, values);
        db.close();
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Deze methode voegt een geconfigureerdelightyear toe
    public Boolean addGeconfigureerdeLightyear(GeconfigureerdeLightyear cl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAILADRES_KLANTLOGIN, LoginActivity.strEmailadres);
        values.put(COL_MODEL, cl.getMdl().toString());
        values.put(COL_KLEUR, cl.getKlr().toString());
        values.put(COL_LAK, cl.getLk().toString());
        values.put(COL_VELG, cl.getVlg().toString());
        long result = db.insert(TABLE_GECONFIGUREERDELIGHTYEAR, null, values);
        db.close();
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Methode die kijkt of de gebruikersnaam van de klant al bestaat
    public Boolean checkEmailadres(String emailadres) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM KlantLogin WHERE mldrs = ?", new String[]{emailadres});
        if(cursor.getCount() > 0) {
            cursor.close();
            return false;
        } else {
            return true;
        }
    }

    //Methode die kijkt of de klant bestaat en inlogt als het waar is
    public Boolean checkLogin(String emailadres, String wachtwoord) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM KlantLogin WHERE mldrs = ? AND wchtwrd = ?", new String[]{emailadres, wachtwoord});
        if(cursor.getCount() > 0) {
            //klantnaam = cursor.getString(cursor.getColumnIndex(COL_NAAM));
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    //Methode die naam weergeeft bij profiel
    public String getKlantnaam() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nm FROM KlantLogin WHERE mldrs = ?", new String[]{LoginActivity.strEmailadres});
        if(cursor != null && cursor.moveToFirst()) {
            klantnaam = cursor.getString(cursor.getColumnIndex(COL_NAAM));
            cursor.close();
            return klantnaam;
        } else {
            return null;
        }
    }

    //Methode die de geconfigureerde Lightyear weergeeft
    public GeconfigureerdeLightyear getGeconfigureerdeLightyear() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM GeconfigureerdeLightyear WHERE mldrs = ?", new String[]{LoginActivity.strEmailadres});
        if(cursor != null && cursor.moveToFirst()) {
            Model model = Model.valueOf(cursor.getString(cursor.getColumnIndex(COL_MODEL)));
            Kleur kleur = Kleur.valueOf(cursor.getString(cursor.getColumnIndex(COL_KLEUR)));
            Lak lak = Lak.valueOf(cursor.getString(cursor.getColumnIndex(COL_LAK)));
            Velg velg = Velg.valueOf(cursor.getString(cursor.getColumnIndex(COL_VELG)));

            GeconfigureerdeLightyear geconfigureerdeLightyear = GeconfigureerdeLightyear.construct(model);

            geconfigureerdeLightyear.setKlr(kleur);
            geconfigureerdeLightyear.setLk(lak);
            geconfigureerdeLightyear.setVlg(velg);

            cursor.close();
            return geconfigureerdeLightyear;
        } else {
            return null;
        }

    }
}
