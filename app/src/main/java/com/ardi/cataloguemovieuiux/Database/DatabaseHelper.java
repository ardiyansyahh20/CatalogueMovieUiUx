package com.ardi.cataloguemovieuiux.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbfilm";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_FILM = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL)",
            DatabaseContract.TABLE,
            DatabaseContract.filmColumns._ID,
            DatabaseContract.filmColumns.ID_FILM,
            DatabaseContract.filmColumns.JUDUL_FILM,
            DatabaseContract.filmColumns.RILIS,
            DatabaseContract.filmColumns.OVERVIEW,
            DatabaseContract.filmColumns.POSTER
    );

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FILM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE);
        onCreate(db);
    }
}
