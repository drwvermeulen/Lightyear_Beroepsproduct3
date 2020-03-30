package com.example.lightyear_beroepsproduct3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.lightyear_beroepsproduct3.LoginActivity.strEmailadres;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Klasse variabelen
    private String klantnaam;
    private ArrayList<GeconfigureerdeLightyear> geconfigureerdeLightyearList;
    public static final String PIONEERNUMMERREEKSID = "PioneerEdition";

    //Naam  van de database
    private static final String DATABASE_NAME = "LightyearDatabase";

    //Deze strings worden gebruikt voor de tabel klantlogin
    private static final String TABLE_KLANTLOGINS = "KlantLogins";
    private static final String COL_EMALADRES = "mldrs";
    private static final String COL_WACHTWOORD = "wchtwrd";
    private static final String COL_NAAM = "nm";
    private static final String COL_TELEFOONNUMMER = "tlfnnmmr";
    private static final String CONSTRAINT_PK_MLDRS = "pk_mldrs";

    //Dit is de string die uiteindelijk de tabel klantlogin gaat creeeren
    private String strCreateTableKlantLogin ="CREATE TABLE " + TABLE_KLANTLOGINS + "("
            + COL_EMALADRES + " TEXT NOT NULL, "
            + COL_WACHTWOORD + " TEXT NOT NULL, "
            + COL_NAAM + " TEXT NOT NULL, "
            + COL_TELEFOONNUMMER + " TEXT NOT NULL,"
            + "CONSTRAINT " + CONSTRAINT_PK_MLDRS + " PRIMARY KEY (" + COL_EMALADRES + "))";

    //Dit is de string die de tabel verwijdert
    private String strDropTableKlantLogin = "DROP TABLE IF EXISTS " + TABLE_KLANTLOGINS;

    //Deze strings worden gebruikt voor de tabel geconfigureerde lightyear
    private static final String TABLE_GECONFIGUREERDELIGHTYEARS = "GeconfigureerdeLightyears";
    private static final String COL_CONFIGURATIENUMMER = "cnfgrtnmmr";
    private static final String COL_EMAILADRES_KLANTLOGIN = "mldrs";
    private static final String COL_MODEL = "mdl";
    private static final String COL_KLEUR = "klr";
    private static final String COL_LAK = "lk";
    private static final String COL_VELG = "vlg";
    private static final String COL_PIONEEREDITION_PIONEEREDITION = "pnrdtn";
    private static final String CONSTRAINT_FK_MLDRS_KLANTLOGIN = "fk_mldrs";

    //Dit is de string die uiteindelijk de tabel geconfigureerdelightyear gaat creeeren
    private String strCreateTableGeconfigureerdeLightyear = "CREATE TABLE " + TABLE_GECONFIGUREERDELIGHTYEARS + "("
            + COL_CONFIGURATIENUMMER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_EMAILADRES_KLANTLOGIN + " TEXT NOT NULL, "
            + COL_MODEL + " TEXT NOT NULL, "
            + COL_KLEUR + " TEXT NOT NULL, "
            + COL_LAK + " TEXT NOT NULL, "
            + COL_VELG + " TEXT NOT NULL, "
            + COL_PIONEEREDITION_PIONEEREDITION + " INTEGER, "
            + "CONSTRAINT " + CONSTRAINT_FK_MLDRS_KLANTLOGIN + " FOREIGN KEY (" + COL_EMAILADRES_KLANTLOGIN + ") REFERENCES " + TABLE_KLANTLOGINS + " (" + COL_EMALADRES + "))";

    //Dit is de string die de tabel verwijdert
    private String strDropTableGeconfigureerdeLightyear = "DROP TABLE IF EXISTS " + TABLE_GECONFIGUREERDELIGHTYEARS;

    //Deze strings worden gebruikt voor de tabel NummerReeksen
    private static final String TABLE_NUMMERREEKSEN = "NummerReeksen";
    private static final String COL_NUMMERREEKS_ID = "NummerReeksID";
    private static final String COL_VOLGENDEWAARDE = "VolgendeWaarde";

    //Dit is de string die uiteindelijk de tabel NummerReeksen gaat creeeren
    private String strCreateNummerReeksen = "CREATE TABLE " + TABLE_NUMMERREEKSEN + "("
            + COL_NUMMERREEKS_ID + " TEXT PRIMARY KEY, "
            + COL_VOLGENDEWAARDE + " INTEGER )";

    //Dit is de string die de nummerreeksen initieert
    private String strInitialInsertNummerReeksen = "INSERT INTO " + TABLE_NUMMERREEKSEN + " VALUES ('"+ PIONEERNUMMERREEKSID + "', 1)";

    //Dit is de string die de tabel verwijdert
    private String strDropTableNummerReeksen = "DROP TABLE IF EXISTS " + TABLE_NUMMERREEKSEN;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(strCreateTableKlantLogin);
        db.execSQL(strCreateTableGeconfigureerdeLightyear);
        db.execSQL(strCreateNummerReeksen);
        db.execSQL(strInitialInsertNummerReeksen);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(strDropTableKlantLogin);
        db.execSQL(strDropTableGeconfigureerdeLightyear);
        db.execSQL(strDropTableNummerReeksen);
        onCreate(db);
    }

    //Deze methode voegt een klant toe zodat deze kan inloggen
    public Boolean addKlantLogin(KlantLogin kl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMALADRES, kl.getMldrs());
        values.put(COL_WACHTWOORD, kl.getWchtwrd());
        values.put(COL_NAAM, kl.getNm());
        values.put(COL_TELEFOONNUMMER, kl.getTlfnnmmr());
        long result = db.insert(TABLE_KLANTLOGINS, null, values);
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
        values.put(COL_EMAILADRES_KLANTLOGIN, strEmailadres);
        values.put(COL_MODEL, cl.getMdl().toString());
        values.put(COL_KLEUR, cl.getKlr().toString());
        values.put(COL_LAK, cl.getLk().toString());
        values.put(COL_VELG, cl.getVlg().toString());
        if(cl instanceof GeconfigureerdeLightyearPioneerEdition) {
            GeconfigureerdeLightyearPioneerEdition clpe = (GeconfigureerdeLightyearPioneerEdition) cl;
            values.put(COL_PIONEEREDITION_PIONEEREDITION, clpe.getPnrdtn());
        }
        long result = db.insert(TABLE_GECONFIGUREERDELIGHTYEARS, null, values);
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
        Cursor cursor = db.rawQuery("SELECT * FROM KlantLogins WHERE mldrs = ?", new String[]{emailadres});
        if(cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    //Methode die kijkt of de klant bestaat en inlogt als het waar is
    public Boolean checkLogin(String emailadres, String wachtwoord) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM KlantLogins WHERE mldrs = ? AND wchtwrd = ?", new String[]{emailadres, wachtwoord});
        if(cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    //Methode die naam weergeeft bij profiel
    public String getKlantnaam() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nm FROM KlantLogins WHERE mldrs = ?", new String[]{strEmailadres});
        if(cursor != null && cursor.moveToFirst()) {
            klantnaam = cursor.getString(cursor.getColumnIndex(COL_NAAM));
            cursor.close();
            return klantnaam;
        } else {
            return null;
        }
    }

    //Methode die de geconfigureerde Lightyear weergeeft
    public ArrayList<GeconfigureerdeLightyear> getGeconfigureerdeLightyearList() {
        geconfigureerdeLightyearList = new ArrayList<GeconfigureerdeLightyear>();
        SQLiteDatabase db = this.getReadableDatabase();
        if(db == null) {
            return geconfigureerdeLightyearList;
        }
        Cursor cursor = db.rawQuery("SELECT * FROM GeconfigureerdeLightyears WHERE mldrs = ?", new String[]{strEmailadres});
        while(cursor.moveToNext()) {
            Integer configuratienummer = cursor.getInt(cursor.getColumnIndex(COL_CONFIGURATIENUMMER));
            Model model = Model.getModel(cursor.getString(cursor.getColumnIndex(COL_MODEL)));
            Kleur kleur = Kleur.getKleur(cursor.getString(cursor.getColumnIndex(COL_KLEUR)));
            Lak lak = Lak.getLak(cursor.getString(cursor.getColumnIndex(COL_LAK)));
            Velg velg = Velg.getVelg(cursor.getString(cursor.getColumnIndex(COL_VELG)));
            Integer pioneeredition = cursor.getInt(cursor.getColumnIndex(COL_PIONEEREDITION_PIONEEREDITION));

            GeconfigureerdeLightyear geconfigureerdeLightyear = GeconfigureerdeLightyear.construct(model);

            geconfigureerdeLightyear.setCnfgrtnmmr(configuratienummer);
            geconfigureerdeLightyear.setKlr(kleur);
            geconfigureerdeLightyear.setLk(lak);
            geconfigureerdeLightyear.setVlg(velg);
            if(geconfigureerdeLightyear instanceof GeconfigureerdeLightyearPioneerEdition) {
                GeconfigureerdeLightyearPioneerEdition clpe = (GeconfigureerdeLightyearPioneerEdition) geconfigureerdeLightyear;
                clpe.setPnrdtn(pioneeredition);
                geconfigureerdeLightyearList.add(clpe);
            } else {
                geconfigureerdeLightyearList.add(geconfigureerdeLightyear);
            }
        }
        cursor.close();
        return geconfigureerdeLightyearList;
    }

    public Integer getNummerReeksVolgendeWaarde(String nummerReeksID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Integer laatsteWaarde = 0;
        if(db != null) {
            Cursor cursor = db.rawQuery("SELECT " + COL_VOLGENDEWAARDE + " FROM " + TABLE_NUMMERREEKSEN + " WHERE " + COL_NUMMERREEKS_ID + " = ?", new String[]{nummerReeksID});
            if(cursor.moveToFirst()) {
                laatsteWaarde = cursor.getInt(cursor.getColumnIndex(COL_VOLGENDEWAARDE));
            }
            cursor.close();
        }
        return laatsteWaarde;
    }

    public void updateNummerReeksVolgendeWaarde(String nummerReeksID, Integer pnrdtn) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(db != null) {
            String strUpdateNummerReeksLaatsteWaarde = "UPDATE " + TABLE_NUMMERREEKSEN + " SET " + COL_VOLGENDEWAARDE + " = " + pnrdtn + " WHERE " + COL_NUMMERREEKS_ID + " = '" + nummerReeksID + "'";
            db.execSQL(strUpdateNummerReeksLaatsteWaarde);
        }
    }
}
