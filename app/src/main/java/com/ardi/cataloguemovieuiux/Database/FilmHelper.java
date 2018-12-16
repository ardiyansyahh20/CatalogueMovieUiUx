package com.ardi.cataloguemovieuiux.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static android.provider.BaseColumns._ID;
import static com.ardi.cataloguemovieuiux.Database.DatabaseContract.TABLE;

public class FilmHelper {

    private static String DATABASE_TABLE = TABLE;
    private Context context;
    private DatabaseHelper dbHelper;

    private SQLiteDatabase sqLiteDatabase;
    public FilmHelper(Context context){
        this.context = context;
    }

    public void open() throws SQLException{
        dbHelper = new DatabaseHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Cursor queryProvider(){
        return sqLiteDatabase.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " DESC "
        );
    }

    public Cursor queryByIdProvider(String id){
        return sqLiteDatabase.query(DATABASE_TABLE, null
        ,_ID + " = ? "
        , new String[]{id}
        ,null
        , null
        ,null
        ,null);
    }

    public long insertProvider(ContentValues values){
        return sqLiteDatabase.insert(DATABASE_TABLE, null, values);
    }

    public int updateProvider(String id, ContentValues values){
        return sqLiteDatabase.update(DATABASE_TABLE, values, _ID + " = ? ", new String[]{id});
    }
    public int deleteProvider(String id){
        return sqLiteDatabase.delete(DATABASE_TABLE, _ID + " = ? ", new String[]{id});
    }
}