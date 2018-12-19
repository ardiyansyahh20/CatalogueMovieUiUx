package com.ardi.cataloguemovieuiux.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.provider.BaseColumns._ID;
import static com.ardi.cataloguemovieuiux.Database.DatabaseContract.filmColumns.BACKDROP;
import static com.ardi.cataloguemovieuiux.Database.DatabaseContract.filmColumns.JUDUL_FILM;
import static com.ardi.cataloguemovieuiux.Database.DatabaseContract.filmColumns.OVERVIEW;
import static com.ardi.cataloguemovieuiux.Database.DatabaseContract.filmColumns.POSTER;
import static com.ardi.cataloguemovieuiux.Database.DatabaseContract.filmColumns.RATING;
import static com.ardi.cataloguemovieuiux.Database.DatabaseContract.filmColumns.RILIS;
import static com.ardi.cataloguemovieuiux.Database.DatabaseContract.filmColumns.VOTE;
import static com.ardi.cataloguemovieuiux.Database.DatabaseContract.getColumnInt;
import static com.ardi.cataloguemovieuiux.Database.DatabaseContract.getColumnString;

public class Film implements Parcelable {

    private int id;
    private String judulFilm;
    private String overviewFilm;
    private String tanggalRilis;
    private String posterFilm;
    private String backdropFilm;
    private String ratingFilm;
    private String voteFilm;

    public Film() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudulFilm() {
        return judulFilm;
    }

    public void setJudulFilm(String judulFilm) {
        this.judulFilm = judulFilm;
    }

    public String getRatingFilm() {
        return ratingFilm;
    }

    public void setRatingFilm(String ratingFilm) {
        this.ratingFilm = ratingFilm;
    }

    public String getVoteFilm() {
        return voteFilm;
    }

    public void setVoteFilm(String voteFilm) {
        this.voteFilm = voteFilm;
    }

    public String getOverviewFilm() {
        return overviewFilm;
    }

    public void setOverviewFilm(String overviewFilm) {
        this.overviewFilm = overviewFilm;
    }

    public String getTanggalRilis() {
        return tanggalRilis;
    }

    public void setTanggalRilis(String tanggalRilis) {
        this.tanggalRilis = tanggalRilis;
    }

    public String getPosterFilm() {
        return posterFilm;
    }

    public void setPosterFilm(String posterFilm) {
        this.posterFilm = posterFilm;
    }

    public String getBackdropFilm() {
        return backdropFilm;
    }

    public void setBackdropFilm(String backdropFilm) {
        this.backdropFilm = backdropFilm;
    }

    public Film(JSONObject jsonObject){
        try {
            String judulFilm = jsonObject.getString("title");
            String overviewFilm = jsonObject.getString("overview");
            String tanggalRilis = jsonObject.getString("release_date");
            String posterFilm = jsonObject.getString("poster_path");
            String rating = jsonObject.getString("vote_average");
            String vote = jsonObject.getString("vote_count");
            String backdropFilm = jsonObject.getString("backdrop_path");
            String finalPoster = "http://image.tmdb.org/t/p/w185/"+posterFilm;
            String finalBackdrop = "http://image.tmdb.org/t/p/w780/"+ backdropFilm;
            String finalDate = parseDate(tanggalRilis);
            setRatingFilm(rating);
            setVoteFilm(vote);
            setJudulFilm(judulFilm);
            setTanggalRilis(finalDate);
            setBackdropFilm(finalBackdrop);
            setPosterFilm(finalPoster);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public Film(Cursor cursor){
        this.id = getColumnInt(cursor, _ID);
        this.judulFilm = getColumnString(cursor, JUDUL_FILM);
        this.overviewFilm = getColumnString(cursor, OVERVIEW);
        this.tanggalRilis = getColumnString(cursor, RILIS);
        this.posterFilm = getColumnString(cursor, POSTER);
        this.backdropFilm = getColumnString(cursor, BACKDROP);
        this.ratingFilm = getColumnString(cursor, RATING);
        this.voteFilm = getColumnString(cursor, VOTE);
    }

    protected Film(Parcel in) {
        id = in.readInt();
        judulFilm = in.readString();
        overviewFilm = in.readString();
        tanggalRilis = in.readString();
        posterFilm = in.readString();
        backdropFilm = in.readString();
        ratingFilm = in.readString();
        voteFilm = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    private String parseDate(String tanggal){
        String inputDate = "yyyy-MM-dd";
        String outputDate = "EEEE, MMM dd, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputDate, Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputDate, Locale.US);
        Date date;
        String string = null;

        try{
            date = inputFormat.parse(tanggal);
            string = outputFormat.format(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return string;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(judulFilm);
        dest.writeString(overviewFilm);
        dest.writeString(tanggalRilis);
        dest.writeString(posterFilm);
        dest.writeString(backdropFilm);
        dest.writeString(ratingFilm);
        dest.writeString(voteFilm);
    }
}
