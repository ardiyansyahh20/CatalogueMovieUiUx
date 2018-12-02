package com.ardi.cataloguemovieuiux.Adapter;

import org.json.JSONObject;

public class MovieItems {
    private String titleFilm;
    private String voteFilm;
    private String ratingFilm;
    private String rilisFilm;
    private String overviewFilm;
    private String imageFilm;

    public MovieItems(JSONObject object) {
        try {
            String title = object.getString("title");
            String vote = object.getString("vote_count");
            String rating = object.getString("vote_average");
            String releaseDate = object.getString("release_date");
            String overview = object.getString("overview");
            String image = object.getString("poster_path");

            this.titleFilm = title;
            this.voteFilm = vote;
            this.ratingFilm = rating;
            this.rilisFilm = releaseDate;
            this.overviewFilm = overview;
            this.imageFilm = image;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MovieItems(){
    }

    public String getTitleFilm(){
        return titleFilm;
    }
    public void setTitleFilm(String titleFilm) {
        this.titleFilm = titleFilm;
    }

    public String getVoteFilm() {
        return voteFilm;
    }

    public void setVoteFilm(String voteFilm) {
        this.voteFilm = voteFilm;
    }

    public String getRatingFilm() {
        return ratingFilm;
    }

    public void setRatingFilm(String ratingFilm) {
        this.ratingFilm = ratingFilm;
    }

    public String getRilisFilm() {
        return rilisFilm;
    }

    public void setRilisFilm(String rilisFilm) {
        this.rilisFilm = rilisFilm;
    }

    public String getOverviewFilm() {
        return overviewFilm;
    }

    public void setOverviewFilm(String overviewFilm) {
        this.overviewFilm = overviewFilm;
    }

    public String getImageFilm() {
        return imageFilm;
    }

    public void setImageFilm(String imageFilm) {
        this.imageFilm = imageFilm;
    }
}