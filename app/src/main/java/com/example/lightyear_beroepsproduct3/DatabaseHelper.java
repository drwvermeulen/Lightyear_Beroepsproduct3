package com.example.lightyear_beroepsproduct3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_KLANTLOGIN = "KlantLogin";
    private static final String COL_EMALADRES = "mldrs";
    private static final String COL_WACHTWOORD = "wchtwrd";
    private static final String COL_NAAM = "nm";
    private static final String COL_TELEFOONNUMMER = "tlfnnmmr";
    private static final String CONSTRAINT_PK_MLDRS = "pk_mldrs";

    private String strCreateTableKlantLogin ="CREATE TABLE " + TABLE_KLANTLOGIN + "("
            + COL_EMALADRES + " TEXT NOT NULL, "
            + COL_WACHTWOORD + " TEXT NOT NULL, "
            + COL_NAAM + " TEXT NOT NULL, "
            + COL_TELEFOONNUMMER + " TEXT NOT NULL,"
            + "CONSTRAINT " + CONSTRAINT_PK_MLDRS + " PRIMARY KEY (" + COL_EMALADRES + "))";

    /*
    public static final String TABLE_MODEL = "Model";
    public static final String COL_MDL = "mdl";

    public String strCreateTableModel = "CREATE TABLE " + TABLE_MODEL + "("
            + COL_MDL + " TEXT CHECK(mdl IN ('Lightyear One', 'Lightyear One - Pioneer Edition')), "
            + "PRIMARY KEY ("+ COL_MDL + "))";

    public static final String TABLE_KLEUR = "Kleur";
    public static final String COL_KLR = "klr";

    public String strCreateTableKleur = "CREATE TABLE " + TABLE_KLEUR + "("
            + COL_KLR + " TEXT CHECK(klr IN ('Zwart', 'Wit', 'Rood', 'Blauw')), "
            + "PRIMARY KEY ("+ COL_KLR + "))";

    public static final String TABLE_LAK = "Lak";
    public static final String COL_LK = "lk";

    public String strCreateTableLak = "CREATE TABLE " + TABLE_LAK + "("
            + COL_LK + " TEXT CHECK(lk IN ('Unilak', 'Metallic lak', 'Matte lak')), "
            + "PRIMARY KEY ("+ COL_LK + "))";

    public static final String TABLE_VELG = "Velg";
    public static final String COL_VLG = "vlg";

    public String strCreateTableVelg = "CREATE TABLE " + TABLE_VELG + "("
            + COL_VLG + " TEXT CHECK(vlg IN ('16inch Velgen', '17inch Velgen', '18inch Velgen')), "
            + "PRIMARY KEY ("+ COL_VLG + "))";
     */

    public DatabaseHelper(Context context){
        super(context, TABLE_KLANTLOGIN, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(strCreateTableKlantLogin);
        /*
        db.execSQL(strCreateTableModel);
        db.execSQL(strCreateTableKleur);
        db.execSQL(strCreateTableLak);
        db.execSQL(strCreateTableVelg);
         */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

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

    //Methode die kijkt of de gebruikersnaam van de klant al bestaat
    public Boolean checkEmailadres(String emailadres) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM KlantLogin WHERE mldrs = ?", new String[]{emailadres});
        if(cursor.getCount() > 0) {
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
            return true;
        } else {
            return false;
        }
    }
}
