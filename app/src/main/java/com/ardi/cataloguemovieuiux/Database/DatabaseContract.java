package com.ardi.cataloguemovieuiux.Database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;


public class DatabaseContract {
    public static final String TABLE = "movie";
    public static final class filmColumns implements BaseColumns{
        public static final String ID_FILM = "movie_id";
        public static final String JUDUL_FILM = "title";
        public static final String RILIS = "release_date";
        public static final String POSTER = "image_poster";
        public static final String OVERVIEW = "overview";
        public static final String BACKDROP = "backdrop";
        public static final String RATING = "rating";
        public static final String VOTE = "vote";
    }

    public static final String AUTHORITY = "com.ardi.cataloguemovieuiux";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE)
            .build();

    public static String getColumnString (Cursor cursor, String columnName){
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName));
    }

    public static int getColumnInt (Cursor cursor, String columnName){
        return cursor.getInt(cursor.getColumnIndexOrThrow(columnName));
    }
    public static long getColumnLong (Cursor cursor, String columnName){
        return cursor.getLong(cursor.getColumnIndexOrThrow(columnName));
    }

}
